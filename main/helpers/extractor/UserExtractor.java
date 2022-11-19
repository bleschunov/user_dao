package main.helpers.extractor;

import main.dao.questionDao.QuestionDaoJdbc;
import main.exceptions.DbException;
import main.models.Question;
import main.models.Quiz;
import main.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserExtractor extends Extractor<User> {

    @Override
    public User extractOne(ResultSet resultSet) throws DbException {
        try {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            return new User(id, name, email);

        } catch (SQLException e) {
            throw new DbException(e);
        }
    }
}
