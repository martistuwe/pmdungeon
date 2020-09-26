package de.fhbielefeld.pmdungeon.game.dungeon.tiles;

import com.badlogic.gdx.ai.pfa.Heuristic;
import com.badlogic.gdx.math.Vector2;

public class TileHeuristic implements Heuristic<Tile> {

    @Override
    public float estimate(Tile node, Tile endNode) {
        return Vector2.dst2(node.getX(), node.getY(), endNode.getX(), endNode.getY());
    }
}
