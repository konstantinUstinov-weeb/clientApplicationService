package prikol2.prikol2.Application.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prikol2.prikol2.Application.models.ApplicationStatus;

public interface ApplicationStatusRepository extends JpaRepository<ApplicationStatus, Long> {

    ApplicationStatus findApplicationStatusByCode(String code);
}
