package fa.edu.model;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProjectDTO {

    private String name;
    private String code;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private Integer pm;
    private Integer qa;
    private List<Integer> ba;
    private List<Integer> tester;
    private List<Integer> dev;
    private List<Integer> techLead;
    private List<Integer> techConsult;

}
