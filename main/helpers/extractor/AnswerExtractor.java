package main.helpers.extractor;

import main.exceptions.DbException;
import main.models.Answer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnswerExtractor extends Extractor<Answer> {
    @Override
    public Answer extractOne(ResultSet resultSet) throws DbException {
        try {
            int id = resultSet.getInt("id");
            String answer = resultSet.getString("answer");
            boolean isCorrect = resultSet.getBoolean("is_correct");
            return new Answer(id, answer, isCorrect);

        } catch (SQLException e) {
            throw new DbException(e);
        }
    }
}
