package farm.field;

import farm.crop.Crop;
import farm.crop.CropType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlotTest {
    
    @Test
    public void testPlotOperations() {
        Plot plot = new Plot();
        assertTrue(plot.isEmpty());
        
        Crop crop = new Crop(CropType.LETTUCE);
        plot.plant(crop);
        assertFalse(plot.isEmpty());
        assertEquals(crop, plot.getCrop());
        
        plot.removeCrop();
        assertTrue(plot.isEmpty());
    }
    
    @Test
    public void testHasDeadCrop() {
        Plot plot = new Plot();
        assertFalse(plot.hasDeadCrop());
        
        Crop crop = new Crop(CropType.STRAWBERRY);
        plot.plant(crop);
        assertFalse(plot.hasDeadCrop());
        
        crop.simulateDay();
        crop.simulateDay();
        crop.simulateDay();
        
        assertTrue(plot.hasDeadCrop());
    }
    
    @Test
    public void testToString() {
        Plot plot = new Plot();
        assertEquals("E", plot.toString());
        
        plot.plant(new Crop(CropType.LETTUCE));
        assertEquals("L", plot.toString());
    }
}