package de.fhbielefeld.pmdungeon.dungeon.wallpattern;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.fhbielefeld.pmdungeon.util.dungeonconverter.Coordinate;

public class HorizontalWall extends WallPattern {

    private final Texture wallMid;
    private final Texture wallTopMid;

    public HorizontalWall() {

        wallMid = new Texture("textures/dungeon/wall/wall_mid.png");
        wallTopMid = new Texture("textures/dungeon/wall/wall_top_mid.png");

        pattern = new de.fhbielefeld.pmdungeon.dungeon.Dungeon.Tile[][]{
                {A, A, A},
                {W, W, W},
                {A, A, A}
        };
    }

    @Override
    public void render(SpriteBatch batch, Coordinate position) {
        batch.draw(wallMid, position.getX() * wallMid.getWidth(), position.getY() * wallMid.getHeight());
        batch.draw(wallTopMid, position.getX() * wallTopMid.getWidth(), (position.getY() + 1f) * wallTopMid.getHeight());
    }
}
