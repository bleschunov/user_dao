package main.dao.userDao;

import main.dao.AbstractDao;
import main.exceptions.DaoException;
import main.exceptions.DbException;
import main.helpers.PropertiesReader;
import main.helpers.extractor.UserExtractor;
import main.models.User;

import java.util.List;
import java.util.Properties;

public class UserDaoJdbc extends AbstractDao<User> implements UserDao {
    private final String SELECT_ALL_USERS_SQL;
    private final String SELECT_USER_BY_ID_SQL;

    public UserDaoJdbc() {
        Properties props = PropertiesReader.readSql();
        SELECT_ALL_USERS_SQL = props.getProperty("select.all.users.sql");
        SELECT_USER_BY_ID_SQL = props.getProperty("select.user.by.id.sql");
    }

    @Override
    public List<User> selectAll() throws DaoException {
        try {
            return selectAll(SELECT_ALL_USERS_SQL, new UserExtractor());
        } catch (DbException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public User selectById(int id) throws DaoException {
        try {
            String query = SELECT_USER_BY_ID_SQL.replaceFirst("\\?", String.valueOf(id));
            return selectById(query, new UserExtractor());
        } catch (DbException e) {
            throw new DaoException(e);
        }
    }
}
