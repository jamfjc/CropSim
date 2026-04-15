package farm.crop;

public enum CropType {
    LETTUCE(3, 30),
    CORN(5, 75),
    STRAWBERRY(6, 60);
    
    private final int growthRate;
    private final int possibleMaturity;
    
    CropType(int growthRate, int possibleMaturity) {
        this.growthRate = growthRate;
        this.possibleMaturity = possibleMaturity;
    }
    
    public int getGrowthRate() {
        return growthRate;
    }
    
    public int getPossibleMaturity() {
        return possibleMaturity;
    }
}