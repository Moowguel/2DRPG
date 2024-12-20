package Object;
import Main.GamePanel;
import Entity.Entity;

public class OBJ_Boots extends Entity{

    public OBJ_Boots(GamePanel gp){
        super(gp);

        name = "Boots";
        down1 = setup("objects/Bbots",gp.tileSize, gp.tileSize);
    }

}
