package Object;
import Main.GamePanel;
import Entity.Entity;

public class OBJ_Door extends Entity{

    public OBJ_Door(GamePanel gp){
        super(gp);

        name = "Door";
        down1 = setup("Objects/Door",gp.tileSize, gp.tileSize);
        collision = true;

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }

}
