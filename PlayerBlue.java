import java.awt.Color;
import java.awt.Graphics;

public class PlayerBlue {
  public static final int WIDTH = 15;
  public static final int HEIGHT = 100;
  public static int x = Game.WIDTH - (WIDTH + 10);
  public static int y = 130;
  public boolean up = false;
  public boolean down = false;

  public PlayerBlue() {}

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

  public void render(Graphics g) {
    g.setColor(Color.BLUE);
    g.fillRect(x, y, WIDTH, HEIGHT);
  }

}