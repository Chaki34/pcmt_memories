package pcmt.Alumni_Gallery.pcmt_memories.DTOs;



public class FolderDTO {

    private String id;
    private String name;
    private String thumbnailUrl;

    public FolderDTO() {
    }

    public FolderDTO(String id, String name, String thumbnailUrl) {
        this.id = id;
        this.name = name;
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}