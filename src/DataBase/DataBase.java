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
            c = DriverManager.getConnection("jdbc:sqlite:DataBase.db");
            p = c.prepareStatement("select * from HighScores");
            stm = c.createStatement();
            rs = p.executeQuery();
            stm.executeUpdate("insert into HighScores(HighScores, id)"
                    + "VALUES (" + 10 + ',' + 1 + ")");


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
