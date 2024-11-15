import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Level {

    private int vague;
    private int actual_vague;

    private List<Even<Point, Direction>> path;

    private Point end;

    public static void main(String[] args) {
        Level t = new Level("assets/level/level1.lvl");
    }

    public Level() {

    }


    public Level(String path) {
        decompile_from_file(path);
    }

    private void decompile_from_file(String file) {
        this.path = new ArrayList<>();
        try {
            BufferedReader file_ = new BufferedReader(new FileReader(file));
            String line;
            while((line = file_.readLine()) != null) {
                if(line.contains("start")) { //line.split("(),=>")
                    System.out.println(Arrays.toString(split(line, "(),=>")));;
                } else if(line.contains("end")) {
                   //System.out.println( Arrays.toString(line.split("),(")));;
                } else if(line.contains("wave")) {
                    System.out.println(Arrays.toString( line.split(",")));;
                }
            }
            file_.close();
        } catch (IOException e) {
            System.out.println(e);
            return;
        }
    }

    private String[] split(String s, String regex) {
        ArrayList<String> splits = new ArrayList<>();
        String concat = "";
        for(int i = 0; i<s.length(); i++) {
            if(regex.contains(String.valueOf(s.charAt(i)))) {
                if(!containNothing(concat)) {
                    splits.add(concat);
                    concat = "";
                }
            } else {
                concat+=s.charAt(i);
            }
        }
        if(!containNothing(concat)) {
            splits.add(concat);
            concat = "";
        }
        String[] array = new String[splits.size()];
        int z = 0;
        for (String string : splits) {
            array[z] = string;
            z++;
        }
        return array;
    }

    private boolean containNothing(String s) {
        boolean isNone = s.length() == 0;
        System.out.println("???");
        for (char c : s.toCharArray()) {
            System.out.println(c);
            if(c != ' ') {
                isNone = false;
            }
        }
        return isNone;
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

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

enum Direction {
    right,
    left,
    up,
    down,
}