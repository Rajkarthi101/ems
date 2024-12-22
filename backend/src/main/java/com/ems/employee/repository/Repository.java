package com.ems.employee.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ems.employee.model.Employee;

public interface Repository extends JpaRepository<Employee, Long>{

	Employee findByEmpName(String empName);
	Employee findByEmpEmail(String empName);

	@Query("SELECT empId FROM Employee where empEmail=?1 AND empId!=?2")
	String updateCheck(String empEmail, int empId);

	@Modifying
	@Query("update Employee set empName=?2,empEmail=?3,empPosition=?4,empSalary=?5 where empId=?1")
	void updateDate(int empId, String empName, String empEmail, String empPosition, String empSalary);
	
	List<Employee> findByEmpNameContaining(String empName);
	List<Employee> findByEmpEmailContaining(String empEmail);
	List<Employee> findByEmpPositionContaining(String empPosition);
	List<Employee> findByEmpSalaryContaining(String empSalary);

}
