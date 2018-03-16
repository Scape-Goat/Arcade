import java.awt.*;

public class Player extends Entity {
    int drawX, drawY, attackType;
    Bounds bounds;

    int dx = 5, dy = 5;
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

    public void Attack(){
        switch(attackType){
            //normal bullet
            case 0: break;

            //laser - shoots a laser from player position to the mouse position
            case 1: break;

            //bouncing - if it touchs a wall it will bounce of in a 45 degree angle
            case 2: break;

            //multi shot - shoots 3-5 bullets at a time
            case 3: break;

            //grenade - if it hits an enemy or if it reaches a certain time limit it will explode in a scatter of bullets
            case 4: break;

            //ally - sends ally out to attack
            case 5: break;

            //shield - sets up an arc that if a enemy touches it they will bounce off but so will player bullets
            case 6: break;



        }

    }






    @Override
    public void paint(Graphics g){
        g.setColor(Color.blue);
        g.fillOval(x, y, width, height);
        g.setColor(Color.pink);
        g.drawOval(drawX,drawY,width,height);
    }
}
