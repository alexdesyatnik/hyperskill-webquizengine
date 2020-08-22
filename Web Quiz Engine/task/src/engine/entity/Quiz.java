package engine.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
public class Quiz {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @JsonIgnore
    private String author;

    @NotNull
    @NotBlank(message = "Quiz title is required")
    private String title;

    @NotNull
    @NotBlank(message = "Quiz text is required")
    private String text;

    @NotNull
    @Size(min = 2)
    @ElementCollection
    private List<String> options;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ElementCollection
    private List<Integer> answer;

    public Quiz() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public List<Integer> getAnswer() {
        return answer != null ? answer : List.of();
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }

    public boolean checkAnswer(Collection<Integer> answer) {
        return Set.copyOf(this.answer).equals(Set.copyOf(answer));
    }
}
