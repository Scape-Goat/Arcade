import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Player extends Entity {
    int drawX, drawY, attackType;
    Bounds bounds;

    double dx = 5, dy = 5;

    public Player(Color color, int x, int y, int diameter, Bounds bounds){
        super(x-diameter/2, y-diameter/2, diameter, diameter);
        drawX = x-diameter/2;
        drawY = y-diameter/2;
        this.bounds = bounds;
        MAXSPEED = 2;
    }

    public void moveTarget(int x,int y){

        drawX = x-width/2;
        drawY = y-height;
    }


    public void move(){

        if(bounds.checkCircleCollision(this, x,y)) {
            dx = 5;
            dy = 5;

            if (Game.isUp() && bounds.checkCircleCollision(this, x, y - dy))
                y -= dy;
            if (Game.isDown() && bounds.checkCircleCollision(this, x, y + dy))
                y += dy;
            if (Game.isLeft() && bounds.checkCircleCollision(this, x - dx, y))
                x -= dx;
            if (Game.isRight() && bounds.checkCircleCollision(this, x + dx, y))
                x += dx;
        }
        else{
            double angle = Math.atan2(Math.abs(305-y), Math.abs(305-x));
            dx = MAXSPEED*Math.cos(angle);
            dy = MAXSPEED*Math.sin(angle);
            if(300-y<0){
                dy*=-1;
            }
            if(300-x<0){
                dx*=-1;
            }
            y+=dy;
            x+=dx;

        }
    }

    public void Attack(List<Entity> entities){
        entities.add(new Bullet( x+width/2-5, y+width/2-5, 10,10,drawX+width/2,drawY+width/2));
    }






    @Override
    public void paint(Graphics g){
        g.setColor(color);
        g.fillOval(x, y, width, height);
        g.setColor(Color.pink);
        g.drawOval(drawX,drawY,width,height);
    }
}
