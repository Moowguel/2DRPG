package Entity;

import Main.GamePanel;
import Main.KeyHandler;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    int standCounter;

    public Player(GamePanel gp, KeyHandler keyH){

        super(gp);

        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);


        solidArea = new Rectangle(); // determinate the area of collision
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 26;
        solidArea.height = 26;


        // bigger the number, bigger the range.
        attackArea.width = 36;
        attackArea.height = 36;


        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }

    public void setDefaultValues(){
        //PLAYER STATUS
        maxLife = 6;
        life = maxLife;


        //world start player position
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down"; // direction sprite start                                               ww
    }

    public void getPlayerImage(){

        up1 = setup("player/boy_up_1",gp.tileSize, gp.tileSize);
        up2 = setup("player/boy_up_2",gp.tileSize, gp.tileSize);
        down1 = setup("player/boy_down_1",gp.tileSize, gp.tileSize);
        down2 = setup("player/boy_down_2",gp.tileSize, gp.tileSize);
        left1 = setup("player/boy_left_1",gp.tileSize, gp.tileSize);
        left2 = setup("player/boy_left_2",gp.tileSize, gp.tileSize);
        right1 = setup("player/boy_right_1",gp.tileSize, gp.tileSize);
        right2 = setup("player/boy_right_2",gp.tileSize, gp.tileSize);

    }

    public void getPlayerAttackImage(){

        attackUp1 = setup("player/boy_attack_up_1",gp.tileSize, gp.tileSize*2);
        attackUp2 = setup("player/boy_attack_up_2",gp.tileSize, gp.tileSize*2);
        attackDown1 = setup("player/boy_attack_down_1",gp.tileSize, gp.tileSize*2);
        attackDown2 = setup("player/boy_attack_down_2",gp.tileSize, gp.tileSize*2);
        attackLeft1 = setup("player/boy_attack_left_1",gp.tileSize*2, gp.tileSize);
        attackLeft2 = setup("player/boy_attack_left_2",gp.tileSize*2, gp.tileSize);
        attackRight1 = setup("player/boy_attack_right_1",gp.tileSize*2, gp.tileSize);
        attackRight2 = setup("player/boy_attack_right_2",gp.tileSize*2, gp.tileSize);

    }

    public void update(){

        if (attacking == true){
            attacking();
        }
        //player movement update
        else if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftpressed == true || keyH.rigthPressed || keyH.enterPressed == true){
            if (keyH.upPressed == true){
                direction = "up";
            } else if (keyH.downPressed == true) {
                direction = "down";
            } else if (keyH.leftpressed == true) {
                direction = "left";
            } else if (keyH.rigthPressed == true) {
                direction = "right";
            }

            // CHECKING TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);
            //CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);
            //CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interacNPC(npcIndex);
            //CHECK MONSTER COLLISION
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);
            //CHECK EVENT
            gp.eHandler.checkEvent();


            //collision checker - movement
            if(collisionOn == false && keyH.enterPressed == false){
                switch (direction){
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }

            gp.keyH.enterPressed = false;


            //animation change
            spriteCounter++;
            if(spriteCounter > 13){
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if (spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        else{

            standCounter++;//add a buffer into animation state default
            if(standCounter == 10){
                spriteNum = 1;//animation state default
                standCounter =0;
            }
        }

        if (invincible == true){
            invincibleCounter++;
            if (invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void attacking(){
        spriteCounter++;

        if(spriteCounter <= 5){
            spriteNum = 1;
        }
        if (spriteCounter > 5 && spriteCounter <= 25){
            spriteNum = 2;

            //save current worldx/y solid area
            int currentWorldX = worldX;
            int currentWoedlY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            //adjust player's wordx/y for the attackarea
            switch (direction){
                case "up":
                    worldY -= attackArea.height;
                    break;
                case "down":
                    worldY += attackArea.height;
                    break;
                case "left":
                    worldX -= attackArea.width;
                    break;
                case "right":
                    worldX += attackArea.width;
                    break;
            }

            //attack area becomes solid area
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;
            //check monster collision with the update worldx/y and solid area
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);
            //restore original area of collision
            worldX = currentWorldX;
            worldY = currentWoedlY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if (spriteCounter >25){
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }


    public void pickUpObject(int i){
        if (i != 999){

        }
    }

    public void interacNPC(int i){

        if (gp.keyH.enterPressed == true){
            if (i != 999){
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
            else {
                gp.playSE(7);
                attacking = true;
            }
        }

    }

    public void contactMonster(int i){
        if (i != 999){
            if(invincible == false){
                gp.playSE(6);
                life -= 1;
                invincible = true;
            }
        }
    }
    public void damageMonster(int i) {
        if (i != 999) {
           if (gp.monster[i].invincible == false){

               gp.playSE(5);
               gp.monster[i].life -= 1;
               gp.monster[i].invincible = true;

               if (gp.monster[i].life <= 0){
                   gp.monster[i].dying = true;
               }
           }
        }
    }



    public void draw(Graphics2D g2){

        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction){
            case "up":
                if (attacking == false){
                    if (spriteNum == 1){
                        image = up1;
                    }
                    if (spriteNum == 2){
                        image = up2;
                    }
                }
                if (attacking == true){
                    tempScreenY = screenY - gp.tileSize;
                    if (spriteNum == 1){
                        image = attackUp1;
                    }
                    if (spriteNum == 2){
                        image = attackUp2;
                    }
                }
                break;
            case "down":
                if (attacking == false){
                    if (spriteNum == 1){
                        image = down1;
                    }
                    if (spriteNum == 2){
                        image = down2;
                    }
                }
                if (attacking == true){
                    if (spriteNum == 1){
                        image = attackDown1;
                    }
                    if (spriteNum == 2){
                        image = attackDown2;
                    }
                }
                break;
            case "left":
                if (attacking == false) {
                    if (spriteNum == 1){
                        image = left1;
                    }
                    if (spriteNum == 2){
                        image = left2;
                    }
                }
                if (attacking == true) {
                    tempScreenX = screenX - gp.tileSize;
                    if (spriteNum == 1){
                        image = attackLeft1;
                    }
                    if (spriteNum == 2){
                        image = attackLeft2;
                    }
                }
                break;
            case "right":
                if (attacking == false) {
                    if (spriteNum == 1){
                        image = right1;
                    }
                    if (spriteNum == 2){
                        image = right2;
                    }
                }
                if (attacking == true){
                    if (spriteNum == 1){
                        image = attackRight1;
                    }
                    if (spriteNum == 2){
                        image = attackRight2;
                    }
                }
                break;
        }

        if(invincible == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));//set opacity level when player is invencible
        }

        g2.drawImage(image, tempScreenX, tempScreenY,null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));//reset opacity level

    }
}
