package Main;
import Entity.NPC_OldMan;
import Object.OBJ_Key;
import Object.OBJ_Door;
import Object.OBJ_Chest;
import Object.OBJ_ChestO;
import Object.OBJ_Boots;
import Object.OBJ_Coin;
import monster.MON_GreenSlime;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        gp.obj[0] = new OBJ_Coin(gp);
        gp.obj[0].worldX = gp.tileSize*23;
        gp.obj[0].worldY = gp.tileSize*24;

    }

    public void setNpc(){
        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize*21;
        gp.npc[0].worldY = gp.tileSize*21;

    }

    public void setMonster(){
        gp.monster[0] = new MON_GreenSlime(gp);
        gp.monster[0].worldX = gp.tileSize*23;
        gp.monster[0].worldY = gp.tileSize*36;

        gp.monster[1] = new MON_GreenSlime(gp);
        gp.monster[1].worldX = gp.tileSize*23;
        gp.monster[1].worldY = gp.tileSize*37;

    }

}
