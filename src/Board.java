import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Board extends JPanel implements ActionListener {

    Bounds bounds;
    Player player;
    List<Entity> entities = new ArrayList<>();
    Timer timer;
    int entityIndex = 0;
    int fillX = 290, fillY=290, drawX = 290, drawY = 290, ticks = 0;

    private long lastShot, currentShot;

    public Board() {
        setPreferredSize(new Dimension(600, 600));
        setBackground(Color.black);
        timer = new Timer(1000 / 60, this);


    }

    public void start(){
        entityIndex = 50;
        bounds = new Bounds(Color.white, 560 ,getWidth(), getHeight());
        player = new Player(Color.red, getWidth()/2, getHeight()/2, 20, bounds);


        for(int i = 0; i<entityIndex; i++) {
            entities.add(new Enemy(20, 20, 5, bounds, getWidth(), getHeight()));
        }
        timer.start();
        lastShot = System.currentTimeMillis();
    }


    public void paintComponent(Graphics g){
        if(ticks>0) {
            super.paintComponent(g);

            bounds.paint(g);
            g.setColor(Color.white);

            for(Entity entity: entities)
                entity.paint(g);
            //for(Bullet bullet: bullets)
                //bullet.paint(g);
            //g.drawOval(100,100,400,400);
            //g.fillOval(fillX,fillY,20,20);
            //g.drawOval(drawX,drawY, 20,20);

            player.paint(g);
        }
    }

    public void checkCollision(){
        for(int i = 0; i<entities.size(); i++){
            if(entities.get(i).getBounds().intersects(player.getBounds())&& entities.get(i).getColor().equals(player.getColor())) {
                System.out.println("Hit");
                entities.remove(entities.get(i));
                break;
            }
            for(int j = 0; j<entities.size()-1; j++){
                if(i!=j&& entities.get(i).getBounds().intersects(entities.get(j).getBounds())&& entities.get(i).getColor().equals(entities.get(j).getColor())){
                    if(entities.get(i) instanceof Bullet && entities.get(j) instanceof Enemy  ) {
                        entities.remove(entities.get(i));
                        entities.get(j).setRemove(true);
                        break;
                    }

                }


            }

        }
        update();
    }

    public void update(){
        for(int i = 0; i<entities.size(); i++){
            if(entities.get(i).remove)
                entities.remove(entities.get(i));

        }
    }




    public void actionPerformed(ActionEvent e) {
            checkCollision();
            currentShot = System.currentTimeMillis();
            ticks++;
        if(ticks>4) {
            if (ticks % 180 == 0 && bounds.diameter>player.getWidth()) {
                bounds.decreaseSize();
                player.randomColor();
                for(Entity entity: entities)
                    entity.randomColor();

                /// /for(int i = 0; i<entityIndex; i++) {
                    //enemies[i].randomColor();
                //}
            }
            player.move();

            //for (int i = 0; i < entityIndex; i++) {
                //if(enemies[i]!=null)
                //enemies[i].move(getWidth(), getHeight());
            //}

            //for(Bullet bullet: bullets)
                //bullet.move(getWidth(), getHeight());

            for(Entity entity: entities)
                entity.move(getWidth(), getHeight());

            if (Game.isShoot() && currentShot - lastShot > 250) {
                lastShot = System.currentTimeMillis();

                player.Attack(entities);

            }

        }
        repaint();

    }

}