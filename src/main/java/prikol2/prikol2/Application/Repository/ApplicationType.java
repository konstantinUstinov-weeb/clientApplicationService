package prikol2.prikol2.Application.Repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "application_type")
public class ApplicationType {
    @Id
    private String code;

    @Column
    private String name;
}
