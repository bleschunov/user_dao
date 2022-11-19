package main.dao.answerDao;

import main.dao.Dao;
import main.exceptions.DbException;
import main.models.Answer;

import java.util.List;

public interface AnswerDao extends Dao<Answer> {
    List<Answer> getAllConnectedWithQuestion(int questionId) throws DbException;
}
