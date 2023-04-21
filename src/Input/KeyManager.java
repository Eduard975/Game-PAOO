package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
    public boolean W;      /*!< Flag pentru tasta "sus" apasata.*/
    public boolean S;      /*!< Flag pentru tasta "jos" apasata.*/
    public boolean A;      /*!< Flag pentru tasta "stanga" apasata.*/
    public boolean D;      /*!< Flag pentru tasta "dreapta" apasata.*/

    public KeyManager() {
        W = false;
        S = false;
        A = false;
        D = false;
    }

    public void Update(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_W -> W = true;
            case KeyEvent.VK_S -> S = true;
            case KeyEvent.VK_A -> A = true;
            case KeyEvent.VK_D -> D = true;
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
        }
    }

}
