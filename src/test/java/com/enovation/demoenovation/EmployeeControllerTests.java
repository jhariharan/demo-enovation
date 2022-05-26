package com.enovation.demoenovation;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.enovation.demoenovation.entity.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoEnovationApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTests {

  @Autowired
  private TestRestTemplate restTemplate;


  @LocalServerPort
  private int port;

  private String getRootUrl() {
    return "http://localhost:" + port;
  }

  @Test
  public void contextLoads() {

  }

  @Test
  public void testGetAllEmployees() {
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> entity = new HttpEntity<String>(null, headers);
    ResponseEntity<String> response = restTemplate.withBasicAuth("admin", "testing123").exchange(getRootUrl() + "/api/v1/employees",
        HttpMethod.GET, entity, String.class);
    assertNotNull(response.getBody());
  }

  @Test
  public void testWhenEmployeeIdNotFound() {
    Employee employee = restTemplate.withBasicAuth("admin", "testing123").getForObject(getRootUrl() + "/api/v1/employees/10", Employee.class);
    assertNull(employee.getFirstName());
    assertNull(employee.getLastName());
    assertNull(employee.getEmailAddress());
  }

  @Test
  public void testCreateEmployee() {
    Employee employee = new Employee();
    employee.setId(1);
    employee.setFirstName("firstname");
    employee.setLastName("lastname");
    employee.setEmailAddress("emailaddress@email.com");

    ResponseEntity<Employee> postResponse = restTemplate.withBasicAuth("admin", "testing123")
        .postForEntity(getRootUrl() + "/api/v1/employees", employee, Employee.class);
    Assert.assertNotNull(postResponse);
    Assert.assertNotNull(postResponse.getBody());
  }

  @Test
  public void testUpdateEmployee() {
    int id = 1;
    Employee employee = restTemplate.withBasicAuth("admin", "testing123").getForObject(getRootUrl() + "/api/v1/employees" + id, Employee.class);
    employee.setEmailAddress("newemailaddress@email.com");

    restTemplate.put(getRootUrl() + "/api/v1/employees" + id, employee);

    Employee updateEmployee = restTemplate.withBasicAuth("admin", "testing123").getForObject(getRootUrl() + "/api/v1/employees" + id, Employee.class);
    Assert.assertNotNull(updateEmployee);
  }

  @Test
  public void testDeleteEmployee() {
    int id = 2;
    Employee employee = restTemplate.withBasicAuth("admin", "testing123").getForObject(getRootUrl() + "/api/v1/employees" + id, Employee.class);
    Assert.assertNotNull(employee);

    restTemplate.delete(getRootUrl() + "/api/v1/employees" + id);

    try {
      restTemplate.withBasicAuth("admin", "testing123").getForObject(getRootUrl() + "/api/v1/employees" + id, Employee.class);
    } catch (final HttpClientErrorException e) {
      assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
    }
  }

}

