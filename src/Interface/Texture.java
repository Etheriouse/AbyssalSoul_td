package Interface;

import java.awt.Image;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.ImageIcon;

import Math.Even;

public class Texture {

    private static String path_asset = "assets/texture/";

    public static Image floor = getImage(path_asset + "map/decors/floor.png");
    public static Image floor_none = getImage(path_asset + "map/decors/floor_none.png");
    public static Image floor_shovel = getImage(path_asset + "map/decors/floor_shovel.png");
    public static Image floor_bones = getImage(path_asset + "map/decors/floor_bones.png");
    public static Image floor_hand_bones = getImage(path_asset + "map/decors/floor_hand_bones.png");
    public static Image floor_tombestone = getImage(path_asset + "map/decors/floor_tombstone.png");
    public static Image floor_blood_tombestone = getImage(path_asset + "map/decors/floor_blood_tombstone.png");
    public static Image floor_mossy_hand_bones = getImage(path_asset + "map/decors/floor_mossy_hand_bones.png");
    public static Image floor_mossy_tombestone = getImage(path_asset + "map/decors/floor_mossy_tombstone.png");
    public static Image floor_blood_mossy_tombestone = getImage(
            path_asset + "map/decors/floor_blood_mossy_tombstone.png");
    public static Image floor_grillage = getImage(path_asset + "map/decors/floor_grillage.png");
    public static Image floor_grillage_middle = getImage(path_asset + "map/decors/floor_grillage_middle.png");
    public static Image floor_skull = getImage(path_asset + "map/decors/floor_skull.png");
    public static Image floor_red_skull = getImage(path_asset + "map/decors/floor_red_skull.png");

    public static Image floor_grillage_portail_exit = getImage(
            path_asset + "map/decors/floor_grillage_portal_exit.png");
    public static Image portal = getImage(path_asset + "map/decors/portal.png");

    public static Image top = getImage(path_asset + "map/path/top.png"); // 1
    public static Image left = getImage(path_asset + "map/path/left.png"); // 2
    public static Image topleft = getImage(path_asset + "map/path/topleft.png"); // 3
    public static Image topright = getImage(path_asset + "map/path/topright.png"); // 4
    public static Image botleft = getImage(path_asset + "map/path/botleft.png"); // 5
    public static Image botright = getImage(path_asset + "map/path/botright.png"); // 6

    public static Image side_bricks = getImage(path_asset + "map/interface/side_stone.png");

    public static Image heart = getImage(path_asset + "map/interface/heart.png");
    public static Image cash_coin = getImage(path_asset + "map/interface/cash_coin.png");
    public static Image tower_frame = getImage(path_asset + "map/interface/tower_frame.png");

    public static Image range_texture = getImage(path_asset + "entity/range.png");
    public static Image hitbox_texture = getImage(path_asset + "entity/hitbox.png");

    public static Image icon = getImage(path_asset + "icon/icon.png");

    public static Image error = getImage(path_asset + "map/error.png");

    public static Map<String, Image> textures_entity = new TreeMap<>();

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void setupTexture() {
        putToMap(
            new Even("none", getImage(path_asset + "entity/none.png")),
            new Even("bloon", getImage(path_asset + "entity/mob/bloon.png")),
            new Even("minigolem", getImage(path_asset + "entity/mob/minigolem.png")),

            new Even("tibo", getImage(path_asset + "entity/tower/tibo.png")),

            new Even("rune_crystal", getImage(path_asset + "entity/tower/rune_crystal.png")),
            new Even("rune_crystal_atk1", getImage(path_asset + "entity/tower/rune_crystal_atk_sprite1.png")),
            new Even("rune_crystal_atk2", getImage(path_asset + "entity/tower/rune_crystal_atk_sprite1.png")),
            new Even("rune_crystal_atk3", getImage(path_asset + "entity/tower/rune_crystal_atk_sprite2.png")),
            new Even("rune_crystal_atk4", getImage(path_asset + "entity/tower/rune_crystal_atk_sprite2.png")),
            new Even("rune_crystal_atk5", getImage(path_asset + "entity/tower/rune_crystal_atk_sprite3.png")),
            new Even("rune_crystal_atk6", getImage(path_asset + "entity/tower/rune_crystal_atk_sprite3.png")),
            new Even("rune_crystal_atk7", getImage(path_asset + "entity/tower/rune_crystal_atk_sprite4.png")),
            new Even("rune_crystal_atk8", getImage(path_asset + "entity/tower/rune_crystal_atk_sprite4.png")),
            new Even("rune_crystal_atk9", getImage(path_asset + "entity/tower/rune_crystal_atk_sprite5.png")),
            new Even("rune_crystal_atk10", getImage(path_asset + "entity/tower/rune_crystal_atk_sprite5.png"))
        );
    }

    public static Map<String, Image> texture_effect = Map.of(
            "air0", getImage(path_asset + "entity/effect/air1.png"),
            "air1", getImage(path_asset + "entity/effect/air2.png"),
            "air2", getImage(path_asset + "entity/effect/air3.png"),

            "fire0", getImage(path_asset + "entity/effect/fire1.png"),
            "fire1", getImage(path_asset + "entity/effect/fire2.png"),
            "fire2", getImage(path_asset + "entity/effect/fire3.png"),
            "fire3", getImage(path_asset + "entity/effect/fire4.png"),

            "freeze", getImage(path_asset + "entity/effect/freeze.png"),
            "ice_cube", getImage(path_asset + "entity/effect/ice_cube.png"));

    private static Image getImage(String s) {
        return new ImageIcon(s).getImage();
    }

    @SuppressWarnings("unchecked")
    private static void putToMap(Even<String, Image>... keyvalue) {
        for (Even<String, Image> even : keyvalue) {
            textures_entity.put(even.one, even.two);
        }
    }

}
