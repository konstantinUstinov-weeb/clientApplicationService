package prikol2.prikol2.Application.Repository;


import jakarta.persistence.*;


@Entity
@Table(name = "application_status")
public class ApplicationStatus {
    @Id
    private String code;

    @Column
    private String name;
}
