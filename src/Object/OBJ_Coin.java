package Object;
import Main.GamePanel;
import Entity.Entity;

public class OBJ_Coin extends Entity{

    public OBJ_Coin(GamePanel gp){
        super(gp);

        name = "Coin";
        down1 = setup("Objects/Coin_bronze",gp.tileSize, gp.tileSize);
    }

}
