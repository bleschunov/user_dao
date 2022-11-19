package main.models;

import java.util.List;

public class Question {
    private final int id;
    private String caption, question, explanation;
    private List<Answer> answers;

    public Question(int id, String caption, String question, String explanation, List<Answer> answers) {
        this.id = id;
        this.caption = caption;
        this.question = question;
        this.explanation = explanation;
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", caption='" + caption + '\'' +
                ", question='" + question + '\'' +
                ", explanation='" + explanation + '\'' +
                ",\n answers=" + answers +
                '}';
    }
}
