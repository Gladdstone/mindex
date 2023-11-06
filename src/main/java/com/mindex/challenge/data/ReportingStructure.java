package com.mindex.challenge.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportingStructure {
    private String employeeId;
    private int numberOfReports;

    public ReportingStructure() {
    }

    public ReportingStructure(String employeeId){
        this.employeeId = employeeId;
    }
}
