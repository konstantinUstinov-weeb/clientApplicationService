package prikol2.prikol2.Application.models;
import jakarta.persistence.*;
import lombok.*;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "application")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", nullable = false, unique = true)
    private String number;
    @Column(name = "passport_number", nullable = false)
    private String passportNumber;
    @Column(name = "applicant_name", nullable = false)
    private String applicantName;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "department_id", nullable = false, length = 7)
    private String departmentId;
    @Column(name = "status_id", nullable = false, length = 15)
    private String statusId;
    @Column(name = "type_id", nullable = false, length = 7)
    private String typeId;
}
