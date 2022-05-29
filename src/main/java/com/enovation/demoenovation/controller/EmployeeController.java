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

  /**
   * GET end point which gets list of all employees
   * @return List
   */

  @GetMapping("/employees")
  public List<Employee> getAllEmployees() {
    return employeeService.getAllEmployees();
  }

  /**
   * POST end point to add a new employee
   * @param employee
   * @return employee
   */
  @PostMapping("/employees")
  public Employee addEmployee(@Valid @NotNull @RequestBody Employee employee) {
    return employeeService.addEmployee(employee);
  }

  /**
   * GET end point to to get employee details for an id
   * @param id
   * @return employee
   */

  @GetMapping("/employees/{id}")
  public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id) throws EmployeeNotFoundException {
    Employee employee = employeeService.getEmployeeById(id)
        .orElseThrow(() -> new EmployeeNotFoundException("Employee not found for this id :: " + id));
    return ResponseEntity.ok().body(employee);
  }

  /**
   * End point to update the details of an employee
   * @param id
   * @return employee
   */
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

  /**
   * End point to delete an employee given an id
   * @param id
   * @return employee
   */
  @DeleteMapping("/employees/{id}")
  public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") int id) throws EmployeeNotFoundException {
    employeeService.getEmployeeById(id)
        .orElseThrow(() -> new EmployeeNotFoundException("Employee not found, hence deletion failed for this id"));
    employeeService.deleteEmployee(id);

    return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
  }

  @DeleteMapping("/employees")
  public void deleteAllEmployees() {
    employeeService.deleteAllEmployees();
  }

}
