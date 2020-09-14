package de.fhbielefeld.pmdungeon.game.dungeon.wallpattern;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ObjectMap;
import de.fhbielefeld.pmdungeon.game.dungeon.Dungeon;
import de.fhbielefeld.pmdungeon.game.dungeon.Textures;
import de.fhbielefeld.pmdungeon.game.dungeon.dungeonconverter.Coordinate;

public class LowerRightCornerWall extends WallPattern {

    public LowerRightCornerWall(ObjectMap<Textures, Texture> textureMap) {
        super(textureMap);

        this.patternList.add(new Dungeon.Tile[][]{
                {A, W, A},
                {W, W, A},
                {A, A, A}
        });
    }

    @Override
    public void render(SpriteBatch batch, Coordinate position) {
        batch.draw(textureMap.get(Textures.WALL_SIDE_MID_RIGHT), position.getX(), position.getY() + 1f, 1, 1);
        batch.draw(textureMap.get(Textures.WALL_SIDE_FRONT_RIGHT), position.getX(), position.getY(), 1, 1);
        batch.draw(textureMap.get(Textures.WALL_CORNER_FRONT_RIGHT), position.getX() - 1f, position.getY(), 1, 1);
    }
}
