package Entity;

import java.util.ArrayList;
import java.util.Iterator;

import Interface.Texture;
import Interface.Window;
import Map.Game;
import Map.Element.Elementary;
import Math.Direction;
import Math.Even;
import Math.Point;

public class Tower extends Entity {

    private int range;
    private int level;
    private int damage;
    private long cooldown;
    private long last_atk_time = 0;
    /*
     * 0 => zone
     * 1 => lastets
     * 2 => first
     * 3 => more resistant
     * 4 => proche tower
     */
    private int mods = 0;

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
        this.mods = 2;
    }

    /*
     * On donne la range en nombre de case
     * le cooldown en ms
     */
    public Tower(int x, int y, String texture, Elementary element, int damage, double range, int cooldown, int mods) {
        super(x, y, texture, element);
        /*
         * 1c -> 1*Window.Ts
         * 45c -> 45*Window.Ts
         */
        this.cooldown = cooldown * 1_000_000;
        this.damage = damage;
        this.level = 1;
        this.range = (int) (range * Window.Ts + (Window.Ts * 0.5));
        this.mods = mods;
    }

    /**
     * 0 => zone <br>
     * 1 => lastets
     * 2 => first
     * 3 => more resistant
     */
    public int attack(ArrayList<Mob> mobs, ArrayList<Even<Point, Direction>> path) {
        int cash = 0;
        if (mobs.size() == 0) {
            return 0;
        }
        Iterator<Mob> m = mobs.iterator();
        if (this.mods == 0) { // zone
            while (m.hasNext()) {
                Mob entity = m.next();
                if (isInRange(entity)) {
                    if (System.nanoTime() - last_atk_time >= getcouldown()) {
                        this.dealDamage(entity);
                        if (entity.dead()) {
                            cash += entity.getCash();
                            m.remove();
                        }
                        last_atk_time = System.nanoTime();
                    }
                }
            }
        } else if (mods == 1) { // laster
            if (System.nanoTime() - last_atk_time >= getcouldown()) {
                ArrayList<Mob> inRange = new ArrayList<>();

                for (Mob mob : mobs) {
                    if (isInRange(mob)) {
                        inRange.add(mob);
                    }
                }

                if (inRange.size() == 0) {
                    return 0;
                }

                ArrayList<Mob> path_equal = new ArrayList<>();
                Mob less_path = inRange.get(0);
                for (Mob mob : inRange) {
                    if (mob.pathIndex() < less_path.pathIndex()) {
                        less_path = mob;
                        path_equal.clear();
                        path_equal.add(mob);
                    } else if (mob.pathIndex() == less_path.pathIndex()) {
                        path_equal.add(mob);
                    }
                }

                Mob less_distance_mob = path_equal.get(0);
                Point source = path.get(less_distance_mob.pathIndex()).one;
                for (Mob mob : path_equal) {
                    if (distance(mob.x, mob.y, source.x, source.y) < distance(less_distance_mob.x, less_distance_mob.y,
                            source.x, source.y)) {
                        less_distance_mob = mob;
                    }
                }

                this.dealDamage(less_distance_mob);
                if (less_distance_mob.dead()) {
                    cash += less_distance_mob.getCash();
                    mobs.remove(less_distance_mob);
                }
                last_atk_time = System.nanoTime();

            }

        } else if (mods == 2) { //first

            if (System.nanoTime() - last_atk_time >= getcouldown()) {
                ArrayList<Mob> inRange = new ArrayList<>();

                for (Mob mob : mobs) {
                    if (isInRange(mob)) {
                        inRange.add(mob);
                    }
                }

                if (inRange.size() == 0) {
                    return 0;
                }

                ArrayList<Mob> path_equal = new ArrayList<>();
                Mob less_path = inRange.get(0);
                for (Mob mob : inRange) {
                    if (mob.pathIndex() > less_path.pathIndex()) {
                        less_path = mob;
                        path_equal.clear();
                        path_equal.add(mob);
                    } else if (mob.pathIndex() == less_path.pathIndex()) {
                        path_equal.add(mob);
                    }
                }

                Mob great_distance_mob = path_equal.get(0);
                Point source = path.get(great_distance_mob.pathIndex()).one;
                for (Mob mob : path_equal) {
                    if (distance(mob.x, mob.y, source.x, source.y) > distance(great_distance_mob.x,
                            great_distance_mob.y,
                            source.x, source.y)) {
                        great_distance_mob = mob;
                    }
                }

                this.dealDamage(great_distance_mob);
                if (great_distance_mob.dead()) {
                    cash += great_distance_mob.getCash();
                    mobs.remove(great_distance_mob);
                }
                last_atk_time = System.nanoTime();

            }

        } else if (mods == 3) { // more hp
            if (System.nanoTime() - last_atk_time >= getcouldown()) {
                ArrayList<Mob> inRange = new ArrayList<>();

                for (Mob mob : mobs) {
                    if (isInRange(mob)) {
                        inRange.add(mob);
                    }
                }

                if (inRange.size() == 0) {
                    return 0;
                }

                ArrayList<Mob> hp_equal = new ArrayList<>();
                Mob great_hp = inRange.get(0);
                for (Mob mob : inRange) {
                    if (mob.max_hp() > great_hp.max_hp()) {
                        great_hp = mob;
                        hp_equal.clear();
                        hp_equal.add(mob);
                    } else if (mob.max_hp() == great_hp.max_hp()) {
                        hp_equal.add(mob);
                    }
                }

                Mob less_distance_mob = hp_equal.get(0);
                for (Mob mob : hp_equal) {
                    if (distance(mob.getHixbot().x, mob.getHixbot().y, getHixbot().x, getHixbot().y) < distance(
                            less_distance_mob.getHixbot().x, less_distance_mob.getHixbot().y,
                            getHixbot().x, getHixbot().y)) {
                        less_distance_mob = mob;
                    }
                }

                this.dealDamage(less_distance_mob);
                if (less_distance_mob.dead()) {
                    cash += less_distance_mob.getCash();
                    mobs.remove(less_distance_mob);
                }
                last_atk_time = System.nanoTime();

            }

        } else {
            if (System.nanoTime() - last_atk_time >= getcouldown()) {
                ArrayList<Mob> inRange = new ArrayList<>();

                for (Mob mob : mobs) {
                    if (isInRange(mob)) {
                        inRange.add(mob);
                    }
                }

                if (inRange.size() == 0) {
                    return 0;
                }

                Mob less_distance_mob = inRange.get(0);
                for (Mob mob : inRange) {
                    if (distance(mob.getHixbot().x, mob.getHixbot().y, getHixbot().x, getHixbot().y) < distance(
                            less_distance_mob.getHixbot().x, less_distance_mob.getHixbot().y,
                            getHixbot().x, getHixbot().y)) {
                        less_distance_mob = mob;
                    }
                }

                this.dealDamage(less_distance_mob);
                if (less_distance_mob.dead()) {
                    cash += less_distance_mob.getCash();
                    mobs.remove(less_distance_mob);
                }
                last_atk_time = System.nanoTime();

            }

        }
        return cash;
    }

    public long getcouldown() {
        return this.cooldown;
    }

    private int distance(int x_d, int y_d, int x_s, int y_s) {
        int distance = (int) Math.sqrt((Math.pow((x_d - x_s), 2) + Math.pow((y_d - y_s), 2)));
        return distance;
    }

    public Point getHixbot() {
        return new Point(x + (Window.Ts / 2), y + (Window.Ts / 2));
    }

    public int range() {
        return this.range;
    }

    private boolean isInRange(Mob m) {
        /*
         * si d > r1 + r2 = se touche pas
         * si d <= r1 + r2 = se touche
         */
        int d = distance(m.getHixbot().x, m.getHixbot().y, this.getHixbot().x, this.getHixbot().y);
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
