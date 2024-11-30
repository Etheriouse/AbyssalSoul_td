import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;
import java.util.Random;

import Interface.Window;

public class App {

    static Random rand = new Random();
    public static void main(String[] args) throws Exception {
        Window.GetNewInstance().run();
        System.exit(0);
        try {
            double time = 0.5;
            /* 1-> 0.99
             * 99 -> 0.01
             */
            for(int i = 1; i<100; i++) {
                BufferedWriter bw = new BufferedWriter(new FileWriter("assets/level/wave/wave"+i+".w"));
                generateWaveFile(i, bw, time);
                bw.close();
                time-=0.005;
            }
        } catch(Exception e) {

        }
    }

    public static void generateWaveFile(int wave_strenght, BufferedWriter bw, double time) throws IOException {
        int max_luck = 100;
        String line = "";
        int mob;
        double n_time = time;
        for(double i = 0.0; i<wave_strenght*2; i+=0.5) {
            mob = rand.nextInt((int) (max_luck+wave_strenght*10))+wave_strenght;
            if(mob < 100) {
                line = n_time + " | " + enemy.bloon.value();
            } else if(mob < 175) {
                line = n_time + " | " + enemy.minigolem.value();
            } else if(mob < 240) {
                line = n_time + " | " + enemy.braiser.value();
            } else if(mob < 295) {
                line = n_time + " | " + enemy.crystal.value();
            } else if(mob < 345) {
                line = n_time + " | " + enemy.creature.value();
            } else if(mob < 395) {
                line = n_time + " | " + enemy.fleau.value();
            } else if(mob < 440) {
                line = n_time + " | " + enemy.zeplin.value();
            } else if(mob < 485) {
                line = n_time + " | " + enemy.jeff.value();
            } else if(mob < 525) {
                line = n_time + " | " + enemy.golem.value();
            } else if(mob < 565) {
                line = n_time + " | " + enemy.paladin.value();
            } else if(mob < 605) {
                line = n_time + " | " + enemy.seigueur.value();
            } else if(mob < 640) {
                line = n_time + " | " + enemy.gardien.value();
            } else if(mob < 675) {
                line = n_time + " | " + enemy.marcheur.value();
            } else {
                line = n_time + " | " + enemy.singularite.value();
            }
            bw.append(line+"\n");
            n_time+=time;
        }

    }
}

enum enemy {
    /* * red = bloon 1 air *
         * blue = minigolem 2 earth *
         * green = esprit braiser 4 fire *
         * yellow = crystal mouvant 8 rune *
         * pink = creature degenerer 16 ether *
         * black = fleau des tenebre 32 abyss
         * white = Zeplin 64 air *
         * ceramic = jeff 128 eau
         * heavy = golem 256 rune *
         * moab (blue moab) = paladin des mer 512 eau
         * bfb (red moab) = seigueur cendrer 1024 fire *
         * zomg (green moab) = gardient des rock 2048 earth *
         * ddt (black moab) Marcheur d'abysse 4096 abysse *
         * bad (purple moab) = singulariter degenerer 8192 ether * */
    bloon("bloon"),
    minigolem("minigolem"),
    braiser("braiser"),
    crystal("crystal"),
    creature("creature"),
    fleau("fleau"),
    zeplin("zeplin"),
    jeff("jeff"),
    golem("golem"),
    paladin("paladin"),
    seigueur("seigneur"),
    gardien("gardien"),
    marcheur("marcheur"),
    singularite("singularite");


    private String value;

    public String value() {
        return value;
    }

    private enemy(String s) {
        this.value = s;
    }
}
