package Object;
import Main.GamePanel;
import Entity.Entity;

public class OBJ_Key extends Entity{

    public OBJ_Key(GamePanel gp){
        super(gp);

        name = "Key";
        down1 = setup("Objects/key",gp.tileSize, gp.tileSize);
    }

}
