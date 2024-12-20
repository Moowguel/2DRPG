package Object;
import Main.GamePanel;
import Entity.Entity;

public class OBJ_ChestO extends Entity{

    public OBJ_ChestO(GamePanel gp){
        super(gp);

        name = "ChestO";
        down1 = setup("Objects/ChestO",gp.tileSize, gp.tileSize);
    }

}
