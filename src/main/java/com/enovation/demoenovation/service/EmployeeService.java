package com.enovation.demoenovation.service;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.enovation.demoenovation.entity.Employee;

public interface EmployeeService {

  List<Employee> getAllEmployees();

  Optional<Employee> getEmployeeById(int id);

  Employee addEmployee(@NotNull Employee employee);

  Employee updateEmployee(@NotNull Employee employee);

  void deleteEmployee(int id);

  void deleteAllEmployees();

}