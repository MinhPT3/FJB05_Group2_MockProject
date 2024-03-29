package fa.edu.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private Integer id;
    private String name;
    private String email;
    private String rank;
    private Double salary;
    private String department;
    private String password;
    private String rePassword;
    @OneToMany(mappedBy = "staff")

    @JsonIgnore
    private List<Claim> claims = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "staff")
    private List<Working> workings = new ArrayList<>();
}
