package de.fhbielefeld.pmdungeon.dungeon.wallpattern;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.fhbielefeld.pmdungeon.dungeon.Dungeon;
import de.fhbielefeld.pmdungeon.util.dungeonconverter.Coordinate;

public class DungeonCutout extends WallPattern {

    public DungeonCutout() {
        super(null);
        this.patternList.add(new Dungeon.Tile[3][3]);
    }

    public void fromDungeonTiles(Dungeon dungeon, Coordinate center) {
        // inverted arraycopy to account for internal mirrored representation
        Dungeon.Tile[][] pattern = new Dungeon.Tile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                if (center.getX() - 1 + x < 0 || center.getX() - 1 + x >= dungeon.getWidth() ||
                        center.getY() - 1 + y < 0 || center.getY() - 1 + y >= dungeon.getHeight()) {
                    pattern[2 - y][x] = A;
                } else {
                    pattern[2 - y][x] = dungeon.tiles[center.getX() - 1 + x][center.getY() - 1 + y];
                }
            }
        }
        patternList.add(pattern);
    }

    @Override
    public void render(SpriteBatch batch, Coordinate position) {
        throw new UnsupportedOperationException("DungeonCutout can't be rendered");
    }
}
