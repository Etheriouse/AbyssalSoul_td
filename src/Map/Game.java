package Map;

import Math.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import Entity.Tower;
import Interface.Texture;
import Interface.Window;

public class Game {

    Level all_level[] = init_levels();
    int level = 0;

    public final int max_level = 10;
    public int fps = 60;
    public static final int init_tick = 40;
    public int tick = init_tick; // tick 40 rate normally

    public static int size_game_y = Window.height; // ratio = 1.38
    public static int size_game_x = (int) Math.round((size_game_y * 1.38888888888) / 10.0f) * 10;
    public static int side_buy = (int) (0.08 * (Window.Ts * 2.5 * 2));

    public static final int maxWave = 6;

    public static boolean show_hitbox = true;

    public static boolean speed = false;

    public static boolean show_advanced_tool_tips = true;

    public static long start_level;
    public static long ticks_process;

    public Game() {
        Texture.setupTexture();
    }

    private Level[] init_levels() {
        Level lvls[] = new Level[max_level];
        for (int i = 0; i < max_level; i++) {
            lvls[i] = new Level("assets/level/level" + (i + 1) + ".lvl");
        }
        return lvls;
    }

    public void run() {

        long now, tick_time, frame_time, calcule_fps;

        long duration_tick = 1_000_000_000 / tick;
        long duration_fps = 1_000_000_000 / fps;

        tick_time = System.nanoTime();
        frame_time = System.nanoTime();

        calcule_fps = System.nanoTime();

        int ips = 0;
        int ticks = 0;

        int ips_actual = 0, tick_actual = 0;

        start_level = System.currentTimeMillis();

        boolean pause = false;
        ticks_process = 0;

        boolean select = false;
        boolean place = false;

        Point wich_tower = new Point(0, 0);

        while (!isFailed()) {

            if (Window.keysDown.contains(KeyEvent.VK_SPACE)) { // x2 game
                if (Window.cooldown(KeyEvent.VK_SPACE)) {
                    Window.resetcooldown(KeyEvent.VK_SPACE);
                    speed = !speed;
                }

            }

            if (Window.keysDown.contains(KeyEvent.VK_ESCAPE)) { // pause game
                if (Window.cooldown(KeyEvent.VK_ESCAPE)) {
                    Window.resetcooldown(KeyEvent.VK_ESCAPE);
                    pause = !pause;
                }

            }

            if (Window.Click) {
                if (Window.cooldown(MouseEvent.BUTTON1)) {
                    Window.resetcooldown(MouseEvent.BUTTON1);
                    if (Window.xMouse > Window.x_offset && Window.xMouse < size_game_x) {
                        // System.out.println("in place zone");
                        if (select) {
                            // System.out.println("placed");
                            select = false;
                            place = true;
                        }
                    } else if (Window.xMouse >= size_game_x
                            && Window.xMouse <= size_game_x + Window.Ts * 2 * 2.5) {
                        // System.out.println("x valid");
                        if (Window.yMouse > Window.height * 0.22
                                && Window.yMouse < (Window.height * 0.22 + Window.Ts * 2.5 * 5)) {
                            // System.out.println("in zone");
                            select = !select;
                            wich_tower.x = (((int) ((Window.xMouse-Window.x_offset)/(Window.Ts*2.6))) -9);
                            wich_tower.y = ((int) ((Window.yMouse-Window.height * 0.22)/(Window.Ts*2.5)));
                        }
                    }
                    // System.out.println("x: " + Window.xMouse + " y: " + Window.yMouse);

                    // wich_tower.x = (Window.xMouse-Window.x_offset)/Window.Ts*2.5;
                    // wich_tower.y = Window.yMouse/Window.Ts;
                }
            }
            // System.out.println("doit etre entre x : ["+ (size_game_x) + " et " +
            // (size_game_x + Window.Ts * 2 * 2.5) + "]");
            // System.out.println("doit etre entre y : ["+ (Window.height * 0.22) + " et " +
            // (Window.height * 0.22 + Window.Ts * 2.5 * 4.75) + "]");

            if (place) {
                addTower(wich_tower.x, wich_tower.y);
                place = false;
            }

            now = System.nanoTime();
            if (!speed) {
                duration_tick = 1_000_000_000 / tick;
            } else {
                duration_tick = 1_000_000_000 / (tick * 2);
            }

            if (now - tick_time > duration_tick) {
                if (!pause) {
                    process();
                    ticks++;
                    ticks_process++;
                }
                tick_time += duration_tick;
            }

            if (now - frame_time > duration_fps) {
                print();
                ips++;
                frame_time += duration_fps;
            }

            if (now - calcule_fps > 1000000000) {
                ips_actual = ips;
                tick_actual = ticks;
                ips = 0;
                ticks = 0;
                calcule_fps = System.nanoTime();
            }

            if (show_advanced_tool_tips) {
                Window.drawString("fps: " + ips_actual, 40, 20, 40);
                Window.drawString("tick: " + tick_actual + "/s", 40, 20, 80);
            }

            //System.out.println("select: " + select);
            //System.out.println("placed: " + place);
            Window.refresh();
            //Window.cls();
        }
    }

    public static long convertTickToMs(Long tick) {
        /*
         * 40 = tick from Game
         * 40t => 1000ms
         * 1t => 25ms
         * nt => (n*1000/40)
         */
        return (tick * 1000) / Game.init_tick;
    }

    public static long convertMsToTick(Long ms) {
        /*
         * 40 = tick from Game
         * 40t => 1000ms
         * 1t => 25ms
         * nt => (n*1000/40)
         */
        return (ms * Game.init_tick) / 1000;
    }

    public void addTower(int i, int j) {
        all_level[level].addTower(Tower.towers[j][i].copy());
    }

    public boolean isFailed() {
        return all_level[level].isFailed();
    }

    public void process() {
        all_level[level].process();
    }

    public void print() {
        all_level[level].print();
    }
}
