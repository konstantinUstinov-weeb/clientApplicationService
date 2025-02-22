package prikol2.prikol2.Application.mapper;

import prikol2.prikol2.Application.models.Application;
import prikol2.prikol2.Application.models.ApplicationDto;

public class ApplicationMapper {

    public static ApplicationDto mapToApplicationDto(Application application) {

        return ApplicationDto.builder()
                .number(application.getNumber())
                .applicantName(application.getApplicantName())
                .passportNumber(application.getPassportNumber())
                .typeId(application.getTypeId())
                .statusId(application.getStatusId())
                .departmentId(application.getDepartmentId())
                .userLogin(application.getUserId())
                .build();
    }

    public static Application maptoApplication(ApplicationDto applicationDto){
        return Application.builder()
                .number(applicationDto.getNumber())
                .applicantName(applicationDto.getApplicantName())
                .passportNumber(applicationDto.getPassportNumber())
                .typeId(applicationDto.getTypeId())
                .statusId(applicationDto.getStatusId())
                .departmentId(applicationDto.getDepartmentId())
                .userId(applicationDto.getUserLogin())
                .build();
    }
}
