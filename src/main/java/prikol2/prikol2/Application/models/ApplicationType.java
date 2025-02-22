package prikol2.prikol2.Application.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "application_type")
public class ApplicationType {
    @Id
    @Column(name = "code", length = 7)
    private String code;

    @Column(name = "name", nullable = false, length = 15)
    private String name;
}
