package de.fhbielefeld.pmdungeon.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ObjectMap;

public enum Textures {

    FLOOR("textures/dungeon/floor/floor_1.png"),
    WALL_MID("textures/dungeon/wall/wall_mid.png");

    private final String filename;

    Textures(String filename) {
        this.filename = filename;
    }

    protected Texture get() {
        if (filename != null) {
            return new Texture(filename);
        } else {
            throw new IllegalArgumentException("No texture found for filename.");
        }
    }

    public static ObjectMap<Textures, Texture> loadAllTextures() {
        ObjectMap<Textures, Texture> textureMap = new ObjectMap<>();
        for (Textures t : Textures.values()) {
            textureMap.put(t, t.get());
        }
        return textureMap;
    }
}
