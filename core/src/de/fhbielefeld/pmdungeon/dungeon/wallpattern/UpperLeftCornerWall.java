package de.fhbielefeld.pmdungeon.dungeon.wallpattern;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ObjectMap;
import de.fhbielefeld.pmdungeon.dungeon.Dungeon;
import de.fhbielefeld.pmdungeon.util.Textures;
import de.fhbielefeld.pmdungeon.util.dungeonconverter.Coordinate;

public class UpperLeftCornerWall extends WallPattern {

    public UpperLeftCornerWall(ObjectMap<Textures, Texture> textureMap) {
        super(textureMap);

        this.patternList.add(new Dungeon.Tile[][]{
                {A, A, A},
                {A, W, W},
                {A, W, A}
        });
    }

    @Override
    public void render(SpriteBatch batch, Coordinate position) {
        batch.draw(textureMap.get(Textures.WALL_CORNER_LEFT), position.getX() * textureMap.get(Textures.WALL_CORNER_LEFT).getWidth(), position.getY() * textureMap.get(Textures.WALL_CORNER_LEFT).getHeight());
        batch.draw(textureMap.get(Textures.WALL_CORNER_TOP_LEFT), position.getX() * textureMap.get(Textures.WALL_CORNER_TOP_LEFT).getWidth(), (position.getY() + 1f) * textureMap.get(Textures.WALL_CORNER_TOP_LEFT).getHeight());
    }
}
