package Map;

import Interface.Window;

public class Game {

    Level levels[] = init_levels();
    int level_actual = 0;

    public final int max_level = 10;
    public int fps = 60;
    public final int tick = 40; // tick 40 rate normally

    public static final int maxWave = 6;

    public static boolean show_hitbox = true;

    public static long start_level;

    public Game() {

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


        while (!isFailed()) {

            now = System.nanoTime();

            if (now - tick_time > duration_tick) {
                process();
                ticks++;
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

            Window.drawString("fps: " + ips_actual, 40, 20, 40);
            Window.drawString("tick: " + tick_actual + "/s", 40, 20, 80);
            Window.refresh();

        }
    }

    public boolean isFailed() {
        return levels[level_actual].isFailed();
    }

    public void process() {
        levels[level_actual].process();
    }

    public void print() {
        levels[level_actual].print();
    }
}
