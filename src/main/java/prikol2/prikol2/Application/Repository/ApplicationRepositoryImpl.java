package prikol2.prikol2.Application.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prikol2.prikol2.Application.Application;


import java.util.Optional;


public interface ApplicationRepositoryImpl extends JpaRepository<Application, Long> {
    Optional<Application> findApplicationsById(Long id);
}
