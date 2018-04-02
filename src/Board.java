import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Board extends JPanel implements ActionListener {
    Bounds bounds;
    Player player;
    List<Entity> entities = new ArrayList<>();
    Timer timer;
    int entityIndex = 5, ticks = 0;
    private long lastShot, currentShot;
    public Board() {
        setPreferredSize(new Dimension(600, 600));
        setBackground(Color.black);
        timer = new Timer(1000 / 60, this);


    }
    public void start(){
        if(bounds == null)
        bounds = new Bounds(Color.white, getWidth()-40 ,getWidth(), getHeight());
        player = new Player(Color.red, getWidth()/2, getHeight()/2, 20, bounds);
        while(entities.size()>0)
                entities.remove(0);
        for(int i = 0; i<entityIndex; i++) {
            entities.add(new Enemy(20, 20, 5, bounds, getWidth(), getHeight()));
        }
        System.out.println(entities.size());
        timer.restart();
        ticks = 0;
        lastShot = System.currentTimeMillis();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(STATS.isPlay()) {
            if(bounds!=null)
            bounds.paint(g);
            g.setColor(Color.white);
            player.paint(g);
            for(Entity entity: entities)
                entity.paint(g);

        }
        else if(STATS.isMenu()){
            g.setFont(new Font("Serif", Font.BOLD, 72));
            printRainbowString("Color Collision", getWidth(), 0, getHeight()/3, g);
            g.setFont(new Font("Serif", Font.BOLD, (int)(72/1.5)));
            printRainbowString("Press Enter to Start", getWidth(), 0 , (int)(getHeight()*(2.0/3)), g);
        }
        else if(STATS.isInstructions()){
            g.setFont(new Font("Serif", Font.BOLD, (int)(72/1.75)));
            printRainbowString("Collect circles of the same color", getWidth(), 0, (int)(getHeight()*(1.0/8)), g);
            printRainbowString("Avoid circles of different colors", getWidth(), 0, (int)(getHeight()*(3.0/8)), g);
            printRainbowString("Move with W,A,S,D or Arrows", getWidth(), 0, (int)(getHeight()*(4.0/8)), g);
            printRainbowString("Press space to shoot", getWidth(), 0, (int)(getHeight()*(5.0/8)), g);
            printRainbowString("Press enter to continue", getWidth(), 0, (int)(getHeight()*(7.0/8)), g);
        }
        else if(STATS.isPause()){
            g.setFont(new Font("Serif", Font.BOLD, (72)));
            printRainbowString("PAUSED", getWidth(), 0, (int)(getHeight()*(4.0/8)), g);
        }
        else if(STATS.isEnd()){
            g.setFont(new Font("Serif", Font.BOLD, (50)));
            printRainbowString("GAME OVER", getWidth(), 0, (int)(getHeight()*(1.0/8)), g);
            printRainbowString("Wave Number: " + STATS.getLevel(), getWidth(), 0, (int)(getHeight()*(4.0/8)), g);
            printRainbowString("Press Enter to play again", getWidth(), 0, (int)(getHeight()*(7.0/8)), g);
        }
    }
    public void checkCollision(){
        for(int i = 0; i<entities.size(); i++){
            if(entities.get(i).getBounds().intersects(player.getBounds()) && entities.get(i) instanceof Enemy ){
                if(entities.get(i).getColor().equals(player.getColor()) ) {
                    entities.remove(entities.get(i));
                    break;
                }
                else{
                    player.hit( this);
                    break;
                }
            }
            for(int j = 0; j<entities.size()-1; j++){
                if(i!=j&& entities.get(i).getBounds().intersects(entities.get(j).getBounds())&& entities.get(i).getColor().equals(entities.get(j).getColor())){
                    if(entities.get(i) instanceof Bullet && entities.get(j) instanceof Enemy  ) {
                        entities.remove(entities.get(i));
                        entities.get(j).setRemove(true);
                        player.decreaseBulletCount();
                        break;
                    }
                }
            }
        }
        update();
    }
    public void update() {
        int enemyCount = 0;
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).remove)
                entities.remove(entities.get(i));
        }
        for(Entity entity: entities)
            if(entity instanceof Enemy)
                enemyCount+=1;
        if(enemyCount == 0) {
            entityIndex += 5;
            STATS.increaseLevel();
            start();
        }
    }
    public void actionPerformed(ActionEvent e) {
        if (STATS.isPlay()) {
            checkCollision();
            currentShot = System.currentTimeMillis();
            ticks++;
            if (ticks > 60) {
                if (ticks % (180 * 5) == 0 && bounds.diameter > player.getWidth()) {
                    bounds.decreaseSize();
                    ticks = 60;
                }

                if (ticks % (60 * 5) == 0) {
                    player.randomColor();
                    for (Entity entity : entities)
                        if (!(entity instanceof Bullet))
                            entity.randomColor();
                }
                player.move();
                for (Entity entity : entities)
                    entity.move(getWidth(), getHeight());
                if (Game.isShoot() && currentShot - lastShot > 1000) {
                    lastShot = System.currentTimeMillis();
                    player.Attack(entities);
                }
            }
        }
        repaint();
    }
    private void printRainbowString(String s, int width, int XPos, int YPos, Graphics g2d){

        Color red = new Color(255,80,80);
        Color magenta = new Color(255, 0, 255);
        Color purple = new Color(153, 102, 255);
        Color blue = new Color(51, 102, 255);
        Color turquoise = new Color(0, 255, 153);
        Color green = new Color(51, 204, 51);
        Color yellow = new Color(255, 255, 0);
        Color orange = new Color(255, 153, 51);
        Color[] colors = {red, magenta, purple, blue, turquoise, green, yellow, orange};

        int stringLen = (int)g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();
        int start = width/2 - stringLen/2;

        String[] letters = new String[s.length()];
        for(int i = 0; i<letters.length; i++)
            letters[i] = s.substring(i,i+1);
        for(int i = 0; i<letters.length; i++) {
            g2d.setColor(colors[i%colors.length]);
            g2d.drawString(letters[i], start + XPos + (int) g2d.getFontMetrics().getStringBounds(s.substring(0, i), g2d).getWidth(), YPos  + (int)(g2d.getFontMetrics().getStringBounds(s, g2d).getHeight()/4));
        }
//

    }



}