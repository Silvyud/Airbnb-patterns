package Builder;

public class Director {
    private int levels;
    private int rooms;
    private int windows;

    private Builder builder;

    public Director(int levels, int rooms, int windows, Builder builder) {
        this.levels = levels;
        this.rooms = rooms;
        this.windows = windows;
        this.builder = builder;
    }

    public void setBuilder(Builder builder) {
        this.builder = builder;
    }

    public Property construct() {
        builder.resetBuild();
        builder.buildRooms(rooms);
        builder.buildWindows(windows);
        if (levels > 1) {
            builder.buildPool();
        }
        return builder.buildProperty();
    }
}
