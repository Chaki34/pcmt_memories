package pcmt.Alumni_Gallery.pcmt_memories.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pcmt.Alumni_Gallery.pcmt_memories.Entites.AlumniRegistration;
import pcmt.Alumni_Gallery.pcmt_memories.Services.AlumniRegistrationService;


@Controller
@RequiredArgsConstructor
public class AlumniRegistrationController {

    private final AlumniRegistrationService service;

    @PostMapping("/register")
    public String register(@ModelAttribute AlumniRegistration alumni) {

        service.save(alumni);

        return "redirect:/#register";
    }
}
