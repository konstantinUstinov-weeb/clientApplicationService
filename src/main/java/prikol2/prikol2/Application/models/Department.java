package prikol2.prikol2.Application.models;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "department")
public class Department {
    @Id
    @Column(name = "code", length = 7)
    private String code;

    @Column(name = "name", nullable = false, length = 15)
    private String name;
}
