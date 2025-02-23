package prikol2.prikol2.claim.models;

import lombok.*;
import org.springframework.stereotype.Component;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Component
public class ClaimDto {
    private String passportNumber;
    private String applicantName;
    private String employeeLogin;
    private String departmentId;
    private String statusId;
    private String typeId;
    private String number;
}
