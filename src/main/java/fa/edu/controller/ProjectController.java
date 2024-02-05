package fa.edu.controller;

import fa.edu.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProjectController {

    @Autowired
    ProjectRepository projectRepository;

    @GetMapping("/project/list")
    public String showProjectsList(Model model) {
        model.addAttribute("projectList", projectRepository.findAll());
        return "project/list";
    }

    @GetMapping("/project/create")
    public String showCreateUI(Model model) {

        return "project/create";
    }
}
