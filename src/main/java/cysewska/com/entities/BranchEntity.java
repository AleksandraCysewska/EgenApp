package cysewska.com.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by cysewskaa on 2016-09-05.
 */

@Getter
@Setter
@Entity
@Table(name = "BRANCH")
public class BranchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "BRANCH_ID")
    Long id;
    @Column(name = "BRANCH_NAME")
    String name;

    @OneToMany(mappedBy="branchEntity", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    Set<DepartmentEntity> departments;
}
