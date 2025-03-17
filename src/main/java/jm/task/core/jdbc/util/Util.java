package jm.task.core.jdbc.util;
import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Util {

    static {
        Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
    }

    private Util(){ }

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory(){
        try {
            Configuration configuration = new org.hibernate.cfg.Configuration();
            Properties settings = new Properties();
            settings.put(Environment.DRIVER, "org.postgresql.Driver");
            settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/postgres");
            settings.put(Environment.USER, "postgres");
            settings.put(Environment.PASS, "postgres");
            settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");

            //settings.put(Environment.HBM2DDL_AUTO, "update"); // Автоматическое обновление схемы базы данных

            configuration.setProperties(settings);
            configuration.addAnnotatedClass(User.class);

            return configuration.buildSessionFactory();

        }catch(Throwable e){
            System.err.println("Initial SessionFactory creation failed." + e);
            throw new ExceptionInInitializerError(e);
        }

    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public static void shutdown() {
        getSessionFactory().close();
    }

    //Настройки для JDBC
    private static final String PASSWORD_KEY = "password";
    private static final String USER_KEY = "user";
    private static final String URL_KEY = "url";

    static {
        loadDriver();
    }


    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(
                    PropertiesUtil.get(URL_KEY)
                    ,PropertiesUtil.get(USER_KEY)
                    ,PropertiesUtil.get(PASSWORD_KEY));
        } catch (SQLException e) {
            System.out.println("Ошибка в методе класса Util");
            throw new RuntimeException(e);
        }
    }
    private static void loadDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Ошибка в методе loadDriver класса Util, класс не найден postgresql");
            throw new RuntimeException(e);
        }
    }
}
