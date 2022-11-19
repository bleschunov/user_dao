package main.models;

import java.util.List;

public class Quiz {
    private final int id;
    private String caption, description;
    private List<Question> questions;

    public Quiz(int id, String caption, String description, List<Question> questions) {
        this.id = id;
        this.caption = caption;
        this.description = description;
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", caption='" + caption + '\'' +
                ", description='" + description + '\'' +
                ",\n questions=" + questions +
                '}';
    }
}
