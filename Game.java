import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable, KeyListener {

  public static final int WIDTH = 600;
  public static final int HEIGHT = 600;
  public int count = 0;
  public static Player player;
  public static PlayerBlue playerBlue;
  public static Ball ball;
  public static String advantage = "red";
  public BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

  public Game() {
    this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    this.addKeyListener(this);
    this.setFocusable(true);    
  }
  
  public void update() {
    player.update();
    playerBlue.update();
    ball.update();    
  }
  
  public void render() {

    BufferStrategy bs = this.getBufferStrategy();

    if (bs == null) {
      this.createBufferStrategy(3);
      return;
    }

    Graphics g = layer.getGraphics();
    Graphics2D g2 = (Graphics2D) g;
    g.setColor(Color.BLACK);
    g.fillRect(0,0, WIDTH, HEIGHT);
    g.clearRect(0, 0, WIDTH, HEIGHT);    
    player.draw(g2);
    playerBlue.draw(g2);
    ball.render(g);
    
    g = bs.getDrawGraphics();
    g.drawImage(layer, 0, 0, null);
    bs.show();
  }

  @Override
  public void run() {
    while (true) {
      render();
      update();
      System.out.println("ooo");
      try {
        Thread.sleep(1000 / 60);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {}

  @Override
  public void keyPressed(KeyEvent e) {

    if (e.getKeyCode() == KeyEvent.VK_W) {
      player.up = true;
    }

    if (e.getKeyCode() == KeyEvent.VK_UP) {
      playerBlue.up = true;
    }

    if (e.getKeyCode() == KeyEvent.VK_S) {
      player.down = true;
    }

    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      playerBlue.down = true;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_W) {
      player.up = false;
    }

    if (e.getKeyCode() == KeyEvent.VK_UP) {
      playerBlue.up = false;
    }

    if (e.getKeyCode() == KeyEvent.VK_S) {
      player.down = false;
    }

    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      playerBlue.down = false;
    }
  }

  public static void main(String[] args) {
    Game game = new Game();
    JFrame jf = new JFrame();
    ball = new Ball();
    player = new Player();
    playerBlue = new PlayerBlue();
    jf.setTitle("Game Java");
    jf.setVisible(true);
    jf.add(game);
    jf.pack();
    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jf.setResizable(false);
    jf.setLocationRelativeTo(null);
    new Thread(game).start();
  }
}