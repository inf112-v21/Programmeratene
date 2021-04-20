package game;

import com.badlogic.gdx.math.Vector2;

public enum Direction {
    NORTH,EAST,SOUTH,WEST, orientation;

    private Direction reverse;
    private Direction right;
    private Direction left;
    private Vector2 vector;

    static {
        NORTH.reverse = SOUTH;
        NORTH.left = WEST;
        NORTH.right = EAST;

        EAST.reverse = WEST;
        EAST.left = NORTH;
        EAST.right = SOUTH;

        WEST.reverse = EAST;
        WEST.left = SOUTH;
        WEST.right = NORTH;

        SOUTH.reverse = NORTH;
        SOUTH.left = EAST;
        SOUTH.right = WEST;

        NORTH.vector = new Vector2(0,1);
        SOUTH.vector = new Vector2(0,-1);
        EAST.vector = new Vector2(1,0);
        WEST.vector = new Vector2(-1,0);

    }

    public Direction getReverse() {
        return reverse;
    }

    public Direction getRight() {
        return right;
    }

    public Direction getLeft() {
        return left;
    }
    public Vector2 getVector() {
        return vector;
    }
}
