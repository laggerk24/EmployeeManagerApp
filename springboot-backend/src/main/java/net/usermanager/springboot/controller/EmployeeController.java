package net.usermanager.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.usermanager.springboot.exception.ResourceNotFoundException;
import net.usermanager.springboot.model.Employee;
import net.usermanager.springboot.repository.EmployeeRepository;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/")
public class EmployeeController {
	
	@Autowired
	private  EmployeeRepository employeeRepository;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	
	@PostMapping("/employees")
	public Employee creatEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee = employeeRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Employee with Id does not exist - Id: "+ id));
		return ResponseEntity.ok(employee);
	}

	@PostMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id ,@RequestBody Employee employeeDetails){
		Employee employee = employeeRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Employee with Id does not exist - Id: "+ id));
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmailId(employeeDetails.getEmailId());
		Employee updatedEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> removeEmployee(@PathVariable Long id){
		Employee employee = employeeRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Employee with Id does not exist - Id: "+ id));
		employeeRepository.delete(employee);
		Map<String, Boolean> responseMap = new HashMap<>();
		responseMap.put("delete", true);
		return ResponseEntity.ok(responseMap);
	}
}
