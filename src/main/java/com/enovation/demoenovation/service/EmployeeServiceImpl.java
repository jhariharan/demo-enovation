package com.enovation.demoenovation.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enovation.demoenovation.entity.Employee;
import com.enovation.demoenovation.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  @Autowired
  private EmployeeRepository employeeRepository;

  @Override
  public List<Employee> getAllEmployees() {
    return StreamSupport.stream(employeeRepository.findAll().spliterator(), false).collect(Collectors.toList());
  }

  @Override
  public Optional<Employee> getEmployeeById(int id) {
    return employeeRepository.findById(id);
  }

  @Override
  public Employee addEmployee(@NotNull Employee employee) {
    return employeeRepository.save(employee);
  }

  @Override
  public Employee updateEmployee(@NotNull Employee employee) {
    return employeeRepository.save(employee);
  }

  @Override
  public void deleteEmployee(int id) {
    employeeRepository.deleteById(id);
  }

  @Override
  public void deleteAllEmployees() {
    employeeRepository.deleteAll();
  }
}
