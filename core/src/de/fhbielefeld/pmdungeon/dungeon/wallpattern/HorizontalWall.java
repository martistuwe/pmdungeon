package de.fhbielefeld.pmdungeon.dungeon.wallpattern;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.fhbielefeld.pmdungeon.util.dungeonconverter.Coordinate;

public class HorizontalWall extends WallPattern {

    public HorizontalWall() {
        pattern = new de.fhbielefeld.pmdungeon.dungeon.Dungeon.Tile[][]{
                {A, A, A},
                {W, W, W},
                {A, A, A}
        };
    }

    @Override
    void render(SpriteBatch batch, Coordinate position) {

    }
}
