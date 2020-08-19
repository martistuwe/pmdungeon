package de.fhbielefeld.pmdungeon.dungeon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.fhbielefeld.pmdungeon.util.dungeonconverter.Coordinate;
import de.fhbielefeld.pmdungeon.util.dungeonconverter.Room;

public class Dungeon {

    private SpriteBatch batch;
    private Room[] rooms;

    private Texture floorTexture;
    private Texture wallTextureMid;
    private Texture wallTextureLeft;
    private Texture wallTextureRight;

    public Dungeon(SpriteBatch batch) {
        this.batch = batch;

        floorTexture = new Texture("floor_1.png");
        wallTextureMid = new Texture("wall_mid.png");
        wallTextureLeft = new Texture("wall_side_mid_left.png");
        wallTextureRight = new Texture("wall_side_mid_right.png");
    }

    public void render() {
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
                for (int j = wallFrom.getX(); j < wallTo.getX(); j++) {
                    batch.draw(wallTextureMid, (j + positionOffsetX) * floorTexture.getWidth(), (wallFrom.getY() + positionOffsetY) * floorTexture.getHeight());
                }
                for (int j = wallFrom.getY(); j <= wallTo.getY(); j++) {
                    batch.draw(wallTextureLeft, (wallFrom.getX() + positionOffsetX) * floorTexture.getWidth(), (j + positionOffsetY) * floorTexture.getHeight());
                }
                for (int j = wallFrom.getX(); j > wallTo.getX(); j--) {
                    batch.draw(wallTextureMid, (j + positionOffsetX) * floorTexture.getWidth(), (wallFrom.getY() + positionOffsetY) * floorTexture.getHeight());
                }
                for (int j = wallFrom.getY(); j >= wallTo.getY(); j--) {
                    batch.draw(wallTextureRight, (wallFrom.getX() + positionOffsetX) * floorTexture.getWidth(), (j + positionOffsetY) * floorTexture.getHeight());
                }
            }
        }
    }

    public void setRooms(Room[] rooms) {
        this.rooms = rooms;
    }

    public void dispose() {
        floorTexture.dispose();
        wallTextureMid.dispose();
    }
}
