package de.fhbielefeld.pmdungeon.dungeon.wallpattern;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ObjectMap;
import de.fhbielefeld.pmdungeon.util.Textures;
import de.fhbielefeld.pmdungeon.util.dungeonconverter.Coordinate;

public class TCornerSouthWall extends WallPattern {

    public TCornerSouthWall(ObjectMap<Textures, Texture> textureMap) {
        super(textureMap);

        this.pattern = new de.fhbielefeld.pmdungeon.dungeon.Dungeon.Tile[][]{
                {A, A, A},
                {W, W, W},
                {A, W, A}
        };
    }

    @Override
    public void render(SpriteBatch batch, Coordinate position) {
        batch.draw(textureMap.get(Textures.WALL_MID), position.getX() * textureMap.get(Textures.WALL_MID).getWidth(), position.getY() * textureMap.get(Textures.WALL_MID).getHeight());
        batch.draw(textureMap.get(Textures.WALL_SIDE_MID_RIGHT), position.getX() * textureMap.get(Textures.WALL_SIDE_MID_RIGHT).getWidth(), position.getY() * textureMap.get(Textures.WALL_SIDE_MID_RIGHT).getHeight());
        batch.draw(textureMap.get(Textures.WALL_TOP_MID), position.getX() * textureMap.get(Textures.WALL_TOP_MID).getWidth(), (position.getY() + 1f) * textureMap.get(Textures.WALL_TOP_MID).getHeight());
    }
}