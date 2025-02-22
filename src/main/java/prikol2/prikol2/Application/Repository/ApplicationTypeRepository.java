package prikol2.prikol2.Application.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prikol2.prikol2.Application.models.ApplicationType;

public interface ApplicationTypeRepository extends JpaRepository<ApplicationType, Long> {
    ApplicationType findApplicationTypeByCode(String code);
}
