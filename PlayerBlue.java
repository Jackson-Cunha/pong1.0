import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PlayerBlue {
  public static final int WIDTH = 15;
  public static final int HEIGHT = 100;
  public static int x = Game.WIDTH - (WIDTH + 10);
  public static int y = 130;
  public boolean up = false;
  public boolean down = false;
  public BufferedImage image;

  public PlayerBlue() {
    try {
      image = ImageIO.read(getClass().getResourceAsStream("/res/blue.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

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

  public void draw(Graphics2D g2) {
    g2.drawImage(image, x, y, WIDTH, HEIGHT, null);
  }

}