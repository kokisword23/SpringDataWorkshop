package softuni.workshop.data.entities;

import javax.persistence.*;

@Entity
@Table(name = "employees")
public class Employee extends BaseEntity{

    private int age;
    private String firstName;
    private String lastName;
    private Project project;

    public Employee() {
    }

    @Column
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
