package farm.field;

import farm.crop.Crop;

public class Farm {
    private final Plot[][] field;
    
    public Farm(int rows, int cols) {
        if (rows < 1 || cols < 1) {
            throw new IllegalArgumentException();
        }
        this.field = new Plot[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                field[i][j] = new Plot();
            }
        }
    }
    
    public Plot[][] getField() {
        return field;
    }
    
    public void simulateDay() {
        for (Plot[] row : field) {
            for (Plot plot : row) {
                if (!plot.isEmpty()) {
                    plot.getCrop().simulateDay();
                }
            }
        }
    }
    
    public Crop findMostThirstyCrop() {
        Crop mostThirsty = null;
        int lowestWater = Integer.MAX_VALUE;
        
        for (Plot[] row : field) {
            for (Plot plot : row) {
                if (!plot.isEmpty() && !plot.getCrop().getIsCropDead()) {
                    Crop crop = plot.getCrop();
                    if (crop.getWaterLevel() < lowestWater) {
                        lowestWater = crop.getWaterLevel();
                        mostThirsty = crop;
                    }
                }
            }
        }
        
        return mostThirsty;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                sb.append(field[i][j].toString());
                if (j < field[i].length - 1) {
                    sb.append(" ");
                }
            }
            if (i < field.length - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}