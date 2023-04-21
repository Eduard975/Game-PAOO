package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*! \class public class KeyManager implements KeyListener
    \brief Gestioneaza intrarea (input-ul) de tastatura.

    Clasa citeste daca au fost apasata o tasta, stabiliteste ce tasta a fost actionata si seteaza corespunzator un flag.
    In program trebuie sa se tina cont de flagul aferent tastei de interes. Daca flagul respectiv este true inseamna
    ca tasta respectiva a fost apasata si false nu a fost apasata.
 */
public class KeyManager implements KeyListener {
    public boolean W;      /*!< Flag pentru tasta "sus" apasata.*/
    public boolean S;    /*!< Flag pentru tasta "jos" apasata.*/
    public boolean A;    /*!< Flag pentru tasta "stanga" apasata.*/
    public boolean D;   /*!< Flag pentru tasta "dreapta" apasata.*/

    public KeyManager() {
        W = false;
        S = false;
        A = false;
        D = false;
    }


    public void Update(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        ///se retine in vectorul de flaguri ca o tasta a fost apasata.
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
        ///se retine in vectorul de flaguri ca o tasta a fost eliberata.
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_W -> W = false;
            case KeyEvent.VK_S -> S = false;
            case KeyEvent.VK_A -> A = false;
            case KeyEvent.VK_D -> D = false;
        }
    }

}
