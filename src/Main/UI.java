package Main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import Entity.Entity;
import Object.OBJ_Heart;


public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font pixelFont;
    BufferedImage heart_full, heart_half, heart_blank;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public int commandNum = 0;

    public String currentDialogue = "";

    public UI(GamePanel gp){
        this.gp = gp;

        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("Font/Minecraft.ttf");
            pixelFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //CREATE HUD OBJECT
        Entity heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;


    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;

        g2.setFont(pixelFont);
        g2.setColor(Color.white);


        //TITLE STATE
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }


        //PLAY STATE DRAW UI
        if(gp.gameState == gp.playState){
            drawPlayerLife();
        }
        //PAUSE STATE DRAW UI
        if (gp.gameState == gp.pauseState){
            drawPauseScreen();
            drawPlayerLife();
        }
        //DIALOGUE STATE DRAW UI
        if (gp.gameState == gp.dialogueState){
            drawDialogueScreen();
        }
    }

    public void drawPlayerLife(){

//        gp.player.life = 5;

        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;


        // DRAW MAX HEART
        while(i < gp.player.maxLife/2){
            g2.drawImage(heart_blank, x, y,null);
            i++;
            x += gp.tileSize;
        }

        //RESET
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;


        //DRAW CURRENT LIFE
        while (i < gp.player.life){
            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i < gp.player.life) {
                g2.drawImage(heart_full, x, y,null);
            }
            i++;
            x += gp.tileSize;
        }
    }

    public void drawTitleScreen(){

        //BG COLLOR
        g2.setColor(new Color(0, 183, 255));
        g2.fillRect(0,0,gp.screenWidth, gp.screenHeight);


        g2.setFont(g2.getFont().deriveFont(Font.BOLD,70F));
        //text
        String text = "GameTest v2.0";

        //text position
        int x = getXForCenteredText(text);
        int y = gp.tileSize * 3;


        g2.setColor(Color.black);
        g2.drawString(text,x+5,y+5);//set text a little bit different for shadow effect on the title
        g2.setColor((Color.WHITE)); //set color on the text
        g2.drawString(text,x,y); // Set text on the screen

        //image on the title screen
        x = gp.screenHeight/2 - (gp.tileSize*2)/2 ;
        y += gp.tileSize*2;
        g2.drawImage(gp.player.down1, x,y,gp.tileSize*2, gp.tileSize*2,null);


        //MENU

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

        //
        text = "NEW GAME";
        x = getXForCenteredText(text);
        y += gp.tileSize*4;
        g2.drawString(text,x,y);
        if(commandNum == 0){
            g2.drawString(">", x-gp.tileSize, y);
        }



        text = "LOAD GAME";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text,x,y);
        if(commandNum == 1){
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "QUIT";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text,x,y);
        if(commandNum == 2){
            g2.drawString(">", x-gp.tileSize, y);
        }


    }

    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String text = "paused";

        int x = getXForCenteredText(text);
        int y = gp.screenHeight/2 - (gp.tileSize*2) /2;

        g2.drawString(text, x,y);
    }

    public void drawDialogueScreen(){

        // DIALOGUE POPUP SIZE
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*4;
        drawSubWindow(x, y ,width, height);


        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 25f));
        x += gp.tileSize;
        y += gp.tileSize;

        for(String line: currentDialogue.split("\n")){
            g2.drawString(line,x,y);
            y += 40;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height){
        Color c =new Color(0,0,0,220);
        g2.setColor(c);
        g2.fillRoundRect(x,y,width,height,35,35);

        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5,y+5,width-10,height-10,25,25);
    }

    public int getXForCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }

}
