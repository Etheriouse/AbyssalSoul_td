package Entity;

import java.util.ArrayList;
import java.util.Iterator;

import Interface.Texture;
import Interface.Window;
import Map.Game;
import Map.Element.Elementary;
import Math.Point;

public class Tower extends Entity {

    private int range;
    private int level;
    private int damage;
    private long cooldown;
    private long last_atk_time = 0;

    /*
     * On donne la range en nombre de case
     */
    public Tower() {
        super();
        this.texture = "none";
        this.x = 0;
        this.y = 0;
        this.type = Elementary.null_;
        this.cooldown = 54545 * 1_000_000;
        this.damage = 0;
        this.level = 1;
        this.range = 0;
    }

    /*
     * On donne la range en nombre de case
     * le cooldown en ms
     */
    public Tower(int x, int y, String texture, Elementary element, int damage, double range, int cooldown) {
        super(x, y, texture, element);
        /*
         * 1c -> 1*Window.Ts
         * 45c -> 45*Window.Ts
         */
        this.cooldown = cooldown * 1_000_000;
        this.damage = damage;
        this.level = 1;
        this.range = (int) (range * Window.Ts + (Window.Ts * 0.5));
    }

    public void attack(ArrayList<Mob> mobs) {
        Iterator<Mob> m = mobs.iterator();
        while (m.hasNext()) {
            Mob entity = m.next();
            if (isInRange(entity)) {
                if (System.nanoTime() - last_atk_time >= cooldown) {
                    this.dealDamage(entity);
                    if(entity.dead()) {
                        m.remove();
                    }
                    last_atk_time = System.nanoTime();
                }
            }
        }
    }

    public Point getHixbot() {
        return new Point(x + (Window.Ts / 2), y + (Window.Ts / 2));
    }

    public int range() {
        return this.range;
    }

    public boolean isInRange(Mob m) {
        /*
         * si d > r1 + r2 = se touche pas
         * si d <= r1 + r2 = se touche
         */
        int d = (int) Math.sqrt((Math.pow((m.getHixbot().x - this.getHixbot().x), 2)
                + Math.pow((m.getHixbot().y - this.getHixbot().y), 2)));
        return d <= (this.range + m.rayon_hitbox());

    }

    /*
     * src weak = 0.5
     * src none = 1
     * src eff = 1.5
     */
    private void dealDamage(Mob m) {
        if (Elementary.eff(this.type)[1] == m.type || Elementary.eff(this.type)[1] == m.type) {
            m.takedamage((damage * level) * 1.5);
        } else if (Elementary.weakness(this.type)[1] == m.type || Elementary.weakness(this.type)[1] == m.type) {
            m.takedamage((damage * level) * 0.5);
        } else {
            m.takedamage((damage * level) * 1);
        }
    }

    public void print() {
        Window.drawTexture(this.x, this.y, Window.Ts, Window.Ts, this.getTexture());
        int size = this.range() * 2;
        if (Game.show_hitbox) {
            Window.drawTexture(this.getHixbot().x - this.range(), this.getHixbot().y - this.range(), size, size,
                    Texture.range_texture);
        }
    }

}
