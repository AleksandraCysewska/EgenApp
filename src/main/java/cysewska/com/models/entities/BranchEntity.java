package cysewska.com.models.entities;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Created by cysewskaa on 2016-09-05.
 */


@Entity
@Table(name = "BRANCH")
public class BranchEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    @Column(name = "BRANCH_ID")
    Long id;
    @Column(name = "BRANCH_NAME", unique = true)
    @Size(min = 3, message = "Nazwa musi być unikalna i zawierać minimum jeden znak.")
    String name;


    public BranchEntity(Long id, String name) {
        this.id = id;
        this.name = name;

    }
    public BranchEntity() {

    }
    @OneToMany(mappedBy="branchEntity", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    Set<DepartmentEntity> departments;

    @Override
    public String toString() {
        return "BranchEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", departments=" + departments +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<DepartmentEntity> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<DepartmentEntity> departments) {
        this.departments = departments;
    }
}
