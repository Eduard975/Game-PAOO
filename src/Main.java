import GameWindow.Game;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Mesterul Grigore");

        //DataBase x = new DataBase();
        //x.killCon();

        Game Jocul = new Game();
        window.add(Jocul);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        Jocul.setupGame();
        Jocul.startGameThread();
    }


}