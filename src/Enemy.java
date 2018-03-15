import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.awt.*;

public class Enemy extends Entity {
    public Enemy(int width, int height, int maxSpeed){
        super(Color.red,300, 300, width,height);
        this.MAXSPEED = maxSpeed;
        randomPos();
        setMove();
    }

    public void randomPos(){
        this.x = 300-(int)(Math.random()*300);
        this.y = 300-(int)(Math.random()*300);
        System.out.println(x + " " + y);
    }



    public void setMove(){
        double angle = Math.atan((double)x/y);
        this.dx = (int)(MAXSPEED*Math.cos(angle));
        this.dy = (int)(MAXSPEED*Math.sin(angle));
        System.out.println("DX: " + dx + " DY: " + dy);
    }




}
