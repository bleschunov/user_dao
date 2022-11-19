package main.dao.answerDao;


import main.dao.AbstractDao;
import main.exceptions.DaoException;
import main.exceptions.DbException;
import main.helpers.PropertiesReader;
import main.helpers.extractor.AnswerExtractor;
import main.models.Answer;

import java.util.List;
import java.util.Properties;

public class AnswerDaoJdbc extends AbstractDao<Answer> implements AnswerDao {

    private final String SELECT_ALL_ANSWERS_SQL;
    private final String SELECT_ANSWER_BY_ID_SQL;
    private final String SELECT_ALL_ANSWERS_CONNECTED_WITH_QUESTION_SQL;

    public AnswerDaoJdbc() {
        Properties props = PropertiesReader.readSql();
        SELECT_ALL_ANSWERS_SQL = props.getProperty("select.all.answers.sql");
        SELECT_ANSWER_BY_ID_SQL = props.getProperty("select.answer.by.id.sql");
        SELECT_ALL_ANSWERS_CONNECTED_WITH_QUESTION_SQL =
                props.getProperty("select.all.answers.connected.with.question.sql");
    }

    @Override
    public List<Answer> selectAll() throws DaoException {
        try {
            return selectAll(SELECT_ALL_ANSWERS_SQL, new AnswerExtractor());
        } catch (DbException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Answer selectById(int id) throws DaoException {
        try {
            String query = SELECT_ANSWER_BY_ID_SQL.replaceFirst("\\?", String.valueOf(id));
            return selectById(query, new AnswerExtractor());
        } catch (DbException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Answer> getAllConnectedWithQuestion(int questionId) throws DbException {
        try {
            String query = SELECT_ALL_ANSWERS_CONNECTED_WITH_QUESTION_SQL
                    .replaceFirst("\\?", String.valueOf(questionId));
            return selectAll(query, new AnswerExtractor());
        } catch (DbException e) {
            throw new DaoException(e);
        }
    }
}
