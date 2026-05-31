package Builder;

public interface Builder {
    void resetBuild();
    Property buildProperty();
    void buildRooms(int num);
    void buildWindows(int num);
    void buildPool();
}
