package com.mindex.challenge.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {

    private String employeeUrl;
    private String compensationUrl;
    private String compensationIdUrl;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        employeeUrl = "http://localhost:" + port + "/employee";
        compensationUrl = "http://localhost:" + port + "/compensation";
        compensationIdUrl = "http://localhost:" + port + "/compensation/{id}";
    }

    @Test
    public void testCreateRead() {
        Employee testEmployee = new Employee();
        testEmployee.setFirstName("firstName");
        testEmployee.setLastName("lastName");
        testEmployee.setDepartment("department");
        testEmployee.setPosition("position");

        Employee createdTestEmployee = restTemplate.postForEntity(employeeUrl, testEmployee, Employee.class).getBody();

        Compensation testCompensation = new Compensation();
        testCompensation.setEmployeeId(createdTestEmployee.getEmployeeId());
        testCompensation.setSalary(1000000);
        testCompensation.setEffectiveDate(new Date(1699256880));

        Compensation createdTestCompensation = restTemplate.postForEntity(compensationUrl, testCompensation, Compensation.class).getBody();
        assertNotNull(createdTestCompensation.getEmployeeId());

        Compensation readCompensation = restTemplate.getForEntity(compensationIdUrl, Compensation.class, createdTestCompensation.getEmployeeId()).getBody();
        assertEquals(readCompensation.getEmployeeId(), createdTestCompensation.getEmployeeId());
    }

}
