package com.ems.employee.service;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ems.employee.model.Employee;
import com.ems.employee.repository.Repository;

import jakarta.transaction.Transactional;

@Service
public class EmpService {

	@Autowired
	private Repository repo;
	
	public Employee addEmployee(Employee userData, List<String> errors ){
		Employee empData=null;
		try {
			System.out.println("userData::SERVICE::"+userData);
			String emailString = userData.getEmpEmail();
			empData = repo.findByEmpEmail(emailString);
			if(empData!=null)errors.add("This User Already Exists");
			if(errors.isEmpty()) {
				return repo.save(userData);
			}else {
				return null;
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			errors.add(ex.getMessage());
		}
		return null;
	}
	
	public List<Employee> getEmployeeList(List<String> errors) {
		try {
			return repo.findAll();
		}catch(Exception ex) {
			ex.printStackTrace();
			errors.add(ex.getMessage());
		}
		return null;
	}
	
	public Employee getEmployee(String empName,List<String> errors) {
		try {
			System.out.println("get Employee::");
			return repo.findByEmpName(empName);
		}catch(Exception ex) {
			ex.printStackTrace();
			errors.add(ex.getMessage());
		}
		return null;
	}
	
	
	public boolean deleteData(String empId, List<String> errors) {
		try {
			System.out.println("DELETE EMPLOYEE::");
			repo.deleteById(Long.parseLong(empId));
			return repo.findById(Long.parseLong(empId)).isEmpty();
		}catch(Exception ex) {
			ex.printStackTrace();
			errors.add(ex.getMessage());
		}
		return false;
	}

	@Transactional
	public boolean updatedData(int empId, String empName, String empEmail, String empPosition, String empSalary, List<String> errors) {
		try {
			System.out.println("updatedData EMPLOYEE::"+empId+"  empName::"+empName+"  empEmail::"+empEmail+" empPosition:"+empPosition+" empSalary:"+empSalary);
			String isPresent = repo.updateCheck(empEmail,empId);
			System.out.println("isPresent::"+isPresent);
			if(isPresent!=null)errors.add("This User Already Exists");
			if(errors.isEmpty() && isPresent==null) {
				repo.updateDate(empId,empName,empEmail,empPosition,empSalary);
			}else {
				return false;
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			errors.add(ex.getMessage());
		}
		return false;
	}
	
	public Employee getEmployeeById(String empId, List<String> errors){
		try {
			System.out.println("editEmployee::"+empId);
			return repo.findById(Long.parseLong(empId)).get();
		}catch(Exception ex) {
			ex.printStackTrace();
			errors.add(ex.getMessage());
		}
		return null;
	}

	public List<Employee> searchByEmployeeList(String optionType, String searchBy, List<String> errors) {
		try {
			if(optionType.equals("1")) {
				System.out.print("searchBy"+searchBy);
				return repo.findByEmpNameContaining(searchBy);
			}else if(optionType.equals("2")) {
				return repo.findByEmpEmailContaining(searchBy);
			}else if(optionType.equals("3")) {
				return repo.findByEmpPositionContaining(searchBy);
			}else if(optionType.equals("4")) {
				return repo.findByEmpSalaryContaining(searchBy);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			errors.add(ex.getMessage());
		}
		return null;
	}
}
