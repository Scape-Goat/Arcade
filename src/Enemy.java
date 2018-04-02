import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.awt.*;

public class Enemy extends Entity {
    Bounds bounds;
    int boardWidth, boardHeight;
    public Enemy(int width, int height, int maxSpeed, Bounds bounds, int boardWidth, int boardHeight ){
        super(300, 300, width,height);
        this.MAXSPEED = maxSpeed;
        this.bounds = bounds;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        randomPos();
    }
    public void randomPos(){
        this.x = (int)(Math.random()*(boardWidth-width/2));
        this.y = (int)(Math.random()*(boardHeight-height/2));
        if(bounds.checkCircleCollision(this, x, y))
            randomPos();
        setMove();
    }
    public void setMove() {
        if (MAXSPEED != 0) {
            this.dx = (int) (Math.random() * (MAXSPEED - 3)) + 3;
            this.dy = (int) (Math.random() * (MAXSPEED - 3)) + 3;
        }
    }
}
