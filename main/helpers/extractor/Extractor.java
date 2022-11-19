package main.helpers.extractor;

import main.exceptions.DbException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class Extractor<R> {

    public abstract R extractOne(ResultSet resultSet) throws DbException;

    public List<R> extractAll(ResultSet resultSet) throws DbException {
        try {
            List<R> result = new ArrayList<>();

            while (resultSet.next()) {
                R unit = extractOne(resultSet);
                result.add(unit);
            }

            return result;
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

}
