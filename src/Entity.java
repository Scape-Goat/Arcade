import java.awt.*;
import java.util.List;

public class Entity implements Move, Paint {

    //region Colors
    Color red = new Color(255,0,0);
    Color orange = new Color(255,127,0);
    Color yellow = new Color(255,255,0);
    Color lime = new Color(127,255,0);
    Color green = new Color(0,255,0);
    Color teal = new Color(0,255,127);
    Color cyan = new Color(0,255,255);
    Color lightBlue = new Color(0,127,255);
    Color blue = new Color(0,0,255);
    Color[] colors = {red,orange,yellow,lime,green,teal,cyan, lightBlue,blue};
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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
