import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class Game extends JFrame{

    Board board;

    static boolean up=false,left = false,right = false,down = false, shoot = false;

    public Game(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setTitle("SHAPES EXTREME");
        board = new Board();
        add(board);
        pack();
        setLocationRelativeTo(null);

        //region Listeners
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                //board.setPlayerPos(e.getX(), e.getY());
                board.player.moveTarget(e.getX(), e.getY());
            }


        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                setCursor(getToolkit().createCustomCursor(new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0,0), null));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                board.player.moveTarget(e.getX(), e.getY());

            }
        });

        addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP){
                    up = true;
                }

                if(e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN){
                    down = true;
                }

                if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT){
                    left = true;
                }

                if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT){
                    right = true;
                }

                if(e.getKeyCode() == KeyEvent.VK_SPACE){
                    shoot = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP){
                    up = false;
                }

                if(e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN){
                    down = false;
                }

                if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT){
                    left = false;
                }

                if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT){
                    right = false;
                }

                if(e.getKeyCode() == KeyEvent.VK_SPACE){
                    shoot = false;
                }
            }
        });
        //endregion
    }

    public static void main(String[] args){
        Game game = new Game();
        game.board.start();
    }

    public static boolean isUp() {
        return up;
    }
    public static boolean isLeft() {
        return left;
    }
    public static boolean isRight() {
        return right;
    }
    public static boolean isDown() {
        return down;
    }
    public static boolean isShoot() {
        return shoot;
    }
}
