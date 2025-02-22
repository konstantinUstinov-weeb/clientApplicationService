package prikol2.prikol2.Application.models;

import lombok.*;
import org.springframework.stereotype.Component;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Component
public class ApplicationDto {
    private String passportNumber;
    private String applicantName;
    private String userLogin;
    private String departmentId;
    private String statusId;
    private String typeId;
    private String number;
}
