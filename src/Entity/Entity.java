package Entity;

import java.awt.Image;

import Interface.Texture;
import Map.Element.Elementary;
import Math.Point;

public abstract class Entity {

    protected int x;
    protected int y;

    protected String texture;
    protected Elementary type;


    public Entity() {
        this.x = 0;
        this.y = 0;
        this.texture = "none";
        this.type = Elementary.null_;
    }

    public Entity(String texture) {
        this.texture = texture;
        this.x = 0;
        this.y = 0;
        this.type = Elementary.null_;
    }

    public Entity(int i, int j, String texture, Elementary type) {
        this.x = i;
        this.y = j;
        this.texture = texture;
        this.type = type;
    }

    public Image getTexture() {
        return Texture.textures_entity.get(this.texture);
    }


    public abstract Point getHixbot();


    public int x() {
        return this.x;
    }

    public int y() {
        return this.y;
    }

    public void setx(int x) {
        this.x = x;
    }

    public void sety(int y) {
        this.y = y;
    }

    public Elementary getType() {
        return this.type;
    }

}
