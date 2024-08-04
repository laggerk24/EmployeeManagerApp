package net.usermanager.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.usermanager.springboot.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
