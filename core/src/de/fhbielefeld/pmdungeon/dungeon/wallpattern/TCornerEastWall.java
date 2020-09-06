package de.fhbielefeld.pmdungeon.dungeon.wallpattern;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ObjectMap;
import de.fhbielefeld.pmdungeon.dungeon.Dungeon;
import de.fhbielefeld.pmdungeon.util.Textures;
import de.fhbielefeld.pmdungeon.util.dungeonconverter.Coordinate;

public class TCornerEastWall extends WallPattern {

    public TCornerEastWall(ObjectMap<Textures, Texture> textureMap) {
        super(textureMap);

        this.patternList.add(new Dungeon.Tile[][]{
                {A, W, A},
                {A, W, W},
                {A, W, A}
        });
    }

    @Override
    public void render(SpriteBatch batch, Coordinate position) {
        batch.draw(textureMap.get(Textures.WALL_MID), position.getX(), position.getY(), 1, 1);
        batch.draw(textureMap.get(Textures.WALL_CORNER_TOP_LEFT), position.getX(), position.getY() + 1f, 1, 1);
        batch.draw(textureMap.get(Textures.WALL_SIDE_MID_RIGHT), position.getX(), position.getY(), 1, 1);
        batch.draw(textureMap.get(Textures.WALL_SIDE_MID_RIGHT), position.getX(), position.getY() + 1f, 1, 1);
    }
}
