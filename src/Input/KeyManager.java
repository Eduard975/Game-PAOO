package Input;

import GameWindow.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
    public boolean W;      /*!< Flag pentru tasta "sus" apasata.*/
    public boolean S;      /*!< Flag pentru tasta "jos" apasata.*/
    public boolean A;      /*!< Flag pentru tasta "stanga" apasata.*/
    public boolean D;      /*!< Flag pentru tasta "dreapta" apasata.*/
    public boolean Esc;

    public boolean Space;

    Game gp;

    public KeyManager(Game gp) {
        Esc = false;
        W = false;
        S = false;
        A = false;
        D = false;
        Space = false;
        this.gp = gp;
    }

    public void Update(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        int option = gp.ui.getSelectedCommand();
        if (gp.getMenuState() == 1) {
            switch (code) {
                case KeyEvent.VK_W -> {
                    if (option >= 2)
                        gp.ui.setSelectedCommand(0);
                    else {
                        option++;
                        gp.ui.setSelectedCommand(option);
                    }
                }
                case KeyEvent.VK_S -> {
                    if (option <= 0)
                        gp.ui.setSelectedCommand(2);
                    else {
                        option--;
                        gp.ui.setSelectedCommand(option);
                    }
                }
                case KeyEvent.VK_SPACE -> {
                    int sub_state = gp.ui.getSubMenuState();
                    if (sub_state == 0) {
                        if (option == 2) {
                            gp.ui.setSubMenuState(1);
                        }

                        if (option == 1) {
                            //To_do
                        }

                        if (option == 0) {
                            System.exit(0);
                        }
                    }

                    if (sub_state == 1) {
                        if (option == 2) {
                            gp.player.setTexture_option(0);
                        }

                        if (option == 1) {
                            gp.player.setTexture_option(1);
                        }

                        if (option == 0) {
                            gp.player.setTexture_option(2);
                        }

                        gp.ui.setSubMenuState(2);
                    }

                    if (sub_state == 2) {
                        if (option == 2) {
                            gp.tileManager.changeMap(0);
                            gp.player.updateCoords();
                        }

                        if (option == 1) {
                            gp.tileManager.changeMap(1);
                            gp.player.updateCoords();
                        }

                        if (option == 0) {
                            gp.tileManager.changeMap(2);
                            gp.player.updateCoords();
                        }

                        gp.setMenuState(0);

                        gp.setPlayState(true);
                    }
                }
            }
        } else {
            switch (code) {
                case KeyEvent.VK_W -> W = true;
                case KeyEvent.VK_S -> S = true;
                case KeyEvent.VK_A -> A = true;
                case KeyEvent.VK_D -> D = true;
                case KeyEvent.VK_ESCAPE -> gp.setPlayState(!gp.getPlayState());
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_W -> W = false;
            case KeyEvent.VK_S -> S = false;
            case KeyEvent.VK_A -> A = false;
            case KeyEvent.VK_D -> D = false;
            case KeyEvent.VK_ESCAPE -> gp.setPlayState(gp.getPlayState());
        }
    }

}
