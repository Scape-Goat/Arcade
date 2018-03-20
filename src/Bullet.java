import java.awt.*;
import java.util.List;
public class Bullet extends Entity {

    int targetX, targetY;
    double angle;

    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param targetX
     * @param targetY
     */
    public Bullet( int x, int y, int width, int height, int targetX, int targetY) {
        super( x, y, width, height);
        angle = Math.atan2(Math.abs(targetY-y), Math.abs(targetX-x));
        //System.out.println("Start X: " + x + " End X: " + targetX + " Start Y: "+ y + " End Y: "+ targetY + " Angle: " + Math.toDegrees(angle));

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
