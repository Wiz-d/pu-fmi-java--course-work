package map;

public class Block {

    private String blockMarker;
    private boolean isBlockRedTerritory = false;
    private boolean isBlockBlackTerritory = false;

    public Block(String blockMarker) {
        this.blockMarker = blockMarker;
    }

    public void setIsBlockRedTerritory(boolean isBlockRedTerritory) {
        this.isBlockRedTerritory = isBlockRedTerritory;
    }

    public void setIsBlockBlackTerritory(boolean isBlockBlackTerritory) {
        this.isBlockBlackTerritory = isBlockBlackTerritory;
    }

    public boolean getIsBlockRedTerritory() {
        return this.isBlockRedTerritory;
    }

    public boolean getIsBlockBlackTerritory() {
        return this.isBlockBlackTerritory;
    }

    public void setBlockMarker(String blockMarker) {
        this.blockMarker = blockMarker;
    }

    public String getBlockMarker() {
        return this.blockMarker;
    }


}
