package monster;

import Entity.Entity;
import Main.GamePanel;

import java.util.Random;

public class MON_GreenSlime extends Entity {

    GamePanel gp;

    public MON_GreenSlime(GamePanel gp) {
        super(gp);

        this.gp = gp;

        name = "Green Slime";
        speed = 1;
        type = 2;
        maxLife = 4;
        life = maxLife;


        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();

    }

    public void getImage(){

        up1 = setup("monster/greenslime_down_1",gp.tileSize, gp.tileSize);
        up2 = setup("monster/greenslime_down_2",gp.tileSize, gp.tileSize);
        down1 = setup("monster/greenslime_down_1",gp.tileSize, gp.tileSize);
        down2 = setup("monster/greenslime_down_2",gp.tileSize, gp.tileSize);
        left1 = setup("monster/greenslime_down_1",gp.tileSize, gp.tileSize);
        left2 = setup("monster/greenslime_down_2",gp.tileSize, gp.tileSize);
        right1 = setup("monster/greenslime_down_1",gp.tileSize, gp.tileSize);
        right2 = setup("monster/greenslime_down_2",gp.tileSize, gp.tileSize);

    }

    public void setAction(){

        actionLockCounter++;

        if (actionLockCounter == 60){ // 2 seconds buffer (120fps) to pick a movement

            // basic random movement
            Random random = new Random();
            int i = random.nextInt(100) +1; // pick arandom number to 1/100

            //moves with the random number
            if (i <= 25){
                direction = "up";
            }
            if (i > 25 && i <= 50){
                direction = "down";
            }
            if (i > 50 && i <= 75){
                direction = "left";
            }
            if (i > 75 && i <= 100){
                direction = "right";
            }

            actionLockCounter = 0; //reset 2 seconds buffer
        }
    }
}
