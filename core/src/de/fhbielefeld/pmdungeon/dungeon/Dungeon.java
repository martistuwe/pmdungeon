package de.fhbielefeld.pmdungeon.dungeon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.fhbielefeld.pmdungeon.util.dungeonconverter.Coordinate;
import de.fhbielefeld.pmdungeon.util.dungeonconverter.Room;

public class Dungeon {

    public enum Tile {
        FLOOR,
        WALL,
        EMPTY,
    }

    private Room[] rooms;

    private Texture floorTexture;
    private Texture wallTextureMid;
    private Texture wallTextureLeft;
    private Texture wallTextureRight;

    private int width;
    private int height;
    public Tile[][] tiles;

    public Dungeon() {
        floorTexture = new Texture("textures/dungeon/floor/floor_1.png");
        wallTextureMid = new Texture("textures/dungeon/wall/wall_mid.png");
        wallTextureLeft = new Texture("textures/dungeon/wall/wall_side_mid_left.png");
        wallTextureRight = new Texture("textures/dungeon/wall/wall_side_mid_right.png");
    }

    public Dungeon(int x, int y) {
        this();
        this.width = x;
        this.height = y;
        tiles = new Tile[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                tiles[i][j] = Tile.EMPTY;
            }
        }
    }

    public Coordinate getStart() {
        Coordinate start = rooms[0].getCenter();
        start.setX((rooms[0].getCenter().getX() + rooms[0].getPosition().getX()) * 16);
        start.setY((rooms[0].getCenter().getY() + rooms[0].getPosition().getY()) * 16);
        return start;
    }

    public void renderWalls(SpriteBatch batch) {
        for (Room room : rooms) {
            Coordinate[] shape = room.getShape();
            for (int i = 0; i < shape.length; i++) {
                Coordinate wallFrom = shape[i];
                Coordinate wallTo;
                if (shape.length == i + 1) {
                    wallTo = shape[0];
                } else {
                    wallTo = shape[i + 1];
                }

                int positionOffsetX = room.getPosition().getX();
                int positionOffsetY = room.getPosition().getY();
                for (int j = wallFrom.getY(); j < wallTo.getY(); j++) {
                    batch.draw(floorTexture, (wallFrom.getX() + positionOffsetX) * floorTexture.getWidth(), (j + positionOffsetY) * floorTexture.getHeight());
                    batch.draw(wallTextureRight, (wallFrom.getX() + positionOffsetX) * floorTexture.getWidth(), (j + positionOffsetY) * floorTexture.getHeight());
                }
                for (int j = wallFrom.getX(); j < wallTo.getX(); j++) {
                    batch.draw(wallTextureMid, (j + positionOffsetX) * floorTexture.getWidth(), (wallFrom.getY() + positionOffsetY) * floorTexture.getHeight());
                }
                for (int j = wallFrom.getX(); j > wallTo.getX(); j--) {
                    batch.draw(wallTextureMid, (j + positionOffsetX - 1) * floorTexture.getWidth(), (wallFrom.getY() + positionOffsetY) * floorTexture.getHeight());
                }
                for (int j = wallFrom.getY(); j > wallTo.getY(); j--) {
                    batch.draw(wallTextureRight, (wallFrom.getX() + positionOffsetX) * floorTexture.getWidth(), (j + positionOffsetY) * floorTexture.getHeight());
                }
            }
        }
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                if (this.tiles[i][j] == Tile.FLOOR) {
                    batch.draw(floorTexture, i * floorTexture.getWidth(), j * floorTexture.getHeight());
                }
            }
        }
        renderWalls(batch);
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
        floorTexture.dispose();
        wallTextureMid.dispose();
    }
}
