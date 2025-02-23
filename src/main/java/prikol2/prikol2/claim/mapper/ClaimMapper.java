package prikol2.prikol2.claim.mapper;
import prikol2.prikol2.claim.models.Claim;
import prikol2.prikol2.claim.models.ClaimDto;

public class ClaimMapper {

    public static ClaimDto mapToClaimDto(Claim claim) {

        return ClaimDto.builder()
                .number(claim.getNumber())
                .applicantName(claim.getApplicantName())
                .passportNumber(claim.getPassportNumber())
                .typeId(claim.getTypeId())
                .statusId(claim.getStatusId())
                .departmentId(claim.getDepartmentId())
                .employeeLogin(claim.getEmployeeId())
                .build();
    }

    public static Claim maptoClaim(ClaimDto claimDto){
        return Claim.builder()
                .number(claimDto.getNumber())
                .applicantName(claimDto.getApplicantName())
                .passportNumber(claimDto.getPassportNumber())
                .typeId(claimDto.getTypeId())
                .statusId(claimDto.getStatusId())
                .departmentId(claimDto.getDepartmentId())
                .employeeId(claimDto.getEmployeeLogin())
                .build();
    }
}
