package com.enovation.demoenovation.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enovation.demoenovation.entity.Employee;
import com.enovation.demoenovation.exception.EmployeeNotFoundException;
import com.enovation.demoenovation.service.EmployeeService;

@RestController
@Validated
@RequestMapping("/api/v1")
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  @GetMapping("/employees")
  public List<Employee> getAllEmployees() {
    return employeeService.getAllEmployees();
  }

  @PostMapping("/employees")
  public Employee addEmployee(@Valid @NotNull @RequestBody Employee employee) {
    return employeeService.addEmployee(employee);
  }

  @GetMapping("/employees/{id}")
  public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id) throws EmployeeNotFoundException {
    return employeeService.getEmployeeById(id)
        .map(x -> {
          Employee updateEmployee = employeeService.updateEmployee(x);
          return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
        }).orElseThrow(() -> new EmployeeNotFoundException("No record found with this id"));
  }

  @PutMapping("/employees/{id}")
  public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @Valid @NotNull @RequestBody Employee updateEmployee) throws EmployeeNotFoundException {
    return employeeService.getEmployeeById(id)
        .map(x -> {
          x.setFirstName(updateEmployee.getFirstName());
          x.setLastName(updateEmployee.getLastName());
          x.setEmailAddress(updateEmployee.getEmailAddress());
          Employee newUpdatedEmployee = employeeService.updateEmployee(x);
          return new ResponseEntity<>(newUpdatedEmployee, HttpStatus.OK);
        }).orElseThrow(() -> new EmployeeNotFoundException("Employee update failed for this id"));
  }

  @DeleteMapping("/employees/{id}")
  public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") int id) throws EmployeeNotFoundException {
    employeeService.getEmployeeById(id)
        .orElseThrow(() -> new EmployeeNotFoundException("Employee not found, hence deleted failed for this id"));
    employeeService.deleteEmployee(id);

    return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
  }

  @DeleteMapping("/employees")
  public void deleteAllEmployees() {
    employeeService.deleteAllEmployees();
  }

}
