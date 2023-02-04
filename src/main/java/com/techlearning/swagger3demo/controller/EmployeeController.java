package com.techlearning.swagger3demo.controller;

import java.util.ArrayList;
import java.util.List;

import com.techlearning.swagger3demo.model.Employee;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private List<Employee> employees = createList();

    @Operation(summary = "Get all Employees details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get Employee details",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Employee.class)) }),
            @ApiResponse(responseCode = "400", description = "Employees details not found",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Employees details not found",
                    content = @Content)
    })
    @GetMapping(value = "/employees", produces = "application/json")
    public List<Employee> firstPage() {
        return employees;
    }

    @Operation(summary = "Delete Employees details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted Employee details",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Employee.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid employee id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Employees details not found",
                    content = @Content)
    })
    @DeleteMapping(path = { "/{id}" })
    public Employee delete(@PathVariable("id") int id) {
        Employee deletedEmp = null;
        for (Employee emp : employees) {
            if (emp.getEmpId().equals(id)) {
                employees.remove(emp);
                deletedEmp = emp;
                break;
            }
        }
        return deletedEmp;
    }

    @Operation(summary = "Save Employees details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully saved Employee details",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Employee.class)) })
    })
    @PostMapping
    public Employee create(@RequestBody Employee user) {
        employees.add(user);
        System.out.println(employees);
        return user;
    }

    private static List<Employee> createList() {
        List<Employee> tempEmployees = new ArrayList<>();
        Employee emp1 = new Employee();
        emp1.setName("emp1");
        emp1.setDesignation("manager");
        emp1.setEmpId("1");
        emp1.setSalary(3000);

        Employee emp2 = new Employee();
        emp2.setName("emp2");
        emp2.setDesignation("developer");
        emp2.setEmpId("2");
        emp2.setSalary(3000);
        tempEmployees.add(emp1);
        tempEmployees.add(emp2);
        return tempEmployees;
    }
}
