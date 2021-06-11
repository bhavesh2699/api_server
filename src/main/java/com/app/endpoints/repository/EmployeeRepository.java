package com.app.endpoints.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.endpoints.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String>{
	Employee findByEmail(String email);
	Employee findByEmployeeCode(String employeeCode);
}
