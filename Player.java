import java.awt.Color;
import java.awt.Graphics;

public class Player {
  public static final int WIDTH = 15;
  public static final int HEIGHT = 100;
  public static int x = 10;
  public static int y = 130;//(Game.HEIGHT/2) - HEIGHT/2;
  public boolean up = false;
  public boolean down = false;

  public Player() {}

  public void update() {
        
    if(y > 0) {
      if (this.up) {
        this.y -= 10;
      }
    }

    if((y+HEIGHT) < Game.WIDTH) {
      if (this.down) {
        this.y += 10;
      }
    }

  }

  public void renderR(Graphics g) {
    g.setColor(Color.RED);
    g.fillRect(x, y, WIDTH, HEIGHT);
  }

}