package prikol2.prikol2.Application.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prikol2.prikol2.Application.models.Department;


public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findDepartmentByCode(String code);
}
