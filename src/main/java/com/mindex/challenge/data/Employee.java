package com.mindex.challenge.data;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String position;
    private String department;
    private List<Employee> directReports;

    public Employee() {
    }
}
