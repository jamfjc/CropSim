package farm.crop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CropTest {
    
    @Test
    public void testWaterDecayAfterSimulateDay() {
        Crop crop = new Crop(CropType.LETTUCE);
        int initialWater = crop.getWaterLevel();
        crop.simulateDay();
        assertEquals(initialWater - 1, crop.getWaterLevel());
    }
    
    @Test
    public void testGrowthOccursWhenWatered() {
        Crop crop = new Crop(CropType.LETTUCE);
        crop.water();
        int initialGrowth = crop.getGrowthLevel();
        crop.simulateDay();
        assertEquals(initialGrowth + CropType.LETTUCE.getGrowthRate(), crop.getGrowthLevel());
    }
    
    @Test
    public void testDifferentDeathThresholds() {
        Crop strawberry = new Crop(CropType.STRAWBERRY);
        strawberry.simulateDay();
        strawberry.simulateDay();
        strawberry.simulateDay();
        assertTrue(strawberry.getIsCropDead());
        
        Crop lettuce = new Crop(CropType.LETTUCE);
        lettuce.simulateDay();
        lettuce.simulateDay();
        lettuce.simulateDay();
        assertFalse(lettuce.getIsCropDead());
        lettuce.simulateDay();
        assertTrue(lettuce.getIsCropDead());
        
        Crop corn = new Crop(CropType.CORN);
        corn.simulateDay();
        corn.simulateDay();
        corn.simulateDay();
        corn.simulateDay();
        assertFalse(corn.getIsCropDead());
        corn.simulateDay();
        assertTrue(corn.getIsCropDead());
    }
    
    @Test
    public void testIsMature() {
        Crop crop = new Crop(CropType.LETTUCE);
        assertFalse(crop.isMature());
        
        for (int i = 0; i < 10; i++) {
            crop.water();
            crop.simulateDay();
        }
        assertTrue(crop.isMature());
    }
    
    @Test
    public void testHarvestSuccess() {
        Crop crop = new Crop(CropType.LETTUCE);
        
        for (int i = 0; i < 10; i++) {
            crop.water();
            crop.simulateDay();
        }
        
        assertTrue(crop.isMature());
        assertTrue(crop.harvest());
        assertEquals(0, crop.getGrowthLevel());
        assertEquals(2, crop.getWaterLevel());
    }
    
    @Test
    public void testHarvestFailsWhenNotMatureOrDead() {
        Crop immature = new Crop(CropType.LETTUCE);
        assertFalse(immature.harvest());
        
        Crop dead = new Crop(CropType.STRAWBERRY);
        dead.simulateDay();
        dead.simulateDay();
        dead.simulateDay();
        assertFalse(dead.harvest());
    }
}