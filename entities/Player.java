package entities;

import utilz.LoadSave;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utilz.Constants.PlayerConstants.*;

public class Player extends Entity{
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 20;
    private int playerAction = IDLE;
    private boolean left, up, right, down;
    private float playerSpeed = 2.0f;
    private boolean moving = false, attacking = false;


    public Player(float x, float y) {
        super(x, y);
        loadAnimations();
    }

    public void update(){
        updatePos();
        updateAniTick();
        setAnimation();
    }

    public void render(Graphics g){
        g.drawImage(animations[playerAction][aniIndex], (int)x, (int)y, 90, 128, null);

    }
    private void updateAniTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)) {
                aniIndex = 0;
                attacking = false;
            }
        }
    }

    private void setAnimation() {
        int startAni = playerAction;
        if(moving){
            playerAction = RUNNING;
        }
        else{
            playerAction = IDLE;
        }
        if(attacking){
            playerAction = ATTACK;
        }
        if (startAni != playerAction){
            resetAniTick();
        }
    }

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    private void updatePos() {
        moving = false;

        if(left && !right){
            x -= playerSpeed;
            moving = true;
        }
        else if(right && !left){
            x += playerSpeed;
            moving = true;
        }

        if(up && !down){
            y -= playerSpeed;
            moving = true;
        }
        else if(down && !up){
            y += playerSpeed;
            moving = true;
        }

    }

    private void loadAnimations() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PlAYER_ATLAS);

        animations = new BufferedImage[7][3];
        for(int j = 0; j < animations.length; j++){
            for(int i = 0; i < animations[j].length; i++){
                animations[j][i] = img.getSubimage(i*60, j*65, 50, 64);
            }
        }
    }

    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isAttacking() {
        return attacking;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }
}
