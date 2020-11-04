package de.fhbielefeld.pmdungeon.game.dungeon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ObjectMap;

/**
 * Central texture handling for dungeon textures
 */
public enum Textures {

    FLOOR("floor/floor_1.png"),
    LADDER("floor/floor_ladder.png"),
    WALL_CORNER_BOTTOM_LEFT("wall/wall_corner_bottom_left.png"),
    WALL_CORNER_FRONT_LEFT("wall/wall_corner_front_left.png"),
    WALL_CORNER_FRONT_RIGHT("wall/wall_corner_front_right.png"),
    WALL_CORNER_LEFT("wall/wall_corner_left.png"),
    WALL_CORNER_TOP_LEFT("wall/wall_corner_top_left.png"),
    WALL_CORNER_TOP_RIGHT("wall/wall_corner_top_right.png"),
    WALL_MID("wall/wall_mid.png"),
    WALL_RIGHT("wall/wall_right.png"),
    WALL_SIDE_FRONT_RIGHT("wall/wall_side_front_right.png"),
    WALL_SIDE_MID_RIGHT("wall/wall_side_mid_right.png"),
    WALL_SIDE_TOP_RIGHT("wall/wall_side_top_right.png"),
    WALL_TOP_MID("wall/wall_top_mid.png");

    private static final String PATH = "textures/dungeon/";
    private String filename = null;

    Textures() {
    }

    Textures(String filename) {
        this.filename = filename;
    }

    /**
     * Loading all dungeon textures into a Map
     *
     * @return Map of all textures used to render the dungeon
     */
    public static ObjectMap<Textures, Texture> loadAllTextures() {
        ObjectMap<Textures, Texture> textureMap = new ObjectMap<>();
        for (Textures t : Textures.values()) {
            textureMap.put(t, t.get());
        }
        return textureMap;
    }

    /**
     * Used to set up textures
     *
     * @return Texture
     */
    protected Texture get() {
        if (filename != null) {
            return new Texture(PATH + filename);
        } else {
            throw new IllegalArgumentException("No texture found for filename.");
        }
    }
}
