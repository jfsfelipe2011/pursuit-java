package com.zegames.entities;

import com.zegames.main.Game;
import com.zegames.world.AStar;
import com.zegames.world.Camera;
import com.zegames.world.Vector2i;
import com.zegames.world.World;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Enemy extends Entity{
    public boolean ghostMode = false;
    public int ghostFrames = 0;
    public int nextTime = Entity.rand.nextInt((60 * 20 - 60 * 10) + 60 * 10);

    public Enemy(int x, int y, int width, int height,int speed, BufferedImage sprite) {
        super(x, y, width, height,speed,sprite);
    }

    public void tick(){
        depth = 0;

        if (!ghostMode) {
            if(path == null || path.size() == 0) {
                Vector2i start = new Vector2i(((int)(x/16)),((int)(y/16)));
                Vector2i end = new Vector2i(((int)(Game.player.x/16)),((int)(Game.player.y/16)));
                path = AStar.findPath(Game.world, start, end);
            }

            if(new Random().nextInt(100) < 80)
                followPath(path);

            if(x % 16 == 0 && y % 16 == 0) {
                if(new Random().nextInt(100) < 10) {
                    Vector2i start = new Vector2i(((int)(x/16)),((int)(y/16)));
                    Vector2i end = new Vector2i(((int)(Game.player.x/16)),((int)(Game.player.y/16)));
                    path = AStar.findPath(Game.world, start, end);
                }
            }

            if (isColidding(this, Game.player)) {
                World.restartGame("/level1.png");
            }
        }

        ghostFrames++;

        if (ghostFrames == nextTime) {
            nextTime = Entity.rand.nextInt((60 * 20 - 60 * 10) + 60 * 10);
            ghostFrames = 0;

            ghostMode = !ghostMode;
        }
    }

    public void render(Graphics g) {
        if (!ghostMode) {
            super.render(g);
        } else {
            g.drawImage(Entity.ENEMY_GHOST,this.getX() - Camera.x,this.getY() - Camera.y,null);
        }
    }
}
