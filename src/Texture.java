import java.awt.Image;

import javax.swing.ImageIcon;

public class Texture {

    private static String path_asset = "assets/texture/";

    public static Image floor = getImage(path_asset+"map/decors/floor.png");
    public static Image floor_none = getImage(path_asset+"map/decors/floor_none.png");
    public static Image floor_shovel = getImage(path_asset+"map/decors/floor_shovel.png");
    public static Image floor_bones = getImage(path_asset+"map/decors/floor_bones.png");
    public static Image floor_hand_bones = getImage(path_asset+"map/decors/floor_hand_bones.png");
    public static Image floor_tombestone = getImage(path_asset+"map/decors/floor_tombstone.png");
    public static Image floor_blood_tombestone = getImage(path_asset+"map/decors/floor_blood_tombstone.png");
    public static Image floor_mossy_hand_bones = getImage(path_asset+"map/decors/floor_mossy_hand_bones.png");
    public static Image floor_mossy_tombestone = getImage(path_asset+"map/decors/floor_mossy_tombstone.png");
    public static Image floor_blood_mossy_tombestone = getImage(path_asset+"map/decors/floor_blood_mossy_tombstone.png");
    public static Image floor_grillage = getImage(path_asset+"map/decors/floor_grillage.png");
    public static Image floor_grillage_middle = getImage(path_asset+"map/decors/floor_grillage_middle.png");
    public static Image floor_skull = getImage(path_asset+"map/decors/floor_skull.png");
    public static Image floor_red_skull = getImage(path_asset+"map/decors/floor_red_skull.png");

    public static Image botleft = getImage(path_asset+"map/path/botleft.png");
    public static Image botright = getImage(path_asset+"map/path/botright.png");
    public static Image left = getImage(path_asset+"map/path/left.png");
    public static Image top = getImage(path_asset+"map/path/top.png");
    public static Image topleft = getImage(path_asset+"map/path/topleft.png");
    public static Image topright = getImage(path_asset+"map/path/topright.png");
    public static Image portal = getImage(path_asset+"map/path/portal.png");

    private static Image getImage(String s) {
        return new ImageIcon(s).getImage();
    }

}
