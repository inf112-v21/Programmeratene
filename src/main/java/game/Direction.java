package game;

public enum Direction {
    NORTH,EAST,SOUTH,WEST;

    private Direction reverse;
    private Direction right;
    private Direction left;

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
}
