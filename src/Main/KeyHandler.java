package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftpressed, rigthPressed, enterPressed;
    boolean checkDrawTime = false;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();


        //TITLE STATE
        if (gp.gameState == gp.titleState){

            if (code == KeyEvent.VK_W){
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0){
                    gp.ui.commandNum = 2;
                }
            }
            if (code == KeyEvent.VK_S){
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 2){
                    gp.ui.commandNum = 0;
                }
            }

            if(code == KeyEvent.VK_ENTER){
                if (gp.ui.commandNum == 0){
                    gp.gameState = gp.playState;
                    gp.playMusic(0);
                }
                if (gp.ui.commandNum == 1) {

                }
                if (gp.ui.commandNum == 2) {
                    System.exit(0);
                }
            }
        }


        //play state

        else if (gp.gameState == gp.playState){
            if (code == KeyEvent.VK_W){
                upPressed = true;
            }
            if (code == KeyEvent.VK_S){
                downPressed = true;
            }
            if (code == KeyEvent.VK_A){
                leftpressed = true;
            }
            if (code == KeyEvent.VK_D){
                rigthPressed = true;
            }
            if (code == KeyEvent.VK_ENTER){
                enterPressed = true;
            }

            //PAUSE
            if (code == KeyEvent.VK_P){
                gp.gameState = gp.pauseState;
            }

            //DEBUG
            if (code == KeyEvent.VK_QUOTE){
                if (checkDrawTime == false){
                    checkDrawTime = true;
                } else if (checkDrawTime == true) {
                    checkDrawTime = false;
                }
            }
        }

        // PAUSE STATE

        else if (gp.gameState == gp.pauseState){

            if (code == KeyEvent.VK_P){
                gp.gameState = gp.playState;
            }

        }


        // DIALOGUE STATE

        else if (gp.gameState == gp.dialogueState){
            if (code == KeyEvent.VK_ENTER){
                gp.gameState = gp.playState;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code =e.getKeyCode();

        if (code == KeyEvent.VK_W){
            upPressed = false;
        }
        if (code == KeyEvent.VK_S){
            downPressed = false;
        }
        if (code == KeyEvent.VK_A){
            leftpressed = false;
        }
        if (code == KeyEvent.VK_D){
            rigthPressed = false;
        }

    }
}
