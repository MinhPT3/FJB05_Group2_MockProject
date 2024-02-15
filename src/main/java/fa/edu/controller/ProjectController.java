package fa.edu.controller;

import fa.edu.entities.Project;
import fa.edu.entities.RoleEnum;
import fa.edu.entities.Working;
import fa.edu.model.ProjectDTO;
import fa.edu.repository.ProjectRepository;
import fa.edu.repository.StaffRepository;
import fa.edu.repository.WorkingRepository;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProjectController {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    WorkingRepository workingRepository;

    @GetMapping("/project/list")
    public String showProjectsList(Model model) {
        model.addAttribute("projectList", projectRepository.findAll());
        return "project/list";
    }

    @GetMapping("/project/create")
    public String showCreateUI(Model model) {
        model.addAttribute("staffList", staffRepository.findAll());
        model.addAttribute("projectDTO", new ProjectDTO());

        return "project/create";
    }

    @PostMapping("/project/create")
    public String createProject(@ModelAttribute("projectDTO") ProjectDTO projectDTO) {
//        System.out.println(projectDTO);
        Project project = new Project();

        project.setName(projectDTO.getName());
        project.setCode(projectDTO.getCode());
        project.setStartDate(projectDTO.getStartDate());
        project.setEndDate(projectDTO.getEndDate());

        projectRepository.save(project);

        Working pm = new Working();
        pm.setStaff(staffRepository.findById(projectDTO.getPm()).orElse(null));
        pm.setProject(project);
        pm.setRole(RoleEnum.PM);
        workingRepository.save(pm);

        Working qa = new Working();
        qa.setStaff(staffRepository.findById(projectDTO.getQa()).orElse(null));
        qa.setProject(project);
        qa.setRole(RoleEnum.QA);
        workingRepository.save(qa);

        for (Integer baId : projectDTO.getBa()) {
            Working ba = new Working();
            ba.setStaff(staffRepository.findById(baId).orElse(null));
            ba.setProject(project);
            ba.setRole(RoleEnum.BA);
            workingRepository.save(ba);
        }

        for (Integer testerId : projectDTO.getTester()) {
            Working tester = new Working();
            tester.setStaff(staffRepository.findById(testerId).orElse(null));
            tester.setProject(project);
            tester.setRole(RoleEnum.TESTER);
            workingRepository.save(tester);
        }

        for (Integer devId : projectDTO.getDev()) {
            Working dev = new Working();
            dev.setStaff(staffRepository.findById(devId).orElse(null));
            dev.setProject(project);
            dev.setRole(RoleEnum.DEV);
            workingRepository.save(dev);
        }

        for (Integer leadId : projectDTO.getTechLead()) {
            Working techlead = new Working();
            techlead.setStaff(staffRepository.findById(leadId).orElse(null));
            techlead.setProject(project);
            techlead.setRole(RoleEnum.TECH_LEAD);
            workingRepository.save(techlead);
        }

        for (Integer consultId : projectDTO.getTechConsult()) {
            Working techConsult = new Working();
            techConsult.setStaff(staffRepository.findById(consultId).orElse(null));
            techConsult.setProject(project);
            techConsult.setRole(RoleEnum.TECH_CONSULT);
            workingRepository.save(techConsult);
        }

        return "redirect:/project/list";
    }

    @GetMapping("/project/update")
    public String showUpdateUI(Model model) {
        model.addAttribute("staffList", staffRepository.findAll());
        model.addAttribute("projectDTO", new ProjectDTO());

        return "project/update";
    }
}
