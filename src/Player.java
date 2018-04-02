import java.awt.*;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

public class Player extends Entity {
    int drawX, drawY, spawnX, spawnY;
    Bounds bounds;
   double dx = 0, dy = 0;
    int bulletCount = 0;

    public Player(Color color, int x, int y, int diameter, Bounds bounds) {
        super(x - diameter / 2, y - diameter / 2, diameter, diameter);
        drawX = x - diameter / 2;
        drawY = y - diameter / 2;
        spawnX = drawX;
        spawnY = drawY;
        this.bounds = bounds;
        this.MAXSPEED = 2;

    }

    public void moveTarget(int x, int y) {
         drawX = x - width / 2;
        drawY = y - height;
    }


    public void move() {

        if (bounds.checkCircleCollision(this, x, y)) {

            this.dx = 5;
            this.dy = 5;

            if (Game.isUp() && bounds.checkCircleCollision(this, x, y - dy))
                y -= dy;
            if (Game.isDown() && bounds.checkCircleCollision(this, x, y + dy))
                y += dy;
            if (Game.isLeft() && bounds.checkCircleCollision(this, x - dx, y))
                x -= dx;
            if (Game.isRight() && bounds.checkCircleCollision(this, x + dx, y))
                x += dx;
        } else {
            double angle = Math.atan2(Math.abs(310 - (y+10)), Math.abs(310 - (x+10)));
            dx = MAXSPEED * Math.cos(angle);
            dy = MAXSPEED * Math.sin(angle);
            if (300 - y < 0) {
                dy *= -1;
            }
            if (300 - x < 0) {
                dx *= -1;
            }
            y += dy;
            x += dx;
        }
    }
    public void Attack(List<Entity> entities) {
        bulletCount+=1;
        if(bulletCount>5)
        for(Entity entity: entities)
            if(entity instanceof Bullet) {
                entities.remove(entity);
                bulletCount-=1;
                break;
        }
        entities.add(new Bullet(x + width / 2 - 5, y + width / 2 - 5, 10, 10, drawX + width / 2, drawY + width / 2, color));
    }
    public void hit( Board board) {
        STATS.decreaseLives(board);
        x = spawnX;
        y = spawnY;

    }
    public void decreaseBulletCount(){
        bulletCount -=1;
    }
    @Override
    public void paint(Graphics g){
        g.setColor(color);
        g.fillOval(x, y, width, height);
        g.setColor(Color.black);
        g.setFont(new Font("Serif", Font.BOLD, (int)(20/1)));
        printCenteredString(""+STATS.getLives(), width, x, y+height/2, g);
        g.setColor(Color.pink);
        g.drawOval(drawX,drawY,width,height);
    }
    private void printCenteredString(String s, int width, int XPos, int YPos, Graphics g2d){
        int stringLen = (int)g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();
        int stringHeight = (int)g2d.getFontMetrics().getStringBounds(s, g2d).getHeight();
        int start = width/2 - stringLen/2;
        g2d.drawString(s, start + XPos, YPos + stringHeight/4);
    }
}
