package main.dao.questionDao;

import main.dao.AbstractDao;
import main.exceptions.DaoException;
import main.exceptions.DbException;
import main.helpers.PropertiesReader;
import main.helpers.extractor.AnswerExtractor;
import main.helpers.extractor.QuestionExtractor;
import main.models.Answer;
import main.models.Question;

import java.util.List;
import java.util.Properties;

public class QuestionDaoJdbc extends AbstractDao<Question> implements QuestionDao {

    private final String SELECT_ALL_QUESTIONS_SQL;
    private final String SELECT_QUESTION_BY_ID_SQL;
    private final String SELECT_ALL_QUESTIONS_CONNECTED_WITH_QUIZ_SQL;

    public QuestionDaoJdbc() {
        Properties props = PropertiesReader.readSql();
        SELECT_ALL_QUESTIONS_SQL = props.getProperty("select.all.questions.sql");
        SELECT_QUESTION_BY_ID_SQL = props.getProperty("select.question.by.id.sql");
        SELECT_ALL_QUESTIONS_CONNECTED_WITH_QUIZ_SQL =
                props.getProperty("select.all.questions.connected.with.quiz.sql");
    }

    @Override
    public List<Question> selectAll() throws DaoException {
        try {
            return selectAll(SELECT_ALL_QUESTIONS_SQL, new QuestionExtractor());
        } catch (DbException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Question selectById(int id) throws DaoException {
        try {
            String query = SELECT_QUESTION_BY_ID_SQL.replaceFirst("\\?", String.valueOf(id));
            return selectById(query, new QuestionExtractor());
        } catch (DbException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Question> getAllConnectedWithQuiz(int questionId) throws DbException {
        try {
            String query = SELECT_ALL_QUESTIONS_CONNECTED_WITH_QUIZ_SQL
                    .replaceFirst("\\?", String.valueOf(questionId));
            return selectAll(query, new QuestionExtractor());
        } catch (DbException e) {
            throw new DaoException(e);
        }
    }
}
