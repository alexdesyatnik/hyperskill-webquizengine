package engine.controller;

import engine.QuizAnswer;
import engine.QuizCheckResult;
import engine.entity.Quiz;
import engine.entity.QuizCompletion;
import engine.repository.QuizCompletionRepository;
import engine.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Quiz not found!")
    static class QuizNotFoundException extends RuntimeException {
    }

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizCompletionRepository quizCompletionRepository;

    // Get a list of all quizzes
    @GetMapping()
    public Page<Quiz> getAllQuizzes(@RequestParam(defaultValue = "0") int page) {
        var paging = PageRequest.of(page, 10);
        return quizRepository.findAll(paging);
    }

    // Get a quiz by its id, possibly resulting in a 404 error if quiz is not found
    @GetMapping("/{id}")
    public Quiz getQuiz(@PathVariable int id) {
        return quizRepository.findById(id).orElseThrow(QuizNotFoundException::new);
    }

    // Add a new quiz to the database
    @PostMapping(consumes = "application/json")
    public Quiz addQuiz(@Valid @RequestBody Quiz newQuiz, Principal principal) {
        newQuiz.setAuthor(principal.getName());
        return quizRepository.save(newQuiz);
    }

    // Solve a quiz
    @PostMapping(value = "/{id}/solve", consumes = "application/json")
    public QuizCheckResult checkQuiz(@PathVariable int id, @RequestBody QuizAnswer answer,
                                     Principal principal) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(QuizNotFoundException::new);
        boolean quizResult = quiz.checkAnswer(answer.getAnswer());
        if (quizResult) {
            QuizCompletion quizCompletion = new QuizCompletion();
            quizCompletion.setQuizId(id);
            quizCompletion.setCompletedAt(LocalDateTime.now());
            quizCompletion.setUserEmail(principal.getName());
            quizCompletionRepository.save(quizCompletion);
        }
        return new QuizCheckResult(quizResult);
    }

    // Get a list of completed quizzes
    @GetMapping("/completed")
    public Page<QuizCompletion> getCompletionList(@RequestParam(defaultValue = "0") int page,
                                                  Principal principal) {
        var paging = PageRequest.of(page, 10, Sort.by("completedAt").descending());
        return quizCompletionRepository.findAllByUserEmail(principal.getName(), paging);
    }

    // Delete a quiz
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuiz(@PathVariable int id, Principal principal) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(QuizNotFoundException::new);
        if (!quiz.getAuthor().equals(principal.getName())) {
            return new ResponseEntity<>("User is not the author of the quiz", HttpStatus.FORBIDDEN);
        }
        quizRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
