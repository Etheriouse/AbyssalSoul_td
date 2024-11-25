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
    public static Image cash_coin = getImage(path_asset + "map/interface/cash.png");
    public static Image tower_frame = getImage(path_asset + "map/interface/tower_frame.png");

    public static Image range_texture = getImage(path_asset + "entity/range.png");
    public static Image hitbox_texture = getImage(path_asset + "entity/hitbox.png");

    public static Image setting_texture = getImage(path_asset + "map/interface/gear.png");
    public static Image setting2_texture = getImage(path_asset + "map/interface/gear2.png");


    public static Image care = getImage(path_asset + "map/interface/caré.png");

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

            new Even("care", getImage(path_asset + "map/interface/caré.png")),
            
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
            new Even("rune_crystal_atk10", getImage(path_asset + "entity/tower/rune_crystal_atk_sprite5.png")),
            
            new Even("water_canon", getImage(path_asset + "entity/tower/water_canon.png")),
            new Even("water_canon_atk1", getImage(path_asset + "entity/tower/water_canon_atk_sprite1.png")),
            new Even("water_canon_atk2", getImage(path_asset + "entity/tower/water_canon_atk_sprite1.png")),
            new Even("water_canon_atk3", getImage(path_asset + "entity/tower/water_canon_atk_sprite1.png")),
            new Even("water_canon_atk4", getImage(path_asset + "entity/tower/water_canon_atk_sprite1.png")),
            new Even("water_canon_atk5", getImage(path_asset + "entity/tower/water_canon_atk_sprite1.png")),
            new Even("water_canon_atk6", getImage(path_asset + "entity/tower/water_canon_atk_sprite1.png")),
            new Even("water_canon_atk7", getImage(path_asset + "entity/tower/water_canon_atk_sprite1.png")),
            new Even("water_canon_atk8", getImage(path_asset + "entity/tower/water_canon_atk_sprite1.png")),
            new Even("water_canon_atk9", getImage(path_asset + "entity/tower/water_canon_atk_sprite1.png")),
            new Even("water_canon_atk10", getImage(path_asset + "entity/tower/water_canon_atk_sprite1.png")),
            
            new Even("fire_tombestone", getImage(path_asset + "entity/tower/fire_tombestone.png")),
            new Even("fire_tombestone_atk1", getImage(path_asset + "entity/tower/fire_tombestone_atk_sprite1.png")),
            new Even("fire_tombestone_atk2", getImage(path_asset + "entity/tower/fire_tombestone_atk_sprite1.png")),
            new Even("fire_tombestone_atk3", getImage(path_asset + "entity/tower/fire_tombestone_atk_sprite1.png")),
            new Even("fire_tombestone_atk4", getImage(path_asset + "entity/tower/fire_tombestone_atk_sprite1.png")),
            new Even("fire_tombestone_atk5", getImage(path_asset + "entity/tower/fire_tombestone_atk_sprite1.png")),
            new Even("fire_tombestone_atk6", getImage(path_asset + "entity/tower/fire_tombestone_atk_sprite1.png")),
            new Even("fire_tombestone_atk7", getImage(path_asset + "entity/tower/fire_tombestone_atk_sprite1.png")),
            new Even("fire_tombestone_atk8", getImage(path_asset + "entity/tower/fire_tombestone_atk_sprite1.png")),
            new Even("fire_tombestone_atk9", getImage(path_asset + "entity/tower/fire_tombestone_atk_sprite1.png")),
            new Even("fire_tombestone_atk10", getImage(path_asset + "entity/tower/fire_tombestone_atk_sprite1.png")),
            
            new Even("abysse_eye", getImage(path_asset + "entity/tower/abysse_eye.png")),
            new Even("abysse_eye_atk1", getImage(path_asset + "entity/tower/abysse_eye_atk_sprite1.png")),
            new Even("abysse_eye_atk2", getImage(path_asset + "entity/tower/abysse_eye_atk_sprite1.png")),
            new Even("abysse_eye_atk3", getImage(path_asset + "entity/tower/abysse_eye_atk_sprite1.png")),
            new Even("abysse_eye_atk4", getImage(path_asset + "entity/tower/abysse_eye_atk_sprite1.png")),
            new Even("abysse_eye_atk5", getImage(path_asset + "entity/tower/abysse_eye_atk_sprite1.png")),
            new Even("abysse_eye_atk6", getImage(path_asset + "entity/tower/abysse_eye_atk_sprite1.png")),
            new Even("abysse_eye_atk7", getImage(path_asset + "entity/tower/abysse_eye_atk_sprite1.png")),
            new Even("abysse_eye_atk8", getImage(path_asset + "entity/tower/abysse_eye_atk_sprite1.png")),
            new Even("abysse_eye_atk9", getImage(path_asset + "entity/tower/abysse_eye_atk_sprite1.png")),
            new Even("abysse_eye_atk10", getImage(path_asset + "entity/tower/abysse_eye_atk_sprite1.png")),
            
            new Even("earth_wake", getImage(path_asset + "entity/tower/earth_wake.png")),
            new Even("earth_wake_atk1", getImage(path_asset + "entity/tower/earth_wake_atk_sprite1.png")),
            new Even("earth_wake_atk2", getImage(path_asset + "entity/tower/earth_wake_atk_sprite1.png")),
            new Even("earth_wake_atk3", getImage(path_asset + "entity/tower/earth_wake_atk_sprite1.png")),
            new Even("earth_wake_atk4", getImage(path_asset + "entity/tower/earth_wake_atk_sprite1.png")),
            new Even("earth_wake_atk5", getImage(path_asset + "entity/tower/earth_wake_atk_sprite1.png")),
            new Even("earth_wake_atk6", getImage(path_asset + "entity/tower/earth_wake_atk_sprite1.png")),
            new Even("earth_wake_atk7", getImage(path_asset + "entity/tower/earth_wake_atk_sprite1.png")),
            new Even("earth_wake_atk8", getImage(path_asset + "entity/tower/earth_wake_atk_sprite1.png")),
            new Even("earth_wake_atk9", getImage(path_asset + "entity/tower/earth_wake_atk_sprite1.png")),
            new Even("earth_wake_atk10", getImage(path_asset + "entity/tower/earth_wake_atk_sprite1.png")),


            new Even("air_spiner", getImage(path_asset + "entity/tower/air_spiner.png")),
            new Even("air_spiner_atk1", getImage(path_asset + "entity/tower/air_spiner_atk_sprite1.png")),
            new Even("air_spiner_atk2", getImage(path_asset + "entity/tower/air_spiner_atk_sprite1.png")),
            new Even("air_spiner_atk3", getImage(path_asset + "entity/tower/air_spiner_atk_sprite1.png")),
            new Even("air_spiner_atk4", getImage(path_asset + "entity/tower/air_spiner_atk_sprite1.png")),
            new Even("air_spiner_atk5", getImage(path_asset + "entity/tower/air_spiner_atk_sprite1.png")),
            new Even("air_spiner_atk6", getImage(path_asset + "entity/tower/air_spiner_atk_sprite1.png")),
            new Even("air_spiner_atk7", getImage(path_asset + "entity/tower/air_spiner_atk_sprite1.png")),
            new Even("air_spiner_atk8", getImage(path_asset + "entity/tower/air_spiner_atk_sprite1.png")),
            new Even("air_spiner_atk9", getImage(path_asset + "entity/tower/air_spiner_atk_sprite1.png")),
            new Even("air_spiner_atk10", getImage(path_asset + "entity/tower/air_spiner_atk_sprite1.png")),
            
            new Even("ether_singularity", getImage(path_asset + "entity/tower/ether_singularity.png")),
            new Even("ether_singularity_atk1", getImage(path_asset + "entity/tower/ether_singularity_atk_sprite1.png")),
            new Even("ether_singularity_atk2", getImage(path_asset + "entity/tower/ether_singularity_atk_sprite1.png")),
            new Even("ether_singularity_atk3", getImage(path_asset + "entity/tower/ether_singularity_atk_sprite1.png")),
            new Even("ether_singularity_atk4", getImage(path_asset + "entity/tower/ether_singularity_atk_sprite1.png")),
            new Even("ether_singularity_atk5", getImage(path_asset + "entity/tower/ether_singularity_atk_sprite1.png")),
            new Even("ether_singularity_atk6", getImage(path_asset + "entity/tower/ether_singularity_atk_sprite1.png")),
            new Even("ether_singularity_atk7", getImage(path_asset + "entity/tower/ether_singularity_atk_sprite1.png")),
            new Even("ether_singularity_atk8", getImage(path_asset + "entity/tower/ether_singularity_atk_sprite1.png")),
            new Even("ether_singularity_atk9", getImage(path_asset + "entity/tower/ether_singularity_atk_sprite1.png")),
            new Even("ether_singularity_atk10", getImage(path_asset + "entity/tower/ether_singularity_atk_sprite1.png")),
            
            new Even("dr_stone", getImage(path_asset + "entity/tower/dr_stone.png")),
            new Even("dr_stone_atk1", getImage(path_asset + "entity/tower/dr_stone_atk_sprite1.png")),
            new Even("dr_stone_atk2", getImage(path_asset + "entity/tower/dr_stone_atk_sprite1.png")),
            new Even("dr_stone_atk3", getImage(path_asset + "entity/tower/dr_stone_atk_sprite1.png")),
            new Even("dr_stone_atk4", getImage(path_asset + "entity/tower/dr_stone_atk_sprite1.png")),
            new Even("dr_stone_atk5", getImage(path_asset + "entity/tower/dr_stone_atk_sprite1.png")),
            new Even("dr_stone_atk6", getImage(path_asset + "entity/tower/dr_stone_atk_sprite1.png")),
            new Even("dr_stone_atk7", getImage(path_asset + "entity/tower/dr_stone_atk_sprite1.png")),
            new Even("dr_stone_atk8", getImage(path_asset + "entity/tower/dr_stone_atk_sprite1.png")),
            new Even("dr_stone_atk9", getImage(path_asset + "entity/tower/dr_stone_atk_sprite1.png")),
            new Even("dr_stone_atk10", getImage(path_asset + "entity/tower/dr_stone_atk_sprite1.png")),
            
            new Even("fire_storm", getImage(path_asset + "entity/tower/fire_storm.png")),
            new Even("fire_storm_atk1", getImage(path_asset + "entity/tower/fire_storm_atk_sprite1.png")),
            new Even("fire_storm_atk2", getImage(path_asset + "entity/tower/fire_storm_atk_sprite1.png")),
            new Even("fire_storm_atk3", getImage(path_asset + "entity/tower/fire_storm_atk_sprite1.png")),
            new Even("fire_storm_atk4", getImage(path_asset + "entity/tower/fire_storm_atk_sprite1.png")),
            new Even("fire_storm_atk5", getImage(path_asset + "entity/tower/fire_storm_atk_sprite1.png")),
            new Even("fire_storm_atk6", getImage(path_asset + "entity/tower/fire_storm_atk_sprite1.png")),
            new Even("fire_storm_atk7", getImage(path_asset + "entity/tower/fire_storm_atk_sprite1.png")),
            new Even("fire_storm_atk8", getImage(path_asset + "entity/tower/fire_storm_atk_sprite1.png")),
            new Even("fire_storm_atk9", getImage(path_asset + "entity/tower/fire_storm_atk_sprite1.png")),
            new Even("fire_storm_atk10", getImage(path_asset + "entity/tower/fire_storm_atk_sprite1.png")),
            
            new Even("abysse_shadow", getImage(path_asset + "entity/tower/abysse_shadow.png")),
            new Even("abysse_shadow_atk1", getImage(path_asset + "entity/tower/abysse_shadow_atk_sprite1.png")),
            new Even("abysse_shadow_atk2", getImage(path_asset + "entity/tower/abysse_shadow_atk_sprite1.png")),
            new Even("abysse_shadow_atk3", getImage(path_asset + "entity/tower/abysse_shadow_atk_sprite1.png")),
            new Even("abysse_shadow_atk4", getImage(path_asset + "entity/tower/abysse_shadow_atk_sprite1.png")),
            new Even("abysse_shadow_atk5", getImage(path_asset + "entity/tower/abysse_shadow_atk_sprite1.png")),
            new Even("abysse_shadow_atk6", getImage(path_asset + "entity/tower/abysse_shadow_atk_sprite1.png")),
            new Even("abysse_shadow_atk7", getImage(path_asset + "entity/tower/abysse_shadow_atk_sprite1.png")),
            new Even("abysse_shadow_atk8", getImage(path_asset + "entity/tower/abysse_shadow_atk_sprite1.png")),
            new Even("abysse_shadow_atk9", getImage(path_asset + "entity/tower/abysse_shadow_atk_sprite1.png")),
            new Even("abysse_shadow_atk10", getImage(path_asset + "entity/tower/abysse_shadow_atk_sprite1.png"))
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
