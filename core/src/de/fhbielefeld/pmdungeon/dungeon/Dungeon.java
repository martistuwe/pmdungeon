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

    private final Texture floor;
    private final Texture wallMid;
    private final Texture wallSideTopRight;
    private final Texture wallSideMidRight;
    private final Texture wallSideFrontRight;
    private final Texture wallCornerFrontLeft;
    private final Texture wallCornerFrontRight;
    private final Texture wallCornerBottomLeft;
    private final Texture wallTopMid;
    private final Texture wallCornerLeft;
    private final Texture wallRight;
    private final Texture wallCornerTopLeft;
    private final Texture wallCornerTopRight;

    private int width;
    private int height;
    public Tile[][] tiles;

    public Dungeon() {
        floor = new Texture("textures/dungeon/floor/floor_1.png");
        wallMid = new Texture("textures/dungeon/wall/wall_mid.png");
        wallSideTopRight = new Texture("textures/dungeon/wall/wall_side_top_right.png");
        wallSideMidRight = new Texture("textures/dungeon/wall/wall_side_mid_right.png");
        wallSideFrontRight = new Texture("textures/dungeon/wall/wall_side_front_right.png");
        wallCornerFrontLeft = new Texture("textures/dungeon/wall/wall_corner_front_left.png");
        wallCornerFrontRight = new Texture("textures/dungeon/wall/wall_corner_front_right.png");
        wallCornerBottomLeft = new Texture("textures/dungeon/wall/wall_corner_bottom_left.png");
        wallTopMid = new Texture("textures/dungeon/wall/wall_top_mid.png");
        wallCornerLeft = new Texture("textures/dungeon/wall/wall_corner_left.png");
        wallRight = new Texture("textures/dungeon/wall/wall_right.png");
        wallCornerTopLeft = new Texture("textures/dungeon/wall/wall_corner_top_left.png");
        wallCornerTopRight = new Texture("textures/dungeon/wall/wall_corner_top_right.png");
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
        WallPattern wallPattern = new WallPattern();
        for (int i = 0; i < this.width; i++) {
            for (int j = this.height - 1; j >= 0; j--) {
                if (this.tiles[i][j] == Tile.WALL) {
                    wallPattern.fromDungeonTiles(this, new Coordinate(i, j));

                    if (wallPattern.equals(WallPattern.horizontal)) {
                        batch.draw(wallMid, i * wallMid.getWidth(), j * wallMid.getHeight());
                        batch.draw(wallTopMid, i * wallTopMid.getWidth(), (j + 1f) * wallTopMid.getHeight());
                    } else if (wallPattern.equals(WallPattern.verticalWithFloorRight)) {
                        batch.draw(floor, i * floor.getWidth(), j * floor.getHeight());
                        batch.draw(wallSideMidRight, i * wallSideMidRight.getWidth(), j * wallSideMidRight.getHeight());
                    } else if (wallPattern.equals(WallPattern.vertical)) {
                        batch.draw(wallSideMidRight, i * wallSideMidRight.getWidth(), j * wallSideMidRight.getHeight());
                    } else if (wallPattern.equals(WallPattern.cornerLeftBottom)) {
                        batch.draw(wallCornerFrontLeft, i * wallCornerFrontLeft.getWidth(), j * wallCornerFrontLeft.getHeight());
                        batch.draw(wallCornerBottomLeft, i * wallCornerBottomLeft.getWidth(), (j + 1f) * wallCornerBottomLeft.getHeight());
                    } else if (wallPattern.equals(WallPattern.cornerLeftTop)) {
                        batch.draw(wallCornerLeft, i * wallCornerLeft.getWidth(), j * wallCornerLeft.getHeight());
                        batch.draw(wallCornerTopLeft, i * wallCornerTopLeft.getWidth(), (j + 1f) * wallCornerTopLeft.getHeight());
                    } else if (wallPattern.equals(WallPattern.cornerRightTop)) {
                        batch.draw(wallRight, (i - 1f) * wallRight.getWidth(), j * wallRight.getHeight());
                        batch.draw(wallCornerTopRight, (i - 1f) * wallCornerTopRight.getWidth(), (j + 1f) * wallCornerTopRight.getHeight());
                        batch.draw(wallSideMidRight, i * wallSideMidRight.getWidth(), j * wallSideMidRight.getHeight());
                        batch.draw(wallSideTopRight, i * wallSideTopRight.getWidth(), (j + 1f) * wallSideTopRight.getHeight());
                    } else if (wallPattern.equals(WallPattern.cornerRightBottom)) {
                        batch.draw(wallSideFrontRight, i * wallSideFrontRight.getWidth(), j * wallSideFrontRight.getHeight());
                        batch.draw(wallCornerFrontRight, (i - 1f) * wallCornerFrontRight.getWidth(), j * wallCornerFrontRight.getHeight());
                    }
                }
            }
        }
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                if (this.tiles[i][j] == Tile.FLOOR) {
                    batch.draw(floor, i * floor.getWidth(), j * floor.getHeight());
                }
            }
        }
    }

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
                    case EMPTY:
                        System.out.print("  ");
                        break;
                }
            }
            System.out.print("\n");
        }
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
        floor.dispose();
        wallMid.dispose();
    }
}
