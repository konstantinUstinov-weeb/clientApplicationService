package prikol2.prikol2.Application.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prikol2.prikol2.Application.models.Application;

import java.util.Optional;

public interface ApplicationRepositoryImpl extends JpaRepository<Application, Long> {
    Application findApplicationsById(Long id);

    Optional<Application> getApplicationsByNumber(String number);
}
