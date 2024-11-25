package Entity;

import java.awt.Image;

import Interface.Texture;
import Interface.Window;
import Map.Game;
import Map.Element.Elementary;
import Math.Point;
import Math.math;

public class Mob extends Entity {

    private int max_hp;
    private int hp;

    private int index_path;
    private int speed_move;
    private int damage;
    private int rayon_hitbox;

    public Mob() {
        super();
        this.max_hp = 100;
        this.hp = 100;
        this.index_path = 0;
        this.speed_move = -1;
        this.damage = 0;
    }

    public Mob(int max, int hp) {
        super();
        this.max_hp = max;
        this.hp = hp;
        this.index_path = 0;
        this.speed_move = -1;
        this.damage = 0;
    }

    /*
     * On donne le rayon en nombre de case
     */
    public Mob(int max, int x, int y, String tex, Elementary type, int speed, int damage, double rayon_hitbox) {
        super(x, y, tex, type);
        this.max_hp = max;
        this.hp = max;
        this.index_path = 0;
        this.speed_move = speed;
        this.damage = damage;
        this.rayon_hitbox = (int) (rayon_hitbox * Window.Ts);
    }

    public void takedamage(double n, int effect_tower[][]) {
        this.hp -= n;
        if (this.hp < 0) {
            this.hp = 0;
        }
        for(int i = 0; i<effect_tower.length; i++) {
            if(effect_tower[i][0] != 0) {
                this.effect[i][0] = 1;
                this.effect[i][1] = (int) Game.ticks_process+effect_tower[i][1];
            }
        }
    }

    public void clearEffect() {
        for (int[] is : effect) {
            if(is[0] != 0) {
                if(is[1] == Game.ticks_process) {
                    is[1] = 0;
                    is[0] = 0;
                }
            }
        }
    }

    public int damage() {
        return this.damage;
    }

    public int max_hp() {
        return this.max_hp;
    }

    public int hp() {
        return this.hp;
    }

    public void sethp(int hp) {
        this.hp = hp;
    }

    public Point getHixbot() {
        return new Point(x + (Window.Ts / 2), y + (Window.Ts / 2));
    }

    public int rayon_hitbox() {
        return this.rayon_hitbox;
    }

    public int pathIndex() {
        return this.index_path;
    }

    public boolean dead() {
        return this.hp == 0;
    }

    public void forward(Point source, Point destination) {
        int speed = this.speed_move;
        if(this.effect[1][0] != 0) {
            speed = 0;
        } else if(this.effect[0][0] != 0) {
            speed/=2;
        }
        if(this.effect[2][0] != 0) {
            if(Game.ticks_process%40 == 0) {
                this.hp-=1;
                if(this.hp < 0) {
                    this.hp = 0;
                }
            }
        }
        if (source.x == destination.x) {
            if (destination.y - source.y > 0) {
                y += speed;
                if (this.y > destination.y * Window.Ts) {
                    index_path++;
                    this.x = Window.x_offset + destination.x * Window.Ts;
                    this.y = destination.y * Window.Ts;
                    return;
                }
            } else {
                y -= speed;
                if (this.y < destination.y * Window.Ts) {
                    index_path++;
                    this.x = Window.x_offset + destination.x * Window.Ts;
                    this.y = destination.y * Window.Ts;
                    return;
                }
            }
        } else if (source.y == destination.y) {
            if (destination.x - source.x > 0) {
                x += speed;
                if (this.x > Window.x_offset + destination.x * Window.Ts) {
                    index_path++;
                    this.x = Window.x_offset + destination.x * Window.Ts;
                    this.y = destination.y * Window.Ts;
                    return;
                }
            } else {
                x -= speed;
                if (this.x < Window.x_offset + destination.x * Window.Ts) {
                    index_path++;
                    this.x = Window.x_offset + destination.x * Window.Ts;
                    this.y = destination.y * Window.Ts;
                    return;
                }
            }
        }
    }

    public int getCash() {
        /**
         * red = bloon 1 air *
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
         * bad (purple moab) = singulariter degenerer 8192 ether *
         */
        switch (this.texture) {
            case "bloon":
                return 1;

            case "minigolem":
                return 2;

            case "braiser":
                return 4;

            case "crystal":
                return 8;

            case "creature":
                return 16;

            case "fleau":
                return 32;

            case "zeplin":
                return 64;

            case "jeff":
                return 128;

            case "golem":
                return 256;

            case "paladin":
                return 512;

            case "seigueur":
                return 1024;

            case "gardien":
                return 2048;

            case "marcheur":
                return 4096;

            case "singulariter":
                return 8192;

            default:
                return 1;
        }
    }

    public static Mob getMobwithType(String type) {
        /**
         * red = bloon 1 air *
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
         * bad (purple moab) = singulariter degenerer 8192 ether *
         */
        switch (type) {
            case "bloon":
                return new Mob(1, 0, 0, "bloon", Elementary.Air, 3, 1, 0.4);

            case "minigolem":
                return new Mob(2, 0, 0, "minigolem", Elementary.Earth, 4, 2, 0.4);

            case "braiser":
                return new Mob(4, 0, 0, "braiser", Elementary.Fire, 4, 4, 0.4);

            case "crystal":
                return new Mob(8, 0, 0, "crystal", Elementary.Rune, 6, 8, 0.4);

            case "creature":
                return new Mob(16, 0, 0, "creature", Elementary.Ether, 6, 16, 0.4);

            case "fleau":
                return new Mob(32, 0, 0, "fleau", Elementary.Abysse, 5, 32, 0.4);

            case "zeplin":
                return new Mob(64, 0, 0, "zeplin", Elementary.Air, 5, 64, 0.4);

            case "jeff":
                return new Mob(128, 0, 0, "jeff", Elementary.Water, 6, 128, 0.4);

            case "golem":
                return new Mob(256, 0, 0, "golem", Elementary.Rune, 3, 256, 0.4);

            case "paladin":
                return new Mob(512, 0, 0, "paladin", Elementary.Water, 3, 512, 0.4);

            case "seigueur":
                return new Mob(1024, 0, 0, "seigueur", Elementary.Fire, 2, 1024, 0.4);

            case "gardien":
                return new Mob(2048, 0, 0, "gardien", Elementary.Earth, 1, 2048, 0.4);

            case "marcheur":
                return new Mob(4096, 0, 0, "marcheur", Elementary.Abysse, 10, 4096, 0.4);

            case "singulariter":
                return new Mob(8192, 0, 0, "singularite", Elementary.Earth, 1, 8192, 0.4);

            default:
                return new Mob(1, 0, 0, "bloon", Elementary.Air, 1, 1, 0.4);
        }
    }

    public void print() {
        Window.drawString(this.hp + "/"+max_hp, 20, this.x, this.y-(Window.Ts/4));
        int size = this.rayon_hitbox() * 2;
        if (Game.show_hitbox) {
            Window.drawTexture(this.getHixbot().x - this.rayon_hitbox(), this.getHixbot().y - this.rayon_hitbox(), size,
            size, Texture.hitbox_texture);
        }
        Window.drawTexture(this.x, this.y, Window.Ts, Window.Ts, this.getTexture());

        if(effect[0][0] != 0) {
            Window.drawTexture(this.x, this.y, Window.Ts, Window.Ts, Texture.texture_effect.get("air"+(math.getfirstDigit(Game.ticks_process%30))));
        }
        if(effect[1][0] != 0) {
            Window.drawTexture(this.x, this.y, Window.Ts, Window.Ts, Texture.texture_effect.get("freeze"));
        }
        if(effect[2][0] != 0) {
            Window.drawTexture(this.x, this.y, Window.Ts, Window.Ts, Texture.texture_effect.get("fire"+math.getfirstDigit((Game.ticks_process%40))));
        }
    }

}
