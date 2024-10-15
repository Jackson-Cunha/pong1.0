import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

public class Ball {

  public int WIDTH = 20;
  public int HEIGHT = 20;
  public double x;
  public double y;
  public double dx;
  public double dy;
  public double speed = 5.7;
  public boolean showCont = false;
  public String count = "3";
  public int countRed = 0;
  public int countBlue = 0;
  public int direction = 0;
  public int position = 0;

  public Ball() {

    System.out.println("novo jogo");

    randomPositionBall();

    y = Player.y + (Player.HEIGHT / 2) - HEIGHT / 2;
    x = position;

    int angle = new Random().nextInt(10) - 2 - 1;

    dx = Math.cos(Math.toRadians(angle));
    dy = Math.sin(Math.toRadians(angle));

  }

  public void randomPositionBall() {
    var p = new Random().nextInt(2);
    if (p == 0) {
      position = 30;
    } else {
      position = (Game.WIDTH - 60);
    }
  }

  public void countReboot() {

    try {
      Thread.sleep(1000 / 1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    var c = Integer.parseInt(count);
    c--;
    count = String.valueOf(c);

  if (count.equals("-1")) {
   showCont = false;
   count = "3";
   }

  }

  public void task_wait() {

    if ((x + WIDTH) > Game.WIDTH) {
      countRed++;
      y = Player.y + (Player.HEIGHT / 2) - HEIGHT / 2;
      x = 30;
      showCont = true;
      System.out.println("1 " + showCont);
      new Game();
      return;
    }

    if (x < 0) {
      countBlue++;
      y = PlayerBlue.y + (PlayerBlue.HEIGHT / 2) - HEIGHT / 2;
      x = Game.WIDTH - 50;
      showCont = true;
      System.out.println("2 " + showCont);

      new Game();
      return;
    }
    
  }

  public void update() {

    if (!showCont) {

      x += dx * speed;
      y += dy * speed;

      task_wait();

      if (x + WIDTH > PlayerBlue.x && y + HEIGHT >= PlayerBlue.y && y <= PlayerBlue.y + PlayerBlue.HEIGHT) {
        int angle = new Random().nextInt(1) + 2 + 1;
        dx = Math.cos(Math.toRadians(angle));
        dy = Math.sin(Math.toRadians(angle));
        if (dy > 0) {
          dy *= -1;
          dx *= -1;
        }
      }

      if (x < (Player.x + Player.WIDTH) && (y + HEIGHT) >= Player.y && y <= Player.y + Player.HEIGHT) {
        int angle = new Random().nextInt(50) + 2 + 1;
        dx = Math.cos(Math.toRadians(angle));
        dy = Math.sin(Math.toRadians(angle));
        if (dy < 0) {
          dy *= 1;
          dx *= 1;
        }
      }

      if (y < 0) {
        dy *= -1;
      }

      if ((y + WIDTH) > Game.HEIGHT) {
        dy *= -1;
      }

    } else {

      // System.out.println(">><><><>");
      countReboot();

    }
  }

  public void render(Graphics g) {
    g.setColor(Color.WHITE);
    g.fillRect((int) x, (int) y, WIDTH, HEIGHT);

    g.setColor(Color.RED);
    g.setFont(new Font("Serif", Font.BOLD + Font.ITALIC, 20));
    g.drawString("RED : " + countRed, 20, 20);

    g.setColor(Color.BLUE);
    g.setFont(new Font("Serif", Font.BOLD + Font.ITALIC, 20));
    g.drawString("BLUE : " + countBlue, Game.WIDTH - 100, 20);

    if (showCont) {
      g.setColor(Color.WHITE);
      g.setFont(new Font("Serif", Font.BOLD + Font.ITALIC, 40));
      g.drawString(count, Game.WIDTH / 2, 40);
    }

  }

}
