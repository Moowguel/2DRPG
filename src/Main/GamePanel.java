package Main;

import Entity.Entity;
import Entity.Player;
import Tile.TileManager;
import Entity.Entity;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable{

    //SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 tile, default size player / npc
    final int scale = 3; // scale the default size
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768px
    public final int screenHeight = tileSize * maxScreenRow; // 576px

    // WORLD SETTING
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;


    //FPS SETTINGS
    int FPS = 60;

    // SYSTEM
    Thread gameThread;
    Sound music = new Sound();
    Sound se = new Sound();
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    public EventHandler eHandler = new EventHandler(this);
    public UI ui = new UI(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);

    //ENTITY AND OBJ
    public Player player = new Player(this,keyH);
    public Entity obj[] = new Entity[10];
    public Entity npc[] = new Entity[10];
    public Entity monster[] = new Entity[20];
    ArrayList<Entity> entityList = new ArrayList<>();


    //GAME STATE

    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // improve game rendering performance
        this.addKeyListener(keyH);// makes gamepanel recognize key input
        this.setFocusable(true); //gamepanel can be focused to receive key input
    }

    public void setupGame(){
        aSetter.setObject();
        aSetter.setNpc();
        aSetter.setMonster();
        gameState = titleState;

    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer =0;
        int drawCount = 0;

        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000){
                System.out.println("FPS:"+ drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update(){
        if (gameState == playState){
            //player
            player.update();
            //npcs
            for (int i = 0; i < npc.length; i++){
                if (npc[i] != null){
                    npc[i].update();
                }
            }
            for (int i = 0; i < monster.length; i++){
                if (monster[i] != null){
                    if (monster[i].alive == true && monster[i].dying == false){
                        monster[i].update();
                    }
                    if (monster[i].alive == false){
                        monster[i] = null;
                    }
                }
            }
        }
        if (gameState == pauseState){
            //nothing
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        //DEBUG
        long drawStart = 0;
        if (keyH.checkDrawTime == true){
            drawStart = System.nanoTime();
        }

        //TITLE SCREEN

        if(gameState == titleState){
            ui.draw(g2);
        }
        //OTHERS
        else {
            // TILE
            tileM.draw(g2);

            //ADD ENTITIES TO THE LIST
            entityList.add(player);

            for (int i = 0; i < npc.length; i++){
                if (npc[i] != null){
                    entityList.add(npc[i]);
                }
            }
            for (int i = 0; i < obj.length; i++){
                if (obj[i] != null){
                    entityList.add(obj[i]);
                }
            }
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    entityList.add(monster[i]);
                }
            }

            //SORT
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    return Integer.compare(e1.worldY, e2.worldY);
                }
            });

            //DRAW ENTITIES 
            for(int i = 0; i < entityList.size(); i++){
                entityList.get(i).draw(g2);
            }
            //EMPTY ENTITY LIST
            for(int i = 0; i < entityList.size(); i++){
                entityList.clear();
            }

            //UI
            ui.draw(g2);
        }

        //DEBUG
        if (keyH.checkDrawTime == true){
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw Time: " + passed, 10, 400);
            System.out.println("Draw Time: " + passed);
        }

        g2.dispose();
    }

    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic(){
        music.stop();
    }

    public void playSE(int i){
        se.setFile(i);
        se.play();
    }

}
