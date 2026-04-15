package farm.field;

import farm.crop.Crop;

public class Plot {
    private Crop crop;
    
    public Plot() {
        this.crop = null;
    }
    
    public Crop getCrop() {
        return crop;
    }
    
    public boolean isEmpty() {
        return crop == null;
    }
    
    public void plant(Crop crop) {
        if (crop == null) {
            throw new IllegalArgumentException();
        }
        if (!isEmpty()) {
            throw new IllegalStateException();
        }
        this.crop = crop;
    }
    
    public void removeCrop() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        this.crop = null;
    }
    
    public boolean hasDeadCrop() {
        return crop != null && crop.getIsCropDead();
    }
    
    @Override
    public String toString() {
        if (isEmpty()) {
            return "E";
        }
        return crop.toString();
    }
}