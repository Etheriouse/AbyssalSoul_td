package Math;

public enum Direction {
    right(1, 0),
    left(-1, 0),
    up(0, -1),
    down(0, 1),
    null_(0, 0);

    public int x;
    public int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }
}