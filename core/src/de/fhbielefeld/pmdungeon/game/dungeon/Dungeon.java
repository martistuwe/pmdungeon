package de.fhbielefeld.pmdungeon.game.dungeon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ObjectMap;
import de.fhbielefeld.pmdungeon.game.dungeon.dungeonconverter.Coordinate;
import de.fhbielefeld.pmdungeon.game.dungeon.dungeonconverter.Room;
import de.fhbielefeld.pmdungeon.game.dungeon.tiles.Tile;
import de.fhbielefeld.pmdungeon.game.dungeon.wallpattern.WallPattern;
import de.fhbielefeld.pmdungeon.game.dungeon.wallpattern.WallPatternFactory;

import java.util.Random;

public class Dungeon {

    private Room[] rooms;

    private int width;
    private int height;
    private Tile[][] tiles;

    private final ObjectMap<Textures, Texture> textureMap;
    private final WallPatternFactory wallPatternFactory;
    private final Random random = new Random();

    private Dungeon() {
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
                tiles[i][j] = new Tile(Tile.Type.EMPTY, i, j);
            }
        }
    }

    public Coordinate getStartingLocation() {
        return getRandomLocationInRoom(0);
    }

    public Coordinate getBossStartingLocation() {
        return getRandomLocationInRoom(rooms.length - 1);
    }

    public Coordinate getRandomLocationInDungeon() {
        int roomId = (this.random.nextInt(rooms.length - 1)) + 1;
        return getRandomLocationInRoom(roomId);
    }

    private Coordinate getRandomLocationInRoom(int roomId) {
        if (rooms[roomId] != null) {
            Coordinate roomExtensions = rooms[roomId].getExtension();

            Coordinate point = new Coordinate(Integer.MIN_VALUE, Integer.MIN_VALUE);
            while (getTileTypeAt(point) != Tile.Type.FLOOR) {
                point.setX(random.nextInt(roomExtensions.getX() - 1));
                point.setY(random.nextInt(roomExtensions.getY() - 1));
            }
            point.add(new Coordinate(1, 1));
            point.add(new Coordinate(rooms[roomId].getPosition().getX(), rooms[roomId].getPosition().getY()));
            return point;
        } else {
            return null;
        }
    }

    public void renderWalls(SpriteBatch batch) {
        for (int x = 0; x < this.width; x++) {
            for (int y = this.height - 1; y >= 0; y--) {
                if (this.tiles[x][y].getType() == Tile.Type.WALL) {
                    WallPattern wallPattern = wallPatternFactory.getWallPattern(this, new Coordinate(x, y));
                    if (wallPattern != null) {
                        wallPattern.render(batch, new Coordinate(x, y));
                    }
                }
            }
        }
    }

    public void renderFloor(SpriteBatch batch) {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                if (this.tiles[i][j].getType() != Tile.Type.EMPTY && this.tiles[i + 1][j].getType() != Tile.Type.EMPTY) {
                    batch.draw(textureMap.get(Textures.FLOOR), i, j, 1, 1);
                }
            }
        }
    }

    private Tile getTileAt(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return tiles[x][y];
        }
        return null;
    }

    public Tile.Type getTileTypeAt(int x, int y) {
        Tile tile = getTileAt(x, y);
        if (tile != null) {
            return tile.getType();
        }
        return null;
    }

    public Tile.Type getTileTypeAt(Coordinate coordinate) {
        return this.getTileTypeAt(coordinate.getX(), coordinate.getY());
    }

    public void setTileAt(int x, int y, Tile.Type tile) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            tiles[x][y] = new Tile(tile, x, y);
        }
    }

    public boolean isTileAccessible(int x, int y) {
        Tile tile = getTileAt(x, y);
        if (tile != null) {
            return tile.isAccessible();
        }
        return false;
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
