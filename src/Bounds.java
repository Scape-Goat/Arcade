import java.awt.*;

public class Bounds {

    Color color;
    int diameter, boardWidth, boardHeight, x = 0, y=0;
    public Bounds(Color color, int diameter, int boardWidth, int boardHeight){
        this.color = color;
        this.diameter = diameter;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        x = (boardWidth/2)-(diameter/2);
        y = (boardHeight/2)-(diameter/2);
    }

    public boolean checkCircleCollision(Entity player){
        int xDif = player.getX()+player.getWidth()/2 - boardWidth/2;
        int yDif = player.getY()+player.getHeight()/2 - boardHeight/2;
        int radii = player.getWidth()/2 + diameter/2;
        return( ( xDif * xDif )  + ( yDif  * yDif ) < radii * radii);
    }

    public void paint(Graphics g){
        g.setColor(color);
        g.drawOval(x,y,diameter, diameter);
    }

}
