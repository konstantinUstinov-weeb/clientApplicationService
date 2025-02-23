package prikol2.prikol2.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prikol2.prikol2.employee.model.Employee;

import java.util.Optional;

@Repository
public interface EmployeeRepositoryImpl extends JpaRepository<Employee, Long> {
    Optional<Employee> findEmployeeByEmployeeName(String employeeName);

    Boolean existsByEmployeeName(String employeeName);

    Boolean existsByLogin(String login);
}
