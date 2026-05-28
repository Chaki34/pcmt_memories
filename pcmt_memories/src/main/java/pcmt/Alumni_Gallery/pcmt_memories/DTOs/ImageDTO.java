package pcmt.Alumni_Gallery.pcmt_memories.DTOs;

public class ImageDTO {

    // SMALL IMAGE
    private String thumbnailUrl;

    // FULL IMAGE
    private String fullImageUrl;

    // image | video
    private String type;

    private int gridX;
    private int gridY;

    public ImageDTO() {
    }

    public ImageDTO(
            String thumbnailUrl,
            String fullImageUrl,
            String type,
            int gridX,
            int gridY
    ) {
        this.thumbnailUrl = thumbnailUrl;
        this.fullImageUrl = fullImageUrl;
        this.type = type;
        this.gridX = gridX;
        this.gridY = gridY;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getFullImageUrl() {
        return fullImageUrl;
    }

    public void setFullImageUrl(String fullImageUrl) {
        this.fullImageUrl = fullImageUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getGridX() {
        return gridX;
    }

    public void setGridX(int gridX) {
        this.gridX = gridX;
    }

    public int getGridY() {
        return gridY;
    }

    public void setGridY(int gridY) {
        this.gridY = gridY;
    }
}