import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.awt.*;

public class Enemy extends Entity {
    public Enemy(int width, int height, int maxSpeed){
        super(Color.red,300, 300, width,height);
        this.MAXSPEED = maxSpeed;
        randomPos();

    }

    public void randomPos(){

        System.out.println(x + " " + y);
        setMove();
    }



    public void setMove(){
        double angle = Math.atan((double)y/x);
        dx = (MAXSPEED*Math.sin(angle));
        dy = (MAXSPEED*Math.cos(angle));
        if(x>300)
            dx*=-1;
        if(y>300)
            dy*=-1;
        System.out.println("DX: " + dx + " DY: " + dy);
    }

    @Override
    public void paint(Graphics g){
        g.fillOval(x,y,width,height);
        g.drawLine(300,300, x+width/2,y+height/2);
        g.drawLine(300,300, x+width/2, 300);
        g.drawLine(x+width/2,300, x+width/2, y+height/2);

    }




}
