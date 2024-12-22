package com.ems.employee.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ems.employee.model.Employee;
import com.ems.employee.model.EmployeeError;
import com.ems.employee.service.EmpService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/employee")
public class EmpController {

	@Autowired
	public EmpService sc;

	@RequestMapping(method = RequestMethod.POST, path = "/addEmployee")
	public ResponseEntity<?> add(@RequestBody Employee model) {
		Employee returned = null;
		EmployeeError errors = new EmployeeError();
		List<String> errorList = new ArrayList<String>();
		returned = sc.addEmployee(model, errorList);
		if (errorList.size() > 0) {
			errors.setMessage("Error adding user");
			errors.setErrors(errorList);
			return new ResponseEntity<EmployeeError>(errors, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Employee>(returned, HttpStatus.OK);
		}
	}

	@RequestMapping(method = RequestMethod.GET, path = "/getAllEmployee")
	public ResponseEntity<?> getAllEmployeesList() throws Exception {
		List<Employee> empDataArr = null;
		EmployeeError errors = new EmployeeError();
		List<String> errorList = new ArrayList<String>();
		empDataArr = sc.getEmployeeList(errorList);
		if (errorList.size() > 0) {
			errors.setErrors(errorList);
			return new ResponseEntity<EmployeeError>(errors, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<List<Employee>>(empDataArr, HttpStatus.OK);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/deleteEmployee")
	public ResponseEntity<?> deleteData(@RequestParam String empId) throws Exception {
		EmployeeError errors = new EmployeeError();
		List<String> errorList = new ArrayList<String>();
		boolean deleted = false;
		deleted = sc.deleteData(empId,errorList);
		if (errorList.size() > 0) {
			errors.setErrors(errorList);
			return new ResponseEntity<EmployeeError>(errors, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Boolean>(deleted, HttpStatus.OK);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/updatedEmployee")
	public ResponseEntity<?> updateData(@RequestBody Employee emp) {
		EmployeeError errors = new EmployeeError();
		List<String> errorList = new ArrayList<String>();
		boolean updated = false;
		updated = sc.updatedData(emp.getEmpId(), emp.getEmpName(), emp.getEmpEmail(), emp.getEmpPosition(),
				emp.getEmpSalary(),errorList);
		if (errorList.size() > 0) {
			errors.setErrors(errorList);
			return new ResponseEntity<EmployeeError>(errors, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Boolean>(updated, HttpStatus.OK);
		}
	}

	@RequestMapping(method = RequestMethod.GET, path = "/getEmployee")
	public ResponseEntity<?> getEmployee(@RequestParam String empId) throws Exception {
		EmployeeError errors = new EmployeeError();
		List<String> errorList = new ArrayList<String>();
		Employee returned = null;
		returned = sc.getEmployeeById(empId,errorList);
		if (errorList.size() > 0) {
			errors.setErrors(errorList);
			return new ResponseEntity<EmployeeError>(errors, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Employee>(returned, HttpStatus.OK);
		}
	}

	@RequestMapping(method = RequestMethod.GET, path = "/searchByEmployee")
	public ResponseEntity<?> searchByEmployeeList(@RequestParam String optionType,@RequestParam String searchBy) throws Exception {
		List<Employee> empDataArr = null;
		EmployeeError errors = new EmployeeError();
		List<String> errorList = new ArrayList<String>();
		empDataArr = sc.searchByEmployeeList(optionType,searchBy,errorList);
		if (errorList.size() > 0) {
			errors.setErrors(errorList);
			return new ResponseEntity<EmployeeError>(errors, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<List<Employee>>(empDataArr, HttpStatus.OK);
		}
	}
}
