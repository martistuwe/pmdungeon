package de.fhbielefeld.pmdungeon.dungeon.wallpattern;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ObjectMap;
import de.fhbielefeld.pmdungeon.dungeon.Dungeon;
import de.fhbielefeld.pmdungeon.util.Textures;
import de.fhbielefeld.pmdungeon.util.dungeonconverter.Coordinate;

public class TCornerNorthWall extends WallPattern {

    public TCornerNorthWall(ObjectMap<Textures, Texture> textureMap) {
        super(textureMap);

        this.patternList.add(new Dungeon.Tile[][]{
                {A, W, A},
                {W, W, W},
                {A, A, A}
        });
    }

    @Override
    public void render(SpriteBatch batch, Coordinate position) {
        batch.draw(textureMap.get(Textures.WALL_MID), position.getX() * textureMap.get(Textures.WALL_MID).getWidth(), position.getY() * textureMap.get(Textures.WALL_MID).getHeight());
        batch.draw(textureMap.get(Textures.WALL_CORNER_BOTTOM_LEFT), position.getX() * textureMap.get(Textures.WALL_CORNER_BOTTOM_LEFT).getWidth(), (position.getY() + 1f) * textureMap.get(Textures.WALL_CORNER_BOTTOM_LEFT).getHeight());
    }
}
