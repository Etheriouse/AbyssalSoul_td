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

    public static Image floor_grillage_portail_exit = getImage(path_asset+"map/decors/floor_grillage_portal_exit.png");
    public static Image portal = getImage(path_asset+"map/decors/portal.png");

    public static Image top = getImage(path_asset+"map/path/top.png");              //1
    public static Image left = getImage(path_asset+"map/path/left.png");            //2
    public static Image topleft = getImage(path_asset+"map/path/topleft.png");      //3
    public static Image topright = getImage(path_asset+"map/path/topright.png");    //4
    public static Image botleft = getImage(path_asset+"map/path/botleft.png");      //5
    public static Image botright = getImage(path_asset+"map/path/botright.png");    //6

    public static Image side_bricks = getImage(path_asset+"map/interface/side_stone.png");

    public static Image icon = getImage(path_asset+"icon/icon.png");

    public static Image error = getImage(path_asset+"map/error.png");

    private static Image getImage(String s) {
        return new ImageIcon(s).getImage();
    }

}
