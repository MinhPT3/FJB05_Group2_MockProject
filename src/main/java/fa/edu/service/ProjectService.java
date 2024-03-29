package fa.edu.service;

import fa.edu.dto.ProjectDTO;
import fa.edu.entities.Project;
import fa.edu.entities.Working;
import fa.edu.repository.ProjectRepository;
import fa.edu.repository.WorkingRepository;
import org.springframework.stereotype.Service;


@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final WorkingRepository workingRepository;

    public ProjectService(ProjectRepository projectRepository, WorkingRepository workingRepository) {
        this.projectRepository = projectRepository;
        this.workingRepository = workingRepository;
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Working createWorking(Working working) {
        return workingRepository.save(working);
    }
    public ProjectDTO convertToDTO(Project project) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setNameProject(project.getNameProject());
        projectDTO.setProjectCode(project.getProjectCode());
        projectDTO.setStartDate(project.getStartDate());
        projectDTO.setEndDate(project.getEndDate());

        return projectDTO;
    }

    public ProjectDTO getProjectDTOById(int id) {
        Project project = projectRepository.findById(id).orElse(null);
        if (project == null) {
            return null;
        }
        return convertToDTO(project);
    }

}
