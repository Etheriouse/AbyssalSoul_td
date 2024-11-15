import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GradientPaint;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.TreeMap;
import java.util.TreeSet;

public class Window extends JFrame {

    private static int width = 1600;
    private static int height = 900;

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
    public static TreeMap<String, Long> cooldown = new TreeMap<>();

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
        Random rand = new Random();
        int size_game_y = height; // ratio = 1.38
        int size_game_x = (int) Math.round((size_game_y * 1.38888888888) / 10.0f) * 10;
        int x_offset = (int) Math.round((width * 0.052083) / 10.0f) * 10;
        ;
        System.out.println("size: " + width + "x" + getheight());
        System.out.println(size_game_x + "x" + size_game_y);
        System.out.println("x_offset: " + x_offset);
        System.out.println("ts: " + Ts);
        while (true) {
            int id = 0;
            for (int y = x_offset; y < size_game_x + x_offset; y += Ts) {
                id = rand.nextInt();
                if (id % 7 == 0) {
                    drawTexture(y, 0, Ts, Ts, Texture.floor_grillage_middle);
                } else {
                    drawTexture(y, 0, Ts, Ts, Texture.floor_grillage);
                }
            }
            for (int i = Ts; i < size_game_y; i += Ts) {
                for (int y = x_offset; y < size_game_x + x_offset; y += Ts) {
                    id = rand.nextInt();
                    int temp = id % 60;
                        switch (temp) {
                            case 0:
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 20:
                            case 21:
                            case 22:
                            case 23:
                            case 24:
                            case 13:
                            case 29:
                            case 30:
                                drawTexture(y, i, Ts, Ts, Texture.floor_none);
                                break;

                            case 6:
                            case 7:
                            case 8:
                            case 9:
                            case 25:
                            case 26:
                            case 27:
                            case 28:
                                drawTexture(y, i, Ts, Ts, Texture.floor_shovel);
                                break;

                            case 10:
                            case 11:
                            case 12:
                                drawTexture(y, i, Ts, Ts, Texture.floor_bones);
                                break;

                            case 14:
                            case 15:
                                drawTexture(y, i, Ts, Ts, Texture.floor_tombestone);
                                break;

                            case 16:
                                drawTexture(y, i, Ts, Ts, Texture.floor_mossy_tombestone);
                                break;

                            case 17:
                            case 18:
                                drawTexture(y, i, Ts, Ts, Texture.floor_hand_bones);
                                break;

                            case 19:
                                drawTexture(y, i, Ts, Ts, Texture.floor_mossy_hand_bones);
                                break;

                            default:
                                drawTexture(y, i, Ts, Ts, Texture.floor);
                                break;
                    }
                }
            }


            refresh();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    private void Setup() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
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

        Cursor gauntletCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                new ImageIcon("assets/cursor/cursor.png").getImage(), new Point(0, 0), "gauntlet cursor");
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
        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                xMouse = e.getX();
                yMouse = e.getY() - MarginTop;
                ;
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
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

        });

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

    private void changeCursor(boolean clicked) {
        if (clicked) {
            Cursor gauntletCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                    new ImageIcon("assets/cursor/cursor_clicked.png").getImage(), new Point(0, 0), "gauntlet cursor");
            this.setCursor(gauntletCursor);
        } else {
            Cursor gauntletCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                    new ImageIcon("assets/cursor/cursor.png").getImage(), new Point(0, 0), "gauntlet cursor");
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