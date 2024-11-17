package Entity;

import Interface.Texture;
import Interface.Window;
import Map.Game;
import Map.Element.Elementary;
import Math.Point;

public class Mob extends Entity {

    private int max_hp;
    private int hp;

    private int index_path;

    private int speed;
    private int damage;
    private int rayon_hitbox;

    public Mob() {
        super();
        this.max_hp = 100;
        this.hp = 100;
        this.index_path = 0;
        this.speed = -1;
        this.damage = 0;
    }

    public Mob(int max, int hp) {
        super();
        this.max_hp = max;
        this.hp = hp;
        this.index_path = 0;
        this.speed = -1;
        this.damage = 0;
    }

    /*
     * On donne le rayon en nombre de case
     */
    public Mob(int max, int hp, int x, int y, String tex, Elementary type, int speed, int damage, double rayon_hitbox) {
        super(x, y, tex, type);
        this.max_hp = max;
        this.hp = hp;
        this.index_path = 0;
        this.speed = speed;
        this.damage = damage;
        this.rayon_hitbox = (int) (rayon_hitbox*Window.Ts);
    }

    public void takedamage(double n) {
        this.hp-=n;
        if(this.hp < 0) {
            this.hp = 0;
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
        return new Point(x+(Window.Ts/2), y+(Window.Ts/2));
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
        if(source.x == destination.x) {
            if(destination.y - source.y > 0) {
                y+=speed;
                if(this.y > destination.y*Window.Ts) {
                    index_path++;
                    this.x = Window.x_offset + destination.x*Window.Ts;
                    this.y = destination.y*Window.Ts;
                    return;
                }
            } else {
                y-=speed;
                if(this.y < destination.y*Window.Ts) {
                    index_path++;
                    this.x = Window.x_offset + destination.x*Window.Ts;
                    this.y = destination.y*Window.Ts;
                    return;
                }
            }
        } else if(source.y == destination.y) {
            if(destination.x - source.x > 0) {
                x+=speed;
                if(this.x > Window.x_offset+ destination.x*Window.Ts) {
                    index_path++;
                    this.x = Window.x_offset + destination.x*Window.Ts;
                    this.y = destination.y*Window.Ts;
                    return;
                }
            } else {
                x-=speed;
                if(this.x < Window.x_offset+ destination.x*Window.Ts) {
                    index_path++;
                    this.x = Window.x_offset + destination.x*Window.Ts;
                    this.y = destination.y*Window.Ts;
                    return;
                }
            }
        }
    }

    public void print() {
        Window.drawTexture(this.x,  this.y, Window.Ts, Window.Ts, this.getTexture());
        int size = this.rayon_hitbox()*2;
        if(Game.show_hitbox) {
            Window.drawTexture(this.getHixbot().x-this.rayon_hitbox(),  this.getHixbot().y-this.rayon_hitbox(), size, size, Texture.hitbox_texture);
        }
    }

}
