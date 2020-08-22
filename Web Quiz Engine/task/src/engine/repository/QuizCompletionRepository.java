package engine.repository;

import engine.entity.QuizCompletion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface QuizCompletionRepository extends PagingAndSortingRepository<QuizCompletion, Integer> {
    Page<QuizCompletion> findAllByUserEmail(String userEmailValue, Pageable pageable);
}
