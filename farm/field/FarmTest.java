package farm.field;

import farm.crop.Crop;
import farm.crop.CropType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FarmTest {
    
    @Test
    public void testFarmCreation() {
        Farm farm = new Farm(3, 4);
        assertEquals(3, farm.getField().length);
        assertEquals(4, farm.getField()[0].length);
        
        for (Plot[] row : farm.getField()) {
            for (Plot plot : row) {
                assertTrue(plot.isEmpty());
            }
        }
    }
    
    @Test
    public void testSimulateDay() {
        Farm farm = new Farm(2, 2);
        farm.getField()[0][0].plant(new Crop(CropType.LETTUCE));
        
        int initialWater = farm.getField()[0][0].getCrop().getWaterLevel();
        farm.simulateDay();
        assertEquals(initialWater - 1, farm.getField()[0][0].getCrop().getWaterLevel());
    }
    
    @Test
    public void testFindMostThirstyCrop() {
        Farm farm = new Farm(2, 2);
        farm.getField()[0][0].plant(new Crop(CropType.LETTUCE));
        farm.getField()[0][1].plant(new Crop(CropType.CORN));
        
        farm.getField()[0][0].getCrop().simulateDay();
        
        Crop thirstiest = farm.findMostThirstyCrop();
        assertEquals(farm.getField()[0][0].getCrop(), thirstiest);
    }
    
    @Test
    public void testToString() {
        Farm farm = new Farm(2, 2);
        String expected = "E E\nE E";
        assertEquals(expected, farm.toString());
        
        farm.getField()[0][0].plant(new Crop(CropType.LETTUCE));
        farm.getField()[1][1].plant(new Crop(CropType.CORN));
        expected = "L E\nE C";
        assertEquals(expected, farm.toString());
    }
}