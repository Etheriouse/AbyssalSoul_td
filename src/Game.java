public class Game {
    
    Level levels[] = init_levels();
    int level_actual = 0;

    public final int max_level = 10;

    public Game() {

    }

    private Level[] init_levels() {
        Level lvls[] = new Level[max_level];
        for(int i = 0; i<max_level; i++) {
            lvls[i] = new Level("assets/level/level"+(i+1)+".lvl");
        }
        return lvls;
    }

    public void run() {
        levels[level_actual].showDecors();
        while (true) {
            print();
        }
    }

    public void print() {
        levels[level_actual].print();
    }
}
