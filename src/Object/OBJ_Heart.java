package Object;

import Main.GamePanel;
import Entity.Entity;

public class OBJ_Heart extends Entity{

    public OBJ_Heart(GamePanel gp){
        super(gp);

        name = "Heart";
        image = setup("objects/heart_full",gp.tileSize, gp.tileSize);
        image2 = setup("objects/heart_half",gp.tileSize, gp.tileSize);
        image3 = setup("objects/heart_blank",gp.tileSize, gp.tileSize);

    }
}
