package farm.person;

import farm.crop.Crop;
import farm.crop.CropType;
import farm.field.Farm;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FarmerTest {
    
    @Test
    public void testPlantCropAndWater() {
        Farm farm = new Farm(3, 3);
        Farmer farmer = new Farmer("John", farm);
        
        farmer.plantCrop(0, 0, CropType.LETTUCE);
        assertFalse(farm.getField()[0][0].isEmpty());
        assertEquals(CropType.LETTUCE, farm.getField()[0][0].getCrop().getType());
        
        int initialWater = farm.getField()[0][0].getCrop().getWaterLevel();
        farmer.waterCrop(0, 0);
        assertTrue(farm.getField()[0][0].getCrop().getWaterLevel() > initialWater);
    }
    
    @Test
    public void testHarvestRemovesMatureCrop() {
        Farm farm = new Farm(3, 3);
        Farmer farmer = new Farmer("John", farm);
        
        farmer.plantCrop(0, 0, CropType.LETTUCE);
        
        for (int i = 0; i < 10; i++) {
            farmer.waterCrop(0, 0);
            farmer.simulateDay();
        }
        
        assertTrue(farm.getField()[0][0].getCrop().isMature());
        farmer.harvestCrop(0, 0);
        assertTrue(farm.getField()[0][0].isEmpty());
        assertEquals(1, farmer.getHarvestedCrops().size());
    }
    
    @Test
    public void testCleanDeadCrop() {
        Farm farm = new Farm(3, 3);
        Farmer farmer = new Farmer("John", farm);
        
        farmer.plantCrop(0, 0, CropType.STRAWBERRY);
        
        farmer.simulateDay();
        farmer.simulateDay();
        farmer.simulateDay();
        
        assertTrue(farm.getField()[0][0].hasDeadCrop());
        farmer.cleanPlot(0, 0);
        assertTrue(farm.getField()[0][0].isEmpty());
    }
    
    @Test
    public void testStory() {
        testStoryCase(CropType.LETTUCE, CropType.CORN, 2, 14, 1);
        testStoryCase(CropType.LETTUCE, CropType.CORN, 2, 15, 2);
        testStoryCase(CropType.STRAWBERRY, CropType.CORN, 4, 27, 0);
        testStoryCase(CropType.STRAWBERRY, CropType.CORN, 4, 28, 1);
        testStoryCase(CropType.STRAWBERRY, CropType.LETTUCE, 5, 8, 0);
    }
    
    private void testStoryCase(CropType type1, CropType type2, int interval, int days, int expected) {
        Farm farm = new Farm(3, 3);
        Farmer farmer = new Farmer("John", farm);
        
        farmer.plantCrop(0, 0, type1);
        farmer.plantCrop(0, 1, type2);
        
        for (int day = 1; day <= days; day++) {
            if (day % interval == 0) {
                farmer.waterCrop(0, 0);
                farmer.waterCrop(0, 1);
            }
            farmer.simulateDay();
        }
        
        int initialHarvested = farmer.getHarvestedCrops().size();
        farmer.harvestCrop(0, 0);
        farmer.harvestCrop(0, 1);
        
        assertEquals(expected, farmer.getHarvestedCrops().size() - initialHarvested);
    }
    
    @Test
    public void testWaterMostThirstyCrop() {
        Farm farm = new Farm(3, 3);
        Farmer farmer = new Farmer("John", farm);
        
        farmer.plantCrop(0, 0, CropType.LETTUCE);
        farmer.plantCrop(0, 1, CropType.CORN);
        
        farmer.simulateDay();
        farmer.waterCrop(0, 1);
        
        int lettuceWater = farm.getField()[0][0].getCrop().getWaterLevel();
        int cornWater = farm.getField()[0][1].getCrop().getWaterLevel();
        
        farmer.waterMostThirstyCrop();
        
        assertTrue(farm.getField()[0][0].getCrop().getWaterLevel() > lettuceWater);
        assertEquals(cornWater, farm.getField()[0][1].getCrop().getWaterLevel());
    }
}