package main.helpers.extractor;

import main.dao.questionDao.QuestionDaoJdbc;
import main.exceptions.DbException;
import main.models.Question;
import main.models.Quiz;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuizExtractor extends Extractor<Quiz> {
    @Override
    public Quiz extractOne(ResultSet resultSet) throws DbException {
        try {
            int id = resultSet.getInt("id");
            String caption = resultSet.getString("caption");
            String description = resultSet.getString("description");
            List<Question> questions = new QuestionDaoJdbc().getAllConnectedWithQuiz(id);
            return new Quiz(id, caption, description, questions);

        } catch (SQLException e) {
            throw new DbException(e);
        }
    }
}
