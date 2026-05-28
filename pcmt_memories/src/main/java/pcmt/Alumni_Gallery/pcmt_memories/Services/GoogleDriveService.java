
        package pcmt.Alumni_Gallery.pcmt_memories.Services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pcmt.Alumni_Gallery.pcmt_memories.DTOs.FolderDTO;
import pcmt.Alumni_Gallery.pcmt_memories.DTOs.ImageDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoogleDriveService {

    @Value("${google.drive.api.key}")
    private String apiKey;

    @Value("${google.drive.folder.id}")
    private String parentFolderId;

    private final RestTemplate restTemplate =
            new RestTemplate();

    private final ObjectMapper objectMapper =
            new ObjectMapper();

    // =========================================
    // FETCH ALL GOOGLE DRIVE FOLDERS
    // =========================================
    public List<FolderDTO> getFolders() {

        List<FolderDTO> folders =
                new ArrayList<>();

        try {

            // DEBUG
            System.out.println("API KEY: " + apiKey);
            System.out.println("PARENT FOLDER ID: " + parentFolderId);

            // GOOGLE DRIVE API URL
            String url =
                    "https://www.googleapis.com/drive/v3/files"
                            + "?q='"
                            + parentFolderId
                            + "' in parents "
                            + "and mimeType='application/vnd.google-apps.folder'"
                            + "&fields=files(id,name)"
                            + "&key="
                            + apiKey;

            System.out.println("REQUEST URL:");
            System.out.println(url);

            // API CALL
            String response =
                    restTemplate.getForObject(url, String.class);

            // DEBUG RESPONSE
            System.out.println("GOOGLE RESPONSE:");
            System.out.println(response);

            // JSON PARSE
            JsonNode root =
                    objectMapper.readTree(response);

            JsonNode files =
                    root.get("files");

            // CHECK FILES
            if (files != null && files.isArray()) {

                for (JsonNode file : files) {

                    String folderId =
                            file.get("id").asText();

                    String folderName =
                            file.get("name").asText();

                    // FETCH THUMBNAIL
                    String thumbnail =
                            getFolderThumbnail(folderId);

                    // ADD DTO
                    folders.add(
                            new FolderDTO(
                                    folderId,
                                    folderName,
                                    thumbnail
                            )
                    );

                    // DEBUG
                    System.out.println(
                            "Folder Added: "
                                    + folderName
                    );
                }
            }

            System.out.println(
                    "TOTAL FOLDERS FOUND: "
                            + folders.size()
            );

        } catch (Exception e) {

            System.out.println("ERROR FETCHING FOLDERS");

            e.printStackTrace();
        }

        return folders;
    }

    // =========================================
    // FETCH FIRST IMAGE AS THUMBNAIL
    // =========================================
    private String getFolderThumbnail(String folderId) {

        try {

            String url =
                    "https://www.googleapis.com/drive/v3/files"
                            + "?q='"
                            + folderId
                            + "' in parents "
                            + "and mimeType contains 'image/'"
                            + "&fields=files(id,name,mimeType)"
                            + "&pageSize=1"
                            + "&key="
                            + apiKey;

            System.out.println("THUMBNAIL URL:");
            System.out.println(url);

            String response =
                    restTemplate.getForObject(url, String.class);

            JsonNode root =
                    objectMapper.readTree(response);

            JsonNode files =
                    root.get("files");

            // IF IMAGE EXISTS
            if (files != null && files.size() > 0) {

                String imageId =
                        files.get(0).get("id").asText();

                System.out.println(
                        "Thumbnail Found: "
                                + imageId
                );

                return "https://drive.google.com/thumbnail?id="
                        + imageId
                        + "&sz=w1000";
            }

        } catch (Exception e) {

            System.out.println(
                    "ERROR FETCHING THUMBNAIL"
            );

            e.printStackTrace();
        }

        // DEFAULT IMAGE
        return "https://via.placeholder.com/600x400.png?text=No+Image";
    }

    public List<ImageDTO> getImagesFromFolder(String folderId) {

        List<ImageDTO> mediaList = new ArrayList<>();

        try {

            String url =
                    "https://www.googleapis.com/drive/v3/files"
                            + "?q='"
                            + folderId
                            + "' in parents and trashed=false"
                            + "&fields=files(id,name,mimeType)"
                            + "&pageSize=200"
                            + "&key="
                            + apiKey;

            String response =
                    restTemplate.getForObject(url, String.class);

            JsonNode root =
                    objectMapper.readTree(response);

            JsonNode files =
                    root.get("files");

            if (files != null && files.isArray()) {

                for (JsonNode file : files) {

                    String fileId =
                            file.get("id").asText();

                    String mimeType =
                            file.get("mimeType").asText();

                    String type =
                            mimeType.startsWith("video/")
                                    ? "video"
                                    : "image";

                    // =========================
                    // FIXED GOOGLE CDN URLS
                    // =========================

                    String thumbnailUrl =
                            "https://lh3.googleusercontent.com/d/"
                                    + fileId
                                    + "=w500";

                    String fullImageUrl =
                            "https://lh3.googleusercontent.com/d/"
                                    + fileId
                                    + "=w2000";

                    // VIDEO PREVIEW
                    if (type.equals("video")) {

                        // VIDEO THUMBNAIL
                        thumbnailUrl =
                                "https://drive.google.com/uc?export=view&id="
                                        + fileId;

                        // VIDEO PLAYER
                        fullImageUrl =
                                "https://drive.google.com/file/d/"
                                        + fileId
                                        + "/preview";
                    }

                    ImageDTO dto =
                            new ImageDTO(
                                    thumbnailUrl,
                                    fullImageUrl,
                                    type,
                                    0,
                                    0
                            );

                    mediaList.add(dto);

                    System.out.println("--------------------------------");
                    System.out.println("TYPE : " + type);
                    System.out.println("THUMBNAIL : " + thumbnailUrl);
                    System.out.println("FULL URL : " + fullImageUrl);
                }
            }

            System.out.println("TOTAL MEDIA FOUND : " + mediaList.size());

        } catch (Exception e) {

            System.out.println("ERROR FETCHING GOOGLE DRIVE MEDIA");

            e.printStackTrace();
        }

        return mediaList;
    }
}

