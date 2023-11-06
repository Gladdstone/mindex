package com.mindex.challenge.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    public ReportingStructure read(String id) {
        LOG.debug("Reading reporting structure: {}", id);

        Employee employee = employeeRepository.findByEmployeeId(id);
        ReportingStructure reportingStructure = new ReportingStructure(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        List<Employee> directReports = employee.getDirectReports();

        if(directReports == null) {
            reportingStructure.setNumberOfReports(0);
            return reportingStructure;
        }

        for(int i = 0; i < directReports.size(); i++) {
            employee = employeeRepository.findByEmployeeId(directReports.get(i).getEmployeeId());
            List<Employee> subReports = employee.getDirectReports();
            if(subReports != null) {
                directReports.addAll(subReports);
            }
        }

        reportingStructure.setNumberOfReports(directReports.size());
        return reportingStructure;
    }

}
