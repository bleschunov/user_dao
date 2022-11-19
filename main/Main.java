package main;

import main.connectionFactory.ConnectionFactoryFactory;
import main.dao.questionDao.QuestionDao;
import main.dao.questionDao.QuestionDaoJdbc;
import main.dao.quizDao.QuizDao;
import main.dao.quizDao.QuizDaoJdbc;
import main.dao.userDao.UserDao;
import main.dao.userDao.UserDaoJdbc;
import main.exceptions.DaoException;
import main.helpers.PropertiesReader;
import main.models.DbName;
import main.models.Question;
import main.models.Quiz;
import main.models.User;
import main.transactionManager.TransactionManager;

import java.util.List;
import java.util.concurrent.Callable;

public class Main {


    public static void main(String[] args) {

//        ConnectionFactoryFactory.setFactoryType(ConnectionFactoryFactory.FactoryType.RAW);
//        PropertiesReader.setDbName(DbName.MYSQL);

        TransactionManager<List<Question>> transactionManager = new TransactionManager<>();
//        QuizDao quizDao = new QuizDaoJdbc();
//        UserDao userDao = new UserDaoJdbc();
        QuestionDao questionDao = new QuestionDaoJdbc();

        try {

//            List<Quiz> quizList = transactionManager.doInTransaction(() -> quizDao.selectAll());
//            List<User> users = transactionManager.doInTransaction(() -> userDao.selectAll());
            List<Question> questions = transactionManager.doInTransaction(() -> questionDao.selectAll());

//            for (Quiz quiz : quizList) {
//                System.out.println(quiz);
//                System.out.println();
//            }

//            for (User user : users) {
//                System.out.println(user);
//                System.out.println();
//            }

            for (Question question : questions) {
                System.out.println(question);
                System.out.println();
            }


        } catch (DaoException e) {
            e.printStackTrace();
        }

//        try {
//
//            System.out.println("Simple: ");
//            Speed.test(new PostgresUserDao(postgresLogin, postgresPassword));
//
//            ConnectionFactoryFactory.setFactoryType(ConnectionFactoryFactory.FactoryType.CACHED);
//
//            System.out.println("Cached: ");
//            Speed.test(new PostgresUserDao(postgresLogin, postgresPassword));
//
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}