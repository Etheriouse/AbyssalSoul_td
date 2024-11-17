package Map;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

import Entity.Mob;
import Entity.Tower;
import Interface.Texture;
import Interface.Window;
import Map.Element.Elementary;
import Math.Point;

public class Level {

    private int level;

    private int wave;
    private int actual_vague = 54;

    private ArrayList<Even<Point, Direction>> path = new ArrayList<>();
    private Point start;

    private ArrayList<Tower> tower = new ArrayList<>();
    private ArrayList<Mob> mob = new ArrayList<>();

    private Mob waves[][] = new Mob[wave][];

    private int type_entry;

    private int floor[][];

    private Point end;

    private int life;

    private boolean failed = false;

    public Level() {

    }

    public Level(String path) {
        decompile_from_file(path);
        int x_offset = (int) Math.round((Window.width * 0.052083) / 10.0f) * 10;
        mob.add(new Mob(20, 20, x_offset + (start.x * Window.Ts), start.y * Window.Ts, "bloon", Elementary.Air, 2, 20, 0.4));
        tower.add(new Tower(x_offset + 14*Window.Ts, 14*Window.Ts, "rune_crystal", Elementary.Rune, 10, 1.75, 500));
        this.level = 1;
    }

    public boolean isFailed() {
        return this.failed;
    }

    private void decompile_from_file(String file) {
        this.path = new ArrayList<>();
        try {
            BufferedReader file_ = new BufferedReader(new FileReader(file));
            String line;
            boolean getfloor = false;
            int y = 0;
            while ((line = file_.readLine()) != null) {
                if (line.contains("life")) {
                    String content[] = split(line, "(),=>");
                    this.life = Integer.parseInt(content[1]);
                } else if (line.contains("end")) {
                    String content[] = split(line, "(),=>");
                    this.end = new Point(Integer.parseInt(content[1]), Integer.parseInt(content[2]));
                } else if (line.contains("start")) {
                    fillPath(split(line, "(),=>"));
                } else if (line.contains("wave")) {
                    String content[] = split(line, "(),=>");
                    this.wave = Integer.parseInt(content[1]);
                } else if (line.contains("floor")) {
                    getfloor = true;
                    String content[] = split(line, "(),=>");
                    this.floor = new int[Integer.parseInt(content[1])][Integer.parseInt(content[2])];
                    continue;
                }
                if (getfloor && y < 18) {
                    for (int x = 0; x < line.length(); x++) {
                        this.floor[y][x] = line.charAt(x);
                    }
                    y++;
                }
            }
            file_.close();
        } catch (IOException e) {
            System.out.println(e);
            return;
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void fillPath(String content[]) {
        this.type_entry = Integer.parseInt(content[1]);
        for (int i = 2; i < content.length; i += 3) {
            Direction d = null;
            switch (content[i + 2]) {
                case "left":
                    d = Direction.left;
                    break;

                case "right":
                    d = Direction.right;
                    break;

                case "down":
                    d = Direction.down;
                    break;

                case "up":
                    d = Direction.up;
                    break;

                default:
                    break;
            }
            this.path.add(new Even(new Point(Integer.parseInt(content[i]), Integer.parseInt(content[i + 1])), d));
        }
        start = path.get(0).one;
        this.path.add(new Even(this.end, Direction.null_));
    }

    private String[] split(String s, String regex) {
        ArrayList<String> splits = new ArrayList<>();
        String concat = "";
        for (int i = 0; i < s.length(); i++) {
            if (regex.contains(String.valueOf(s.charAt(i)))) {
                if (!containNothing(concat)) {
                    splits.add(concat);
                    concat = "";
                }
            } else {
                concat += s.charAt(i);
            }
        }
        if (!containNothing(concat)) {
            splits.add(concat);
            concat = "";
        }
        String[] array = new String[splits.size()];
        int z = 0;
        for (String string : splits) {
            array[z] = clearSpace(string);
            z++;
        }
        return array;
    }

    private boolean containNothing(String s) {
        boolean isNone = s.length() == 0;
        for (char c : s.toCharArray()) {
            if (c != ' ') {
                isNone = false;
            }
        }
        return isNone;
    }

    private String clearSpace(String s) {
        String ns = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                ns += s.charAt(i);
            }
        }
        return ns;
    }

    /**
     * . = none
     * = shovel
     * # = grillage
     * @ = fat grillage
     * _ = exit
     * ù = hand
     * % mossy hand
     * £ = tombestone
     * $ = mossy tombestone
     * ^ = bone
     * + = skull
     */
    public void showDecors() {
        for (int[] is : floor) {
            for (int i : is) {
                System.out.print(i);
            }
            System.out.println();

        }
    }

    private Image getTextureEntryFromInt(int n) {
        switch (n) {
            case 0:
                return Texture.portal;

            case 1:
                return Texture.floor_blood_tombestone;

            case 2:
                return Texture.floor_blood_mossy_tombestone;

            case 3:
                return Texture.floor_red_skull;

            default:
                return Texture.error;
        }
    }

    private Image getTextureFromInt(int n) {
        switch (n) {
            case 35:
                return Texture.floor_grillage;

            case 64:
                return Texture.floor_grillage_middle;

            case 95:
                return Texture.floor_grillage_portail_exit;

            case 46:
                return Texture.floor;

            case 42:
                return Texture.floor_shovel;

            case 249:
                return Texture.floor_hand_bones;

            case 37:
                return Texture.floor_mossy_hand_bones;

            case 163:
                return Texture.floor_tombestone;

            case 36:
                return Texture.floor_mossy_tombestone;

            case 94:
                return Texture.floor_bones;

            case 43:
                return Texture.floor_skull;

            default:
                return Texture.error;
        }
    }

    private int getDirectionFromPoint(Direction s, Direction d) {
        int x = d.x;
        int y = d.y;
        int x_ = s.x;
        int y_ = s.y;
        if ((x == 1 && y_ == -1) || (x_ == -1 && y == 1)) {
            // top right
            return 2;
        }
        if ((x == 1 && y_ == 1) || (x_ == -1 && y == -1)) {
            // bot right
            return 4;
        }
        if ((x == -1 && y_ == -1) || (x_ == 1 && y == 1)) {
            // top left
            return 5;
        }
        if ((x == -1 && y_ == 1) || (x_ == 1 && y == -1)) {
            // bot left
            return 3;
        }
        return -1;
    }

    private Image getTexturePathFromInt(int n) {
        switch (n) {
            case 0:
                return Texture.top;

            case 1:
                return Texture.left;

            case 5:
                return Texture.topleft;

            case 2:
                return Texture.topright;

            case 3:
                return Texture.botleft;

            case 4:
                return Texture.botright;

            default:
                return Texture.error;
        }
    }

    public void print() {
        int size_game_y = Window.height; // ratio = 1.38
        int size_game_x = (int) Math.round((size_game_y * 1.38888888888) / 10.0f) * 10;
        int a = 0, b = 0;
        // System.out.println(Window.Ts);

        for (int i = 0; i < size_game_y; i += Window.Ts) {
            for (int y = Window.x_offset; y < size_game_x + Window.x_offset; y += Window.Ts) {
                Window.drawTexture(y, i, Window.Ts, Window.Ts, getTextureFromInt(this.floor[b][a]));
                a++;
            }
            a = 0;
            b++;
        }

        for (int y = 0; y < size_game_y; y += Window.Ts) {
            for (int x = (100 - Window.Ts); x >= -50; x -= Window.Ts) {
                Window.drawTexture(x, y, Window.Ts, Window.Ts, Texture.side_bricks);
            }
        }

        for (int y = 0; y < size_game_y; y += Window.Ts) {
            for (int x = size_game_x; x < Window.width + Window.Ts; x += Window.Ts) {
                Window.drawTexture(x, y, Window.Ts, Window.Ts, Texture.side_bricks);
            }
        }

        int sub = 1;

        for (int i = 0; i < path.size() - 1; i += 1) {
            Even<Point, Direction> s = path.get(i);
            Even<Point, Direction> d = path.get(i + 1);
            if (s.two.x != 0) {
                if (s.two.x > 0) {
                    for (int j = sub; j < Math.abs(d.one.x - s.one.x); j++) {
                        Window.drawTexture(Window.x_offset + (s.one.x + j) * Window.Ts, s.one.y * Window.Ts, Window.Ts,
                                Window.Ts,
                                getTexturePathFromInt(1));
                    }
                } else {
                    for (int j = sub; j < Math.abs(d.one.x - s.one.x); j++) {
                        Window.drawTexture(Window.x_offset + (s.one.x - j) * Window.Ts, s.one.y * Window.Ts, Window.Ts,
                                Window.Ts,
                                getTexturePathFromInt(1));
                    }
                }
            }
            if (s.two.y != 0) {
                if (s.two.y > 0) {
                    for (int j = sub; j < Math.abs(d.one.y - s.one.y); j++) {
                        Window.drawTexture(Window.x_offset + (s.one.x) * Window.Ts, (s.one.y + j) * Window.Ts, Window.Ts,
                                Window.Ts, getTexturePathFromInt(0));
                    }
                } else {
                    for (int j = sub; j < Math.abs(d.one.y - s.one.y); j++) {
                        Window.drawTexture(Window.x_offset + (s.one.x) * Window.Ts, (s.one.y - j) * Window.Ts, Window.Ts,
                                Window.Ts, getTexturePathFromInt(0));
                    }
                }
            }
            Window.drawTexture(Window.x_offset + d.one.x * Window.Ts, d.one.y * Window.Ts, Window.Ts, Window.Ts,
                    getTexturePathFromInt(getDirectionFromPoint(s.two, d.two)));

        }

        Window.drawTexture(Window.x_offset + this.end.x * Window.Ts, this.end.y * Window.Ts, Window.Ts, Window.Ts,
                Texture.floor_grillage_portail_exit);
        Window.drawTexture(Window.x_offset + this.path.get(0).one.x * Window.Ts, this.path.get(0).one.y * Window.Ts, Window.Ts,
                Window.Ts, getTextureEntryFromInt(type_entry));

        for (Mob entity : mob) {
            entity.print();
        }

        for (Tower entity : tower) {
            entity.print();
        }

        Window.drawString("wave", 40, size_game_x-Window.x_offset, 40);
        Window.drawString(""+actual_vague, 40, size_game_x-Window.x_offset, 80);


        Window.drawString("level", 40, size_game_x-Window.x_offset, 120);
        Window.drawString(""+this.level, 40, size_game_x-Window.x_offset, 160);
    }

    public void process() {
        int path_ = 0;
        Iterator<Mob> m = mob.iterator();
        while (m.hasNext()) {
            Mob entity = m.next();
            if (Window.isOnCase_p(entity.x(), entity.y(), end.x*Window.Ts, end.y*Window.Ts)) {
                this.life-=entity.damage();
                if(this.life <= 0) {
                    System.out.println(life);
                    this.failed = true;
                }
                m.remove();
                continue;
            }

            path_ = entity.pathIndex();
            if (!(path_ + 1 == path.size())) {
                entity.forward(this.path.get(path_).one, this.path.get(path_ + 1).one);
            }
        }

        for (Tower tower : tower) {
            tower.attack(mob);
        }
    }

    public void spawnMob() {

    }

}

class Even<T, V> {
    T one;
    V two;

    public Even(T one, V two) {
        this.one = one;
        this.two = two;
    }
}

enum Direction {
    right(1, 0),
    left(-1, 0),
    up(0, -1),
    down(0, 1),
    null_(0, 0);

    public int x;
    public int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }
}