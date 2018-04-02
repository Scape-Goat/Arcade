import java.awt.*;
import java.util.List;

public class Entity implements Move, Paint {

    //region Colors
    Color white = new Color(255,255,255);
    Color red = new Color(255,80,80);
    Color magenta = new Color(255, 0, 255);
    Color purple = new Color(153, 102, 255);
    Color blue = new Color(51, 102, 255);
    Color turquoise = new Color(0, 255, 153);
    Color green = new Color(51, 204, 51);
    Color yellow = new Color(255, 255, 0);
    Color orange = new Color(255, 153, 51);
    Color[] colors = {white, red, magenta, purple, blue, turquoise, green, yellow, orange};
    //endregion



    Color color;
    int x, y, width, height, MAXSPEED = 5;
    double  dx, dy;
    boolean remove =false;

    public Entity(int x, int y, int width, int height){
        this.color = colors[(int)(Math.random()*colors.length)];
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public void paint(Graphics g) {
        if (!remove) {
            g.setColor(color);
            g.fillOval(x, y, width, height);
        }
    }
    @Override
    public void setPosition(int x, int y) {

    }
    public void randomColor(){
        color = colors[(int)(Math.random()*colors.length)];
    }
    @Override
    public void move(int boardWidth, int boardHeight) {

        x+=dx;
        if (x+width/2 >= boardWidth || x+width/2<=0)
            dx*=-1;
        y+=dy;
        if (y+height/2 >= boardHeight || y+height/2<=0)
            dy*=-1;
    }
    public Color getColor() {
        return color;
    }
    public void setRemove(boolean remove) {
        this.remove = remove;
    }
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,width,height);
    }
}
