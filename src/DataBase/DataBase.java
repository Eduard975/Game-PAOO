package DataBase;

import GameWindow.Game;

import java.sql.*;

public class DataBase {
    Game gp;
    Connection c = null;
    PreparedStatement p = null;
    ResultSet rs = null;
    Statement stm = null;

    public DataBase() {
        EstablishCon();
    }

    public void EstablishCon() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:hs.db");
            stm = c.createStatement();
            stm.executeUpdate("create table if not exists HighScores(" +
                    "scores integer," +
                    "place integer);");
            stm.executeUpdate("insert into HighScores(scores, place)"
                    + "VALUES (" + 10 + ',' + 1 + ");");
            rs = stm.executeQuery("select * from HighScores");


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
}
