package DataBase;

import java.sql.*;

public class DataBase {
    boolean added = false;
    static DataBase db = null;
    Connection c = null;
    ResultSet rs = null;
    Statement stm = null;

    private DataBase() {
        EstablishCon();
    }

    public static DataBase getInstance() {
        if (db == null) {
            db = new DataBase();
        }
        return db;
    }

    public void EstablishCon() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:hs.db");
            stm = c.createStatement();
            stm.executeUpdate("create table if not exists HighScores(" +
                    "scores integer);");


        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void killCon() {
        try {
            c.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateScore(int newScore) {
        try {
            if (!added) {
                stm.executeUpdate("insert into HighScores(scores) values (" + newScore + ");");
                added = true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int requestScore(int i) {
        try {
            rs = stm.executeQuery("select * from HighScores order by scores desc limit 5");
            for (int j = 0; j < i; j++)
                rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void resetAdded() {
        added = false;
    }
}
