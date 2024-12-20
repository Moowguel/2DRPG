package Entity;

import Main.GamePanel;
import java.util.Random;

public class NPC_OldMan extends Entity{

    public NPC_OldMan(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 1;

        getImage();
        setDialogue();
    }

    public void getImage(){

        up1 = setup("Npc/oldman_up_1",gp.tileSize, gp.tileSize);
        up2 = setup("Npc/oldman_up_2",gp.tileSize, gp.tileSize);
        down1 = setup("Npc/oldman_down_1",gp.tileSize, gp.tileSize);
        down2 = setup("Npc/oldman_down_2",gp.tileSize, gp.tileSize);
        left1 = setup("Npc/oldman_left_1",gp.tileSize, gp.tileSize);
        left2 = setup("Npc/oldman_left_2",gp.tileSize, gp.tileSize);
        right1 = setup("Npc/oldman_right_1",gp.tileSize, gp.tileSize);
        right2 = setup("Npc/oldman_right_2",gp.tileSize, gp.tileSize);

    }

    public void setDialogue(){
        dialogue[0] = "Once ago I was a Wizard";
        dialogue[1] = "But its past time...";
        dialogue[2] = "I came here for the treasure \n hided on this island";
        dialogue[3] = "but...";
    }

    @Override
    public void setAction() {

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

    public void speak() {
        super.speak();
    }

}
