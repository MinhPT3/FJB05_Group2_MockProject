package fa.edu.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Integer id;

    private String name;
    private String code;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @OneToMany(mappedBy = "project")
    private List<ClaimRequest> claimRequests = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.REMOVE)
    private List<Working> workingList = new ArrayList<>();

    public Working getPM() {
        Working pm = new Working();

        for (Working w : workingList) {
            if (w.getRole() == RoleEnum.PM) {
                pm = w;
            }
        }
        return pm;
    }

    public Working getQA() {
        Working qa = new Working();

        for (Working w : workingList) {
            if (w.getRole() == RoleEnum.QA) {
                qa = w;
            }
        }
        return qa;
    }
}