package prikol2.prikol2.claim.service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import prikol2.prikol2.claim.models.Claim;
import prikol2.prikol2.claim.models.ClaimDto;
import prikol2.prikol2.claim.repository.ClaimRepositoryImpl;
import prikol2.prikol2.claim.enums.Status;
import prikol2.prikol2.claim.mapper.ClaimMapper;
import prikol2.prikol2.employee.repository.EmployeeRepositoryImpl;

import java.util.Optional;

@Service
@Slf4j
public class ClaimService {



    private final ClaimRepositoryImpl claimRepositoryImpl;
    private final EmployeeRepositoryImpl employeeRepositoryimpl;

    @Autowired
    public ClaimService(ClaimRepositoryImpl claimRepositoryImpl, EmployeeRepositoryImpl employeeRepositoryimpl) {
        this.claimRepositoryImpl = claimRepositoryImpl;
        this.employeeRepositoryimpl = employeeRepositoryimpl;
    }


    public ClaimDto createClaim(ClaimDto claimDto) {
        log.info("Создание заявления");
        validateClaim(claimDto);
        Claim claim = ClaimMapper.maptoClaim(claimDto);
        // отправляется сообщение в кафку -- отправка события на создание заявления с таким-то номером
        // дальше через кафку сообщение + сохранение в базу
        claimRepositoryImpl.save(claim);

        // после сохранения в базу отправка запроса через web client builder в ведомство
        claim.setStatusId(Status.PROCESSING.getValue());
        claimRepositoryImpl.save(claim);

        return ClaimMapper.mapToClaimDto(claim);
    }

    private void validateClaim(ClaimDto claimDto) {
        log.trace("Проверка уникальности номера заявления {}",claimDto.getNumber());
        Optional<Claim> savedClaim =
                claimRepositoryImpl.getClaimByNumber(claimDto.getNumber());
        if(savedClaim.isPresent()) {
            log.error("Заявление с таким номером уже существует {}",claimDto.getNumber());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        log.trace("Проверка существования работника {}",claimDto.getEmployeeLogin());
        if(!employeeRepositoryimpl.existsByLogin(claimDto.getEmployeeLogin())) {
            log.error("Работника с таким номером не существует {}",claimDto.getEmployeeLogin());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleResponseStatusException(ResponseStatusException responseStatusException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseStatusException.getMessage());
    }
}

