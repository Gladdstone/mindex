package com.mindex.challenge.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {

    private String employeeUrl;
    private String reportingStructureUrl;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        employeeUrl = "http://localhost:" + port + "/employee";
        reportingStructureUrl = "http://localhost:" + port + "/reportingstructure/{id}";
    }

    @Test
    public void testRead() {
        Employee testEmployee = new Employee();
        testEmployee.setFirstName("firstName");
        testEmployee.setLastName("lastName");
        testEmployee.setDepartment("department");
        testEmployee.setPosition("position");

        Employee createdTestEmployee = restTemplate.postForEntity(employeeUrl, testEmployee, Employee.class).getBody();
        assertNotNull(createdTestEmployee.getClass());

        ReportingStructure testReportingStructure = restTemplate.getForEntity(reportingStructureUrl, ReportingStructure.class, createdTestEmployee.getEmployeeId()).getBody();
        assertEquals(testReportingStructure.getEmployeeId(), createdTestEmployee.getEmployeeId());
        assertTrue(Integer.class.isInstance(testReportingStructure.getNumberOfReports()));
    }

}
