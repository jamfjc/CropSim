package farm.person;

import farm.crop.Crop;
import farm.crop.CropType;
import farm.field.Farm;
import farm.field.Plot;
import java.util.ArrayList;

public class Farmer {
    private final String name;
    private final Farm farm;
    private final ArrayList<Crop> harvestedCrops;
    
    public Farmer(String name, Farm farm) {
        if (name == null || name.isBlank() || farm == null) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.farm = farm;
        this.harvestedCrops = new ArrayList<>();
    }
    
    public String getName() {
        return name;
    }
    
    public ArrayList<Crop> getHarvestedCrops() {
        return harvestedCrops;
    }
    
    public void plantCrop(int row, int col, CropType type) {
        Plot plot = farm.getField()[row][col];
        if (!plot.isEmpty()) {
            throw new IllegalStateException();
        }
        plot.plant(new Crop(type));
    }
    
    public void waterCrop(int row, int col) {
        Plot plot = farm.getField()[row][col];
        if (!plot.isEmpty() && !plot.getCrop().getIsCropDead()) {
            plot.getCrop().water();
        }
    }
    
    public void harvestCrop(int row, int col) {
        Plot plot = farm.getField()[row][col];
        if (!plot.isEmpty() && plot.getCrop().isMature()) {
            Crop crop = plot.getCrop();
            if (crop.harvest()) {
                harvestedCrops.add(crop);
                plot.removeCrop();
            }
        }
    }
    
    public void waterCrops(CropType type) {
        Plot[][] field = farm.getField();
        for (Plot[] row : field) {
            for (Plot plot : row) {
                if (!plot.isEmpty() && plot.getCrop().getType() == type && !plot.getCrop().getIsCropDead()) {
                    plot.getCrop().water();
                }
            }
        }
    }
    
    public void harvestCrops(CropType type) {
        Plot[][] field = farm.getField();
        for (Plot[] row : field) {
            for (Plot plot : row) {
                if (!plot.isEmpty() && plot.getCrop().getType() == type && plot.getCrop().isMature()) {
                    Crop crop = plot.getCrop();
                    if (crop.harvest()) {
                        harvestedCrops.add(crop);
                        plot.removeCrop();
                    }
                }
            }
        }
    }
    
    public void cleanPlot(int row, int col) {
        Plot plot = farm.getField()[row][col];
        if (plot.hasDeadCrop()) {
            plot.removeCrop();
        }
    }
    
    public void simulateDay() {
        farm.simulateDay();
    }
    
    public void waterMostThirstyCrop() {
        Crop mostThirsty = farm.findMostThirstyCrop();
        if (mostThirsty != null) {
            mostThirsty.water();
        }
    }
}