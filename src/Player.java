import java.awt.*;

public class Player extends Entity {
    int drawX, drawY;
    Bounds bounds;
    public Player(Color color, int x, int y, int diameter, Bounds bounds){
        super(color, x, y, diameter, diameter);
        drawX = x;
        drawY = y;
        this.bounds = bounds;
    }

    public void move(int x,int y){
        if(bounds.checkCircleCollision(this, x-width/2, y-height)) {
            this.x = x-width/2;
            this.y = y-height;
        }
        drawX = x-width/2;
        drawY = y-height;
    }



    @Override
    public void paint(Graphics g){
        g.fillOval(x, y, width, height);
        g.drawOval(drawX,drawY,width,height);
    }
}
