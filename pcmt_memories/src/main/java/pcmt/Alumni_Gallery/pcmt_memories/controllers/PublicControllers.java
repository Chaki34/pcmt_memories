package pcmt.Alumni_Gallery.pcmt_memories.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pcmt.Alumni_Gallery.pcmt_memories.DTOs.FolderDTO;
import pcmt.Alumni_Gallery.pcmt_memories.DTOs.ImageDTO;
import pcmt.Alumni_Gallery.pcmt_memories.Services.GoogleDriveService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller

public class PublicControllers {

    @Autowired
    private GoogleDriveService  googleDriveService;;

    @GetMapping("/")
    public String  Test (){
        return "home";
    }

    // SHOW ALL FOLDERS
    @GetMapping("/alumni-search")
    public String alumniSearch(Model model) {

        List<FolderDTO> folders =
                googleDriveService.getFolders();

        System.out.println("folders size: " + folders.size());

        model.addAttribute("folders", folders);

        return "alumni-search";
    }


    // SEARCH FOLDERS
    @GetMapping("/search")
    public String searchFolders(

            @RequestParam("query") String query,
            Model model

    ) {

        List<FolderDTO> folders =
                googleDriveService.getFolders();

        List<FolderDTO> filteredFolders =
                folders.stream()

                        .filter(folder ->

                                folder.getName()
                                        .toLowerCase()
                                        .contains(query.toLowerCase())

                        )

                        .collect(Collectors.toList());

        model.addAttribute("folders", filteredFolders);

        model.addAttribute("searchQuery", query);

        return "alumni-search";
    }


    @GetMapping("/gallery/{folderId}")
    public String galleryPage(

            @PathVariable String folderId,
            Model model

    ) {

        // FETCH IMAGES + VIDEOS
        List<ImageDTO> alumni =
                googleDriveService.getImagesFromFolder(folderId);

        // SEND TO THYMELEAF
        model.addAttribute("alumni", alumni);

        return "search-result";
    }


}
