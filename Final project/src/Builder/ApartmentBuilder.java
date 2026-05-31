package Builder;

public class ApartmentBuilder implements Builder{
    private int level;
    private Apartment apartment;
    private final int levelCapacity;
    private int PropertiesInLevel;

     public ApartmentBuilder(int levelCapacity) {
        this.levelCapacity = levelCapacity;
        this.PropertiesInLevel = 0;
    }

    @Override
    public void resetBuild() {
        this.apartment = new Apartment();
        apartment.setLevel(level);
    }

    @Override
    public Property buildProperty() {
         PropertiesInLevel++;
         if (PropertiesInLevel == levelCapacity) {
             level++;
             PropertiesInLevel = 0;
         }
        return this.apartment;
    }

    @Override
    public void buildRooms(int num) {
        apartment.setRooms(num);
    }

    @Override
    public void buildWindows(int num) {
        apartment.setWindows(num);
    }
    @Override
    public void buildPool() {
        apartment.setPool(true);
    }
}