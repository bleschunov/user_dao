package main.helpers.extractor;

import main.dao.answerDao.AnswerDaoJdbc;
import main.exceptions.DbException;
import main.models.Answer;
import main.models.Question;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionExtractor extends Extractor<Question>  {
    @Override
    public Question extractOne(ResultSet resultSet) throws DbException {
        try {
            int id = resultSet.getInt("id");
            String caption = resultSet.getString("caption");
            String question = resultSet.getString("question");
            String explanation = resultSet.getString("explanation");
            List<Answer> answers = new AnswerDaoJdbc().getAllConnectedWithQuestion(id);
            return new Question(id, caption, question, explanation, answers);

        } catch (SQLException e) {
            throw new DbException(e);
        }
    }
}
