package engine;

public class QuizCheckResult {
    private final boolean success;
    private final String feedback;

    public QuizCheckResult(boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }

    public QuizCheckResult(boolean success) {
        this(success, success ? "Congratulations, you're right!" : "Wrong answer! Please, try again.");
    }

    public boolean isSuccess() {
        return success;
    }

    public String getFeedback() {
        return feedback;
    }
}
