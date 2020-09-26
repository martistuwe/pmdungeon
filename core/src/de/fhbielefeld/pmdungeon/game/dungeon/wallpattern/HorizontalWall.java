package de.fhbielefeld.pmdungeon.game.dungeon.wallpattern;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ObjectMap;
import de.fhbielefeld.pmdungeon.game.dungeon.Textures;
import de.fhbielefeld.pmdungeon.game.dungeon.dungeonconverter.Coordinate;
import de.fhbielefeld.pmdungeon.game.dungeon.tiles.Tile;

public class HorizontalWall extends WallPattern {

    public HorizontalWall(ObjectMap<Textures, Texture> textureMap) {
        super(textureMap);

        this.patternList.add(new Tile.Type[][]{
                {A, A, A},
                {W, W, W},
                {A, A, A}
        });
        this.patternList.add(new Tile.Type[][]{
                {A, A, A},
                {D, W, W},
                {A, A, A}
        });
        this.patternList.add(new Tile.Type[][]{
                {A, A, A},
                {W, W, D},
                {A, A, A}
        });
    }

    @Override
    public void render(SpriteBatch batch, Coordinate position) {
        batch.draw(textureMap.get(Textures.WALL_MID), position.getX(), position.getY(), 1, 1);
        batch.draw(textureMap.get(Textures.WALL_TOP_MID), position.getX(), position.getY() + 1f, 1, 1);
    }
}
