package VisualisasiAlgoritma;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.util.HashSet;
import java.util.concurrent.Delayed;
import javafx.scene.transform.Affine;
import javax.swing.JPanel;

public class Surface extends JPanel implements Runnable, KeyListener, MouseListener,MouseMotionListener,MouseWheelListener {

    private final static int MAX_FPS = 60;
    private final static int MAX_FRAME_SKIPS = 10;
    private final static int FRAME_PERIOD = 1000 / MAX_FPS;
    volatile boolean running = true;
    Thread thread;
    
    //Keyboard
    HashSet <Integer> keyboard = new HashSet<>();
    //Translate and Scale
    double translateX;
    double translateY;
    double scale;
    
    //Mouse Listener
    private int lastOffsetX;
    private int lastOffsetY;
    
    //Variables
    int cellSize = 40;
    int x0,y0;
    int vx = 3;
    int vy = 3;

    public Surface() {
        translateX = 0;
        translateY = 0;
        scale = 1;
        setOpaque(false);
        setDoubleBuffered(true);
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
        setFocusable(true);
    }

    @Override
    public void addNotify() {
        super.addNotify();
        thread = new Thread(this);
        thread.start();

    }

    private void update() {
//        x0++;
//        y0++;

        if (keyboard.contains(KeyEvent.VK_RIGHT)) {
           x0 += vx; 
           
        }
        if (keyboard.contains(KeyEvent.VK_LEFT)) {
           x0 -= vx; 
           
        }
        if (keyboard.contains(KeyEvent.VK_DOWN)) {
           y0 += vy; 
           
        }
        if (keyboard.contains(KeyEvent.VK_UP)) {
           y0 -= vy; 
           
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g.create();
        draw(g2d);

    }

    private void draw(Graphics2D g2d) {
        AffineTransform tx = new AffineTransform();
        tx.translate(translateX, translateY);
        tx.scale(scale, scale);
        g2d.setTransform(tx);
       
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);
        
        g2d.setColor(Color.decode("#2c3e50"));
        g2d.fillRect(0, 0, getWidth(), getHeight());

        //Gambar grid
        g2d.setColor(Color.red);
        int min_x = 0;
        int max_x = getWidth();
        int min_y = 0;
        int max_y = getHeight();

        int k = min_x;

        //draw line vertical
        while (k < max_x) {
            g2d.drawLine(k, min_y, k, max_y);
            k += cellSize;
        }
        k = min_y;

        //draw line vertical
        while (k < max_y) {
            g2d.drawLine(min_x, k, max_x, k);
            k += cellSize;
        }
        g2d.setColor(Color.red);
        g2d.drawOval(x0 - 20, y0 - 20, 40, 40);

        g2d.dispose();
    }

    private void Delay(long sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void run() {
        long beginTime;
        long timeDiff;
        int sleepTime = 0;
        int frameSkipped;

        while (running) {
            try {
                synchronized (this) {
                    beginTime = System.currentTimeMillis();
                    frameSkipped = 0;
                    update();
                    repaint();

                }
                timeDiff = System.currentTimeMillis() - beginTime;
                sleepTime = (int) (FRAME_PERIOD - timeDiff);
                if (sleepTime > 0) {
                    Delay(sleepTime);

                }
                while (sleepTime < 0 && frameSkipped < MAX_FRAME_SKIPS) {
                    update();
                    sleepTime += FRAME_PERIOD;
                    frameSkipped++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
     //
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Integer key = e.getKeyCode();
        keyboard.add(key);
   
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Integer key = e.getKeyCode();
        keyboard.remove(key);
  
    }

    @Override
    public void mouseClicked(MouseEvent e) {
  
    }

    @Override
    public void mousePressed(MouseEvent e) {
        lastOffsetX = e.getX();
        lastOffsetY = e.getY();
   
    }

    @Override
    public void mouseReleased(MouseEvent e) {
  
    }

    @Override
    public void mouseEntered(MouseEvent e) {
   
    }

    @Override
    public void mouseExited(MouseEvent e) {
  
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int newX = e.getX() - lastOffsetX;
        int newY = e.getY() - lastOffsetY;
        
        lastOffsetX += newX;
        lastOffsetY += newY;
        
        translateX += newX;
        translateY += newY;
        
        repaint();
   
    }

    @Override
    public void mouseMoved(MouseEvent e) {
   
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
            scale += (.1 * e.getWheelRotation());
            scale = Math.max(0.00001, scale);
            repaint();
            
        }
   
    }

}
