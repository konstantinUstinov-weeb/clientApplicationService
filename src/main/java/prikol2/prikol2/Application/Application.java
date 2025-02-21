package prikol2.prikol2.Application;


import jakarta.persistence.*;
import prikol2.prikol2.Application.Repository.ApplicationStatus;
import prikol2.prikol2.Application.Repository.ApplicationType;
import prikol2.prikol2.Application.Repository.Department;
import prikol2.prikol2.User.User;

@Entity
@Table(name = "application")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long passportId;
    @Column
    private String applicantName;
    @Column
    private String userLogin;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "department_code")
    private Department department;

    @OneToOne
    @JoinColumn(name = "application_status")
    private ApplicationStatus applicationStatus;

    @OneToOne
    @JoinColumn(name = "application_type")
    private ApplicationType applicationType;




    public Application() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(ApplicationStatus applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public ApplicationType getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(ApplicationType applicationType) {
        this.applicationType = applicationType;
    }

    public Long getId() {
        return id;
    }







    public Long getPassportId() {
        return passportId;
    }

    public void setPassportId(Long passportId) {
        this.passportId = passportId;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }
}
