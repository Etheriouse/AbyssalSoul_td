package Interface;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.GradientPaint;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

import Map.Game;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.TreeMap;
import java.util.TreeSet;

public class Window extends JFrame {

    public static int width = 1600;
    public static int height = 900;

    public static int x_offset = (int) Math.round((Window.width * 0.052083) / 10.0f) * 10;

    public static int xMouse = 0;
    public static int yMouse = 0;

    public static boolean Click = false;

    public static boolean fullscreen = false;

    public static boolean fenetrer_without_border = false;

    public static int divident_ts = 18;

    public static int Ts = (int) (height / divident_ts); // 90 60 for 1080p

    private static BufferedImage onscreenImage = new BufferedImage(2000, 2000, BufferedImage.TYPE_INT_ARGB);
    private static BufferedImage offscreenImage = new BufferedImage(2000, 2000, BufferedImage.TYPE_INT_ARGB);

    private static Graphics2D offscreen = offscreenImage.createGraphics();
    private static Graphics2D onscreen = onscreenImage.createGraphics();

    private static AffineTransform rotation = new AffineTransform();

    private static ImagePanel panel = new ImagePanel(onscreenImage);

    public static TreeSet<Integer> keysDown;
    private static TreeMap<Integer, Long> cooldown = new TreeMap<>();

    public int getwidth() {
        return width;
    }

    public int getheight() {
        return height;
    }

    public void setheight(int n) {
        height = n + this.getInsets().top;
        this.setSize(width, height);
    }

    public void setwidth(int n) {
        width = n;
        this.setSize(width, height);
    }

    public Window() {
        Setup();
    }

    public void run() {
        Game g = new Game();
        g.run();
    }


    private void Setup() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setIconImage(Texture.icon);
        this.setTitle("AbyssalSoul");
        if (fullscreen && true) {
            GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            gd.setFullScreenWindow(this); // Mettre le JFrame en plein écran

            height = Toolkit.getDefaultToolkit().getScreenSize().height;
            width = Toolkit.getDefaultToolkit().getScreenSize().width;
            Ts = (int) (height / divident_ts);
        }
        if (fenetrer_without_border) {
            this.setUndecorated(true); // Supprimer la barre de titre et les bordures
            height = Toolkit.getDefaultToolkit().getScreenSize().height;
            width = Toolkit.getDefaultToolkit().getScreenSize().width;
            Ts = (int) (height / divident_ts);
            this.setSize(width, height); // resize for get the top inset
            this.setVisible(true);
        } else {
            this.setVisible(true);
            this.setSize(width, height + this.getInsets().top); // resize for get the top inset
        }
        this.setContentPane(panel);
        this.setVisible(true);

        Cursor gauntletCursor = null;
        try {
            gauntletCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                    ImageIO.read(new File("assets/texture/cursor/hand.png")), new Point(0, 0), "gauntlet cursor");
        } catch (HeadlessException | IndexOutOfBoundsException | IOException e) {
            e.printStackTrace();
        }
        this.setCursor(gauntletCursor);
        keysDown = new TreeSet<Integer>();
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                keysDown.add(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                keysDown.remove(e.getKeyCode());
            }
        });
        int MarginTop = this.getInsets().top;
        this.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                xMouse = e.getX();
                yMouse = e.getY() - MarginTop;
            }

        });
        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                xMouse = e.getX();
                yMouse = e.getY() - MarginTop;

                changeCursor(true);
                Click = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                changeCursor(false);
                Click = false;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                xMouse = e.getX();
                yMouse = e.getY() - MarginTop;
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

        });


        cooldown.put(KeyEvent.VK_SPACE, System.currentTimeMillis());
        cooldown.put(KeyEvent.VK_ESCAPE, System.currentTimeMillis());
        cooldown.put(MouseEvent.BUTTON1, System.currentTimeMillis());

        File ancien_font = new File("assets/font/ancien.ttf");
        try {
            setStringFont(Font.createFont(0, ancien_font));
        } catch (FontFormatException e1) {
            System.out.println("Font format execption: " + e1);
            e1.printStackTrace();
        } catch (IOException e1) {
            System.out.println("IOException execption: " + e1);
            e1.printStackTrace();
        }
    }

    public static boolean cooldown(int key) {
        return System.currentTimeMillis()-cooldown.get(key) > 200;
    }

    public static void resetcooldown(int key) {
        cooldown.put(key, System.currentTimeMillis());
    }

    private void changeCursor(boolean clicked) {
        if (clicked) {
            Cursor gauntletCursor = null;
            try {
                gauntletCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                    ImageIO.read(new File("assets/texture/cursor/hand_clicked.png")), new Point(0, 0), "gauntlet cursor");
            } catch (HeadlessException | IndexOutOfBoundsException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            this.setCursor(gauntletCursor);
        } else {
            Cursor gauntletCursor = null;
            try {
                gauntletCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                    ImageIO.read(new File("assets/texture/cursor/hand.png")), new Point(0, 0), "gauntlet cursor");
            } catch (HeadlessException | IndexOutOfBoundsException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            this.setCursor(gauntletCursor);
        }
    }

    public static Window GetNewInstance() {
        return new Window();
    }

    public static void refresh() {
        onscreen.drawImage(offscreenImage, 0, 0, null);
        panel.repaint();
        width = panel.getWidth();
        height = panel.getHeight();
    }

    public static void nettoyer() {
        offscreen.setColor(Color.BLACK);
        offscreen.fillRect(0, 0, width, height);
    }

    public static void setStringFont(Font f) {
        offscreen.setFont(f);
    }

    public static void drawString(String s, int size, int x, int y) {
        offscreen.setFont(offscreen.getFont().deriveFont(0, size));
        offscreen.drawString(s, x, y);
    }

    public static void drawString(String s, int size, int x, int y, String c) {
        offscreen.setColor(Color.decode(c));
        offscreen.setFont(offscreen.getFont().deriveFont(0, size));
        offscreen.drawString(s, x, y);
    }

    public static void drawString(String s, int size, int x, int y, String c, String over) {
        offscreen.setFont(offscreen.getFont().deriveFont(0, size));

        int over_size = size/16;

        offscreen.setColor(Color.decode(over));
        offscreen.drawString(s, x-over_size, y+over_size);
        offscreen.setColor(Color.decode(over));
        offscreen.drawString(s, x-over_size, y-over_size);
        offscreen.setColor(Color.decode(over));
        offscreen.drawString(s, x+over_size, y+over_size);
        offscreen.setColor(Color.decode(over));
        offscreen.drawString(s, x+over_size, y-over_size);


        offscreen.setColor(Color.decode(c));
        offscreen.drawString(s, x, y);
    }

    public static void drawTexture(int x, int y, int sizeX, int sizeY, Image texture) {
        offscreen.drawImage(texture, x, y, sizeX, sizeY, null);
    }

    public static void drawTexture(int x, int y, int sizeX, int sizeY, int angle, Image texture) {
        rotation.rotate(Math.toRadians(angle), x + (sizeX / 2), y + (sizeY / 2));
        offscreen.setTransform(rotation);
        offscreen.drawImage(texture, x, y, sizeX, sizeY, null);
        rotation.rotate(Math.toRadians(-angle), x + (sizeX / 2), y + (sizeY / 2));
        offscreen.setTransform(rotation);
    }

    public static void drawGradient(int x, int y, int width, int height, String color1, String color2) {
        Color couleur1 = Color.decode(color1);
        Color couleur2 = Color.decode(color2);

        GradientPaint gradient = new GradientPaint(x, y + (height / 2), couleur1, x + width, y + (height / 2),
                couleur2);
        offscreen.setPaint(gradient);
        offscreen.fillRect(x, y, width, height);
    }

    public static void setColor(Color c) {
        offscreen.setColor(c);
    }

    public static void cls() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Offset x compenser dans la fonction
     * @param x coordonner x a verifier
     * @param y coordonner y a verifier
     * @param i coordonner de la case en x
     * @param j coordonner de la case en y
     * @return
     */
    public static boolean isOnCase(int x, int y, int i, int j) {
        return (x-x_offset)/Ts == i && (y)/Ts == j;
    }

    /**
     * Offset x compenser dans la fonction
     * @param x coordonner x a verifier
     * @param y coordonner y a verifier
     * @param i coordonner de la case en x pixel
     * @param j coordonner de la case en y pixel
     * @return
     * @note version precise au pixel pret de la fonction isOnCase
     */
    public static boolean isOnCase_p(int x, int y, int i, int j) {
        return (x-x_offset) == i && y == j;
    }
}

class ImagePanel extends JComponent {
    private Image image;

    public ImagePanel(Image image) {
        this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    public void setImage(Image i) {
        this.image = i;
    }
}