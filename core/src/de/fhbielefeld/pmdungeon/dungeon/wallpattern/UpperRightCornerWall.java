package de.fhbielefeld.pmdungeon.dungeon.wallpattern;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ObjectMap;
import de.fhbielefeld.pmdungeon.dungeon.Dungeon;
import de.fhbielefeld.pmdungeon.util.Textures;
import de.fhbielefeld.pmdungeon.util.dungeonconverter.Coordinate;

public class UpperRightCornerWall extends WallPattern {

    public UpperRightCornerWall(ObjectMap<Textures, Texture> textureMap) {
        super(textureMap);

        this.pattern = new Dungeon.Tile[][]{
                {A, A, A},
                {W, W, A},
                {A, W, A}
        };
    }

    @Override
    public void render(SpriteBatch batch, Coordinate position) {
        batch.draw(textureMap.get(Textures.WALL_RIGHT), (position.getX() - 1f) * textureMap.get(Textures.WALL_RIGHT).getWidth(), position.getY() * textureMap.get(Textures.WALL_RIGHT).getHeight());
        batch.draw(textureMap.get(Textures.WALL_CORNER_TOP_RIGHT), (position.getX() - 1f) * textureMap.get(Textures.WALL_CORNER_TOP_RIGHT).getWidth(), (position.getY() + 1f) * textureMap.get(Textures.WALL_CORNER_TOP_RIGHT).getHeight());
        batch.draw(textureMap.get(Textures.WALL_SIDE_MID_RIGHT), position.getX() * textureMap.get(Textures.WALL_SIDE_MID_RIGHT).getWidth(), position.getY() * textureMap.get(Textures.WALL_SIDE_MID_RIGHT).getHeight());
        batch.draw(textureMap.get(Textures.WALL_SIDE_TOP_RIGHT), position.getX() * textureMap.get(Textures.WALL_SIDE_TOP_RIGHT).getWidth(), (position.getY() + 1f) * textureMap.get(Textures.WALL_SIDE_TOP_RIGHT).getHeight());
    }
}
