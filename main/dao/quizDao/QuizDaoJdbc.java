package main.dao.quizDao;

import main.dao.AbstractDao;
import main.exceptions.DaoException;
import main.exceptions.DbException;
import main.helpers.PropertiesReader;
import main.helpers.extractor.QuizExtractor;
import main.models.Quiz;

import java.util.List;
import java.util.Properties;

public class QuizDaoJdbc extends AbstractDao<Quiz> implements QuizDao {
    private final String SELECT_ALL_QUIZES_SQL;
    private final String SELECT_QUIZ_BY_ID_SQL;

    public QuizDaoJdbc() {
        Properties props = PropertiesReader.readSql();
        SELECT_ALL_QUIZES_SQL = props.getProperty("select.all.quizes.sql");
        SELECT_QUIZ_BY_ID_SQL = props.getProperty("select.quiz.by.id.sql");
    }

    @Override
    public List<Quiz> selectAll() throws DaoException {
        try {
            return selectAll(SELECT_ALL_QUIZES_SQL, new QuizExtractor());
        } catch (DbException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Quiz selectById(int id) throws DaoException {
        try {
            String query = SELECT_QUIZ_BY_ID_SQL.replaceFirst("\\?", String.valueOf(id));
            return selectById(query, new QuizExtractor());
        } catch (DbException e) {
            throw new DaoException(e);
        }
    }
}
