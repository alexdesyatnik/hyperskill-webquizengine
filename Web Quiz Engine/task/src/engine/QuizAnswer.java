package engine;

import java.util.Collection;
import java.util.List;

public class QuizAnswer {
    private Collection<Integer> answer = List.of();

    public QuizAnswer() {}

    public QuizAnswer(Collection<Integer> answer) {
        this.answer = answer;
    }

    public Collection<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(Collection<Integer> answer) {
        this.answer = answer;
    }
}
