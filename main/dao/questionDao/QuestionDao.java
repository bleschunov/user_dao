package main.dao.questionDao;

import main.dao.Dao;
import main.exceptions.DbException;
import main.models.Question;

import java.util.List;

public interface QuestionDao extends Dao<Question> {
    List<Question> getAllConnectedWithQuiz(int quizId) throws DbException;
}
