package de.fhbielefeld.pmdungeon.dungeon.wallpattern;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ObjectMap;
import de.fhbielefeld.pmdungeon.util.Textures;
import de.fhbielefeld.pmdungeon.util.dungeonconverter.Coordinate;

public class TCornerWestWall extends WallPattern {

    public TCornerWestWall(ObjectMap<Textures, Texture> textureMap) {
        super(textureMap);

        this.pattern = new de.fhbielefeld.pmdungeon.dungeon.Dungeon.Tile[][]{
                {A, W, A},
                {W, W, A},
                {A, W, A}
        };
    }

    @Override
    public void render(SpriteBatch batch, Coordinate position) {
        batch.draw(textureMap.get(Textures.FLOOR), position.getX() * textureMap.get(Textures.FLOOR).getWidth(), position.getY() * textureMap.get(Textures.FLOOR).getHeight());
        batch.draw(textureMap.get(Textures.WALL_SIDE_MID_RIGHT), position.getX() * textureMap.get(Textures.WALL_SIDE_MID_RIGHT).getWidth(), position.getY() * textureMap.get(Textures.WALL_SIDE_MID_RIGHT).getHeight());
    }
}
