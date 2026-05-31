package Builder;

public class HouseBuilder implements Builder{
    private int levels;
    private House house;

    public HouseBuilder(int levels) {
        this.levels = levels;
        this.house = new House();
        house.setLevels(levels);
    }

    @Override
    public void resetBuild() {
        this.house = new House();
        house.setLevels(levels);
    }

    @Override
    public Property buildProperty() {
        return this.house;
    }

    @Override
    public void buildRooms(int num) {
        house.setRooms(num);
    }

    @Override
    public void buildWindows(int num) {
        house.setWindows(num);
    }

    @Override
    public void buildPool() {
        house.setPool(true);
    }
}
