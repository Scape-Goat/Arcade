import java.awt.*;
import java.util.ArrayList;
import java.util.List;
public class Bullet extends Entity {
    double angle;
    public Bullet( int x, int y, int width, int height, int targetX, int targetY, Color color) {
        super( x, y, width, height);
        this.color = color;
        angle = Math.atan2(Math.abs(targetY-y), Math.abs(targetX-x));
        dx = MAXSPEED*Math.cos(angle);
        dy = MAXSPEED*Math.sin(angle);
        if(targetY-y<0){
            dy*=-1;
        }
        if(targetX-x<0){
            dx*=-1;
        }

    }










}
