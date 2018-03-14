import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Board extends JPanel implements ActionListener {

    Bounds bounds;
    Player player;
    Timer timer;
    int fillX = 290, fillY=290, drawX = 290, drawY = 290, ticks = 0;

    private long lastMoment, currentMoment;

    public Board() {
        setPreferredSize(new Dimension(600, 600));
        setBackground(Color.black);
        timer = new Timer(1000 / 60, this);


    }

    public void start(){
        bounds = new Bounds(Color.white, 600 ,getWidth(), getHeight());
        player = new Player(Color.red, getWidth()/2, getHeight()/2, 40, bounds);
        timer.start();
        lastMoment = System.currentTimeMillis();
    }


    public void setPlayerPos(int x, int y){
        if(checkCircleCollision(x,y)) {
            fillX = x;
            fillY = y;
        }
        drawX = x;
        drawY = y;
    }

    public boolean checkCircleCollision(int x, int y){
        int xDif = x+10 - 300;
        int yDif = y+10 - 300;
        int radii = 10 + 200;
        return( ( xDif * xDif )  + ( yDif  * yDif ) < radii * radii);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        bounds.paint(g);
        g.setColor(Color.white);
        player.paint(g);
        //g.drawOval(100,100,400,400);
        //g.fillOval(fillX,fillY,20,20);
        //g.drawOval(drawX,drawY, 20,20);

    }

    @Override
    public void actionPerformed(ActionEvent e) {ticks++; if(ticks%60==0)bounds.decreaseSize(); repaint(); }

}