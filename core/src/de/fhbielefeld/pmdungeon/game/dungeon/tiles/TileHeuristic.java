package de.fhbielefeld.pmdungeon.game.dungeon.tiles;

import com.badlogic.gdx.ai.pfa.Heuristic;
import com.badlogic.gdx.math.Vector2;

/**
 * Houses the heuristic used by the pathfinding algorithm
 */
public class TileHeuristic implements Heuristic<Tile> {

    /**
     * Heuristic used by the pathfinding algorithm
     *
     * @param node    From
     * @param endNode To
     * @return Distance between from and to tile
     */
    @Override
    public float estimate(Tile node, Tile endNode) {
        return Vector2.dst2(node.getX(), node.getY(), endNode.getX(), endNode.getY());
    }
}
