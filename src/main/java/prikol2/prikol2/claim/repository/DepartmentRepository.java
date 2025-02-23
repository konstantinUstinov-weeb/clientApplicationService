package prikol2.prikol2.claim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prikol2.prikol2.claim.models.Department;


public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findDepartmentByCode(String code);
}
