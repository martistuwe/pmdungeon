package de.fhbielefeld.pmdungeon.dungeon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ObjectMap;
import de.fhbielefeld.pmdungeon.dungeon.wallpattern.WallPattern;
import de.fhbielefeld.pmdungeon.dungeon.wallpattern.WallPatternFactory;
import de.fhbielefeld.pmdungeon.util.Textures;
import de.fhbielefeld.pmdungeon.util.dungeonconverter.Coordinate;
import de.fhbielefeld.pmdungeon.util.dungeonconverter.Room;

public class Dungeon {

    public void printToConsole() {
        for (int i = 0; i < 20; i++) {
            System.out.print("\n");
        }
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                switch (tiles[x][y]) {
                    case WALL:
                        System.out.print("W ");
                        break;
                    case FLOOR:
                        System.out.print("F ");
                        break;
                    case DOOR:
                        System.out.print("D ");
                        break;
                    case EMPTY:
                        System.out.print("  ");
                        break;
                }
            }
            System.out.print("\n");
        }
    }

    private Room[] rooms;

    private int width;
    private int height;
    public Tile[][] tiles;

    private final ObjectMap<Textures, Texture> textureMap;
    private final WallPatternFactory wallPatternFactory;

    public Dungeon() {
        textureMap = Textures.loadAllTextures();
        wallPatternFactory = new WallPatternFactory(textureMap);
    }

    public Dungeon(int width, int height) {
        this();
        this.width = width;
        this.height = height;
        tiles = new Tile[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                tiles[i][j] = Tile.EMPTY;
            }
        }
    }

    public Coordinate getStartingPoint() {
        Coordinate start = rooms[0].getCenter();
        start.setX((rooms[0].getCenter().getX() + rooms[0].getPosition().getX()) * 16);
        start.setY((rooms[0].getCenter().getY() + rooms[0].getPosition().getY()) * 16);
        return start;
    }

    public void renderWalls(SpriteBatch batch) {
        for (int x = 0; x < this.width; x++) {
            for (int y = this.height - 1; y >= 0; y--) {
                WallPattern wallPattern = wallPatternFactory.getWallPattern(this, new Coordinate(x, y));
                if (wallPattern != null) {
                    wallPattern.render(batch, new Coordinate(x, y));
                }
            }
        }
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                if (this.tiles[i][j] == Tile.FLOOR) {
                    batch.draw(textureMap.get(Textures.FLOOR), i * textureMap.get(Textures.FLOOR).getWidth(), j * textureMap.get(Textures.FLOOR).getHeight());
                }
            }
        }
    }

    public enum Tile {
        FLOOR,
        WALL,
        DOOR,
        EMPTY,
    }

    public Tile getTileAt(int x, int y) {
        if (x > 0 && x < width && y > 0 && y < height) {
            return tiles[x][y];
        }
        return null;
    }

    public void setTileAt(int x, int y, Tile tile) {
        if (x > 0 && x < width && y > 0 && y < height) {
            tiles[x][y] = tile;
        }
    }

    public void setRooms(Room[] rooms) {
        this.rooms = rooms;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void dispose() {
        for (Texture t : textureMap.values()) {
            t.dispose();
        }
    }
}
