package prikol2.prikol2.Application.service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import prikol2.prikol2.Application.models.Application;
import prikol2.prikol2.Application.models.ApplicationDto;
import prikol2.prikol2.Application.Repository.ApplicationRepositoryImpl;
import prikol2.prikol2.Application.enums.Status;
import prikol2.prikol2.Application.mapper.ApplicationMapper;
import prikol2.prikol2.User.Repository.UserRepositoryImpl;

import java.util.Optional;

@Service
@Slf4j
public class ApplicationService {

    private final ApplicationRepositoryImpl applicationRepositoryImpl;
    private final UserRepositoryImpl userRepositoryimpl;

    @Autowired
    public ApplicationService(ApplicationRepositoryImpl applicationRepositoryImpl, UserRepositoryImpl userRepositoryimpl) {
        this.applicationRepositoryImpl = applicationRepositoryImpl;
        this.userRepositoryimpl = userRepositoryimpl;
    }


    public ApplicationDto createApplication(ApplicationDto applicationDto) {
        log.info("Создание заявления");
        validateApplication(applicationDto);
        Application application = ApplicationMapper.maptoApplication(applicationDto);
        // отправляется сообщение в кафку -- отправка события на создание заявления с таким-то номером
        // дальше через кафку сообщение + сохранение в базу
        applicationRepositoryImpl.save(application);

        // после сохранения в базу отправка запроса через web client builder в ведомство
        application.setStatusId(Status.PROCESSING.getValue());
        applicationRepositoryImpl.save(application);

        return ApplicationMapper.mapToApplicationDto(application);
    }

    private void validateApplication(ApplicationDto applicationDto) {
        log.trace("Проверка уникальности номера заявления {}",applicationDto.getNumber());
        Optional<Application> savedApplication =
                applicationRepositoryImpl.getApplicationsByNumber(applicationDto.getNumber());
        if(savedApplication.isPresent()) {
            log.error("Заявление с таким номером уже существует {}",applicationDto.getNumber());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        log.trace("Проверка существования работника {}",applicationDto.getUserLogin());
        if(userRepositoryimpl.existsByLogin(applicationDto.getUserLogin())) {
            log.error("Работника с таким номером не существует {}",applicationDto.getUserLogin());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleResponseStatusException(ResponseStatusException responseStatusException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseStatusException.getMessage());
    }
}

