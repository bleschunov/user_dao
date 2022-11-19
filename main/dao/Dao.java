package main.dao;

import main.exceptions.DaoException;

import java.util.List;

public interface Dao<T> {

    List<T> selectAll() throws DaoException;

    T selectById(int id) throws DaoException;
}
