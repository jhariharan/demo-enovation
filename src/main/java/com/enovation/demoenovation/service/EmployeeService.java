package com.enovation.demoenovation.service;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.enovation.demoenovation.entity.Employee;

/**
 * The interface provide methods for performing various CRUD operations for employee entity
 */

public interface EmployeeService {

  /**
   * Get all employee details
   * @return List of all employees
   */
  List<Employee> getAllEmployees();

  /**
   *Get the employee details for the given id
   * @param id of the employee
   * @return List of all employees
   */
  Optional<Employee> getEmployeeById(int id);

  /**
   *  Add new employee details
   * @param employee
   * @return Employee object
   */

  Employee addEmployee(@NotNull Employee employee);

  /**
   *  Update employee details
   * @param employee
   * @return Employee object
   */
  Employee updateEmployee(@NotNull Employee employee);

  /**
   *  Delete the employee given an id
   * @param id
   * @return Employee object
   */
  void deleteEmployee(int id);

  /**
   *  Deletes all the employees
   */
  void deleteAllEmployees();

}