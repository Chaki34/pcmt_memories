package pcmt.Alumni_Gallery.pcmt_memories.controllers;




import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pcmt.Alumni_Gallery.pcmt_memories.Services.AlumniRegistrationService;

@Controller
@RequiredArgsConstructor
public class AdminAlumniController {

    private final AlumniRegistrationService service;

    @GetMapping("/memberslist")
    public String viewAlumni(Model model) {
        model.addAttribute("alumniList", service.getAll());
        return "member-alumni";
    }
}
