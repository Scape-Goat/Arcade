import java.awt.*;

public class Entity {
    Color color;
    int x, y, width, height;

    public Entity(Color color, int x, int y, int width, int height){
        this.color = color;
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

    public void paint(Graphics g){
        g.fillOval(x,y,width,height);
    }
}
