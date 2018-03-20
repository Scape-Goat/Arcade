import java.awt.*;
import java.util.List;

public interface Move {

    public void move( int boardWidth, int boardHeight);


    public Rectangle getBounds();
}
