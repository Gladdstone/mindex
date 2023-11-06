package com.mindex.challenge.data;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Compensation {
    private String employeeId;
    private int salary;
    private Date effectiveDate;

    public Compensation() {
    }
}
