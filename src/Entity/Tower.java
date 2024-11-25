package Entity;

import java.util.ArrayList;
import java.util.Iterator;

import java.awt.Image;

import Interface.Texture;
import Interface.Window;
import Map.Game;
import Map.Element.Elementary;
import Map.Element.TargetSet;
import Math.Direction;
import Math.Even;
import Math.Point;
import Math.math;

public class Tower extends Entity {

    private int range;
    private int level;
    private int damage;
    private int cooldown;
    private long last_atk_time = 0;

    private String texture_sprite;
    private int nb_sprit_anim;
    private int counter_sprite = 0;
    private int price = 0;

    /*
     * 0 => zone
     * 1 => lastets
     * 2 => first
     * 3 => more resistant
     * 4 => proche tower
     */
    private TargetSet target;

    private int tick_sprite_animation = 10;

    /*
     * On donne la range en nombre de case
     */
    public Tower() {
        super();
        this.texture = "none";
        this.texture_sprite = "none";
        this.x = 0;
        this.y = 0;
        this.type = Elementary.null_;
        this.cooldown = 54545 * 1_000_000;
        this.damage = 0;
        this.level = 1;
        this.range = 0;
        this.target = TargetSet.first;
    }

    /*
     * On donne la range en nombre de case
     * le cooldown en ms
     */
    public Tower(int x, int y, String texture, String texture_srite, Elementary element, int damage, double range,
            int cooldown, TargetSet mods, int nb_sprit_anim, int price) {
        super(x, y, texture, element);
        this.texture_sprite = texture_srite;
        /*
         * 1c -> 1*Window.Ts
         * 45c -> 45*Window.Ts
         */
        this.cooldown = (int) Game.convertMsToTick((long) cooldown);
        this.damage = damage;
        this.level = 1;
        this.range = (int) (range * Window.Ts + (Window.Ts * 0.5));
        this.target = mods;
        this.nb_sprit_anim = nb_sprit_anim;
        this.price = price;
    }

    private Tower(int x, int y, String texture, String texture_srite, Elementary element, int damage, int range, int cooldown, TargetSet mods, int nb_sprit_anim, int price) {
        super(x, y, texture, element);
        this.texture_sprite = texture_srite;
        this.cooldown = cooldown;
        this.damage = damage;
        this.level = 1;
        this.range = range;
        this.target = mods;
        this.nb_sprit_anim = nb_sprit_anim;
        this.price = price;
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
        boolean has_atk = false;
        if (this.target == TargetSet.zone) { // zone
            while (m.hasNext()) {
                Mob entity = m.next();
                if (isInRange(entity)) {
                    if (canAtk()) {
                        this.dealDamage(entity);
                        has_atk = true;
                        if (entity.dead()) {
                            cash += entity.getCash();
                            m.remove();
                        }
                    }
                }
            }
            if (has_atk) {
                last_atk_time = Game.ticks_process;
            }
        } else if (target == TargetSet.last) { // laster
            if (canAtk()) {
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
                last_atk_time = Game.ticks_process;

            }

        } else if (target == TargetSet.first) { // first

            if (canAtk()) {
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

                last_atk_time = Game.ticks_process;

            }

        } else if (target == TargetSet.strong) { // more hp
            if (canAtk()) {
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

                last_atk_time = Game.ticks_process;

            }

        } else if (this.target == TargetSet.close) { // TargetSet.close
            if (canAtk()) {
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

                last_atk_time = Game.ticks_process;

            }

        }
        return cash;
    }

    private boolean canAtk() {
        return Game.ticks_process - last_atk_time > cooldown;
    }

    public void resetcooldown() {
        this.last_atk_time = 0;
    }

    private int distance(int x_d, int y_d, int x_s, int y_s) {
        int distance = (int) Math.sqrt((Math.pow((x_d - x_s), 2) + Math.pow((y_d - y_s), 2)));
        return distance;
    }

    public Tower setEffect(int effect[][]) {
        this.effect = effect;
        return this;
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
            m.takedamage((damage * level) * 1.5, this.effect);
        } else if (Elementary.weakness(this.type)[1] == m.type || Elementary.weakness(this.type)[1] == m.type) {
            m.takedamage((damage * level) * 0.5, this.effect);
        } else {
            m.takedamage((damage * level) * 1, this.effect);
        }
    }

    @Override
    public Image getTexture() {
        if (Game.ticks_process - last_atk_time >= tick_sprite_animation || Game.ticks_process <= 40) {
            counter_sprite = 0;
            return Texture.textures_entity.get(this.texture);
        } else {
            int sprite = (int) ((Game.ticks_process - last_atk_time) % (nb_sprit_anim * 2)) + 1;
            return Texture.textures_entity.get(this.texture_sprite + sprite);
        }
    }

    public int getPrice() {
        return price;
    }

    public void print() {
        int size = this.range() * 2;
        Window.drawTexture(this.x, this.y, Window.Ts, Window.Ts, this.getTexture());
        if (Game.show_hitbox) {
            Window.drawTexture(this.getHixbot().x - this.range(), this.getHixbot().y - this.range(), size, size,
                    Texture.range_texture);
        }
    }

    /*
     *  Tower fire = new Tower(Window.x_offset + 14*Window.Ts, 8*Window.Ts, "rune_crystal", "rune_crystal_atk", Elementary.Rune, 1, 1.75, 2000, TargetSet.close, 5);
        fire.setEffect(new int[][]{{0, 80}, {0, 20}, {1, 40*5}});

        Tower ice = new Tower(Window.x_offset + 8*Window.Ts, 14*Window.Ts, "rune_crystal", "rune_crystal_atk", Elementary.Rune, 1, 1.75, 2000, TargetSet.last, 5);
        ice.setEffect(new int[][]{{0, 80}, {1, 20}, {1, 0}});

        Tower venti = new Tower(Window.x_offset + 18*Window.Ts, 14*Window.Ts, "rune_crystal", "rune_crystal_atk", Elementary.Rune, 1, 1.75, 2000, TargetSet.first, 5);
        venti.setEffect(new int[][]{{1, 400}, {0, 20}, {0, 0}});

        Tower venti = new Tower(0, 0, "rune_crystal", "rune_crystal_atk", Elementary.Rune, 1, 1.75, 2000, TargetSet.first, 5);

     */

    public static Tower towers[][] = { // TODO faire les sprite d'animation
            {
                /*
                 * tour runique
                 */
                new Tower(0, 0, "rune_crystal", "rune_crystal_atk", Elementary.Rune, 1, 1.5, 1000, TargetSet.first, 5, 300),
                /*
                 * canon a eau jsp quoi ptdr
                 */
                new Tower(0, 0, "water_canon", "water_canon_atk", Elementary.Water, 2, 1.5, 1000, TargetSet.close, 1, 350),
                /*
                 * tombe stone avec de la lave qui tombe et tout
                 */
                new Tower(0, 0, "fire_tombestone", "fire_tombestone_atk", Elementary.Fire, 3, 1.75, 800, TargetSet.last, 1, 450).setEffect(new int[][]{{0, 0},{0, 0},{1, 6*Game.init_tick}}),
                /*
                 * oeuil sombre
                 */
                new Tower(0, 0, "abysse_eye", "abysse_eye_atk", Elementary.Abysse, 5, 2.0, 750, TargetSet.strong, 1, 500),
                /*
                 * pilier de terre qui fait apparaitre des fissure
                 */
                new Tower(0, 0, "earth_wake", "earth_wake_atk", Elementary.Earth, 4, 2.0, 1100, TargetSet.zone, 3, 400).setEffect(new int[][]{{0, 0},{1, 1*Game.init_tick}, {0, 0}})
            },
            {
                /*
                 * shuriken d'air
                 */
                new Tower(0, 0, "air_spiner", "air_spiner_atk", Elementary.Air, 1, 2.5, 500, TargetSet.first, 1, 250).setEffect(new int[][]{{1, 5*Game.init_tick},{0, 0}, {0, 0}}),
                /*
                 * singulariter d'arcan
                 */
                new Tower(0, 0, "ether_singularity", "ether_singularity_atk", Elementary.Ether, 8, 2.5, 1000, TargetSet.strong, 1, 750),
                /*
                 * Medusa de dr stone
                 */
                new Tower(0, 0, "dr_stone", "dr_stone_atk", Elementary.Earth, 7, 3.0, 2500, TargetSet.zone, 1, 600).setEffect(new int[][]{{0, 0},{1, 2*Game.init_tick}, {0, 0}}),
                /*
                 * tornade de feu
                 */
                new Tower(0, 0, "fire_storm", "fire_storm_atk", Elementary.Fire, 6, 1.5, 1000, TargetSet.last, 1, 550).setEffect(new int[][]{{0, 0},{0, 0}, {1, 5*Game.init_tick}}),
                /*
                 * ombre noir
                 */
                new Tower(0, 0, "abysse_shadow", "abysse_shadow_atk", Elementary.Abysse, 10, 1.75, 750, TargetSet.strong, 1, 700)
            }
    };

    private Tower copyEffect(int eff[][]) {
        for(int i = 0; i<eff.length; i++) {
            this.effect[i][0] = eff[i][0];
            this.effect[i][1] = eff[i][1];
        }
        return this;
    }

    public Tower copy() {
        return new Tower(x, y, texture, texture_sprite, type, damage, range, cooldown, target, nb_sprit_anim, price).copyEffect(effect);
    }

    @Override
    public String toString() {
        return this.texture + " price: " + price;
    }
}
