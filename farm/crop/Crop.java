package farm.crop;

public class Crop {
    private final CropType type;
    private int growthLevel;
    private int waterLevel;
    private boolean isCropDead;
    private int dryDays;
    
    public Crop(CropType type) {
        if (type == null) {
            throw new IllegalArgumentException("`type` cannot be `null`.");
        }
        this.type = type;
        this.growthLevel = 0;
        this.waterLevel = 2;
        this.isCropDead = false;
        this.dryDays = 0;
    }
    
    public CropType getType() {
        return type;
    }
    
    public int getGrowthLevel() {
        return growthLevel;
    }
    
    public int getWaterLevel() {
        return waterLevel;
    }
    
    public boolean getIsCropDead() {
        return isCropDead;
    }
    
    public void water() {
        if (waterLevel + 2 <= 10) {
            waterLevel += 2;
        } else {
            waterLevel = 10;
        }
        dryDays = 0;
    }
    
    public void simulateDay() {
        if (waterLevel > 0 && !isCropDead) {
            growthLevel = Math.min(100, growthLevel + type.getGrowthRate());
            dryDays = 0; // Reset dry days when there's water
        } else {
            dryDays++;
            int maxDryDays = switch (type) {
                case STRAWBERRY -> 0;
                case LETTUCE -> 1;
                case CORN -> 2;
            };
            if (dryDays > maxDryDays) {
                isCropDead = true;
            }
        }
        
        waterLevel = Math.max(0, waterLevel - 1);
    }
    
    public boolean isMature() {
        return !isCropDead && growthLevel >= type.getPossibleMaturity();
    }
    
    public boolean harvest() {
        if (isMature()) {
            growthLevel = 0;
            waterLevel = 2;
            dryDays = 0;
            return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        if (isCropDead) {
            return "D";
        }
        return switch (type) {
            case LETTUCE -> "L";
            case CORN -> "C";
            case STRAWBERRY -> "S";
        };
    }
}