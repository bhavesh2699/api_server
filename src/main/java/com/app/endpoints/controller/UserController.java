package com.app.endpoints.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.endpoints.entity.Employee;
import com.app.endpoints.repository.EmployeeRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/employees")
public class UserController {

    @Autowired
    private EmployeeRepository employeeRepository;

    //GET all employee
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
    	//System.out.println("get all employees hit");
        return ResponseEntity.ok().body(employeeRepository.findAll());
    }

    //POST employee
    @PostMapping
    public ResponseEntity<String>  createEmployee(@RequestBody Employee employee) {
    	//System.out.println("post employee hit");
    	if(employeeRepository.findByEmployeeCode((String)employee.getEmployeeCode()) == null) {
	    	if(employeeRepository.findByEmail((String)employee.getEmail()) != null) 
	    		return ResponseEntity.badRequest().body("Email Exists");
	    	else {
	    		employeeRepository.save(employee);
	    		return ResponseEntity.ok().body("Employee added");
	    	}
	    }
    	else {
    		return ResponseEntity.badRequest().body("Employee Code Exists");
    	}  	    	
    }
    
    // GET employee by id
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") String employeeCode) {
    	//System.out.println("get employee hit");
        return ResponseEntity.ok().body(employeeRepository.findByEmployeeCode(employeeCode));
    }
  
    //POST|PUT employee by id
    @PostMapping("/{id}")
    public ResponseEntity<String> updateEmployee(@RequestBody Employee employee, @PathVariable("id") String employeeCode) {
        Employee existingEmployee = employeeRepository.findByEmployeeCode(employeeCode);
        //System.out.println("put employee hit");
        if(existingEmployee!=null) {
            //System.out.println(existingEmployee.getEmployeeName());
            existingEmployee.setEmployeeName(employee.getEmployeeName());
            existingEmployee.setLocation(employee.getLocation());
            existingEmployee.setEmail(employee.getEmail());
            existingEmployee.setDob(employee.getDob());
            employeeRepository.save(existingEmployee);
            return ResponseEntity.ok().body("Employee Updated");
        }
        return ResponseEntity.badRequest().body("Employee doesn't exists"); 
    }
    
    //GET excel file
	@GetMapping("/employees.xlsx")
    public void downloadCsv(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=employees.xlsx");
        ByteArrayInputStream stream = ExcelFileExporter.contactListToExcelFile(createExcelData());
        IOUtils.copy(stream, response.getOutputStream());
    }

	private List<Employee> createExcelData(){
    	return employeeRepository.findAll();
    }

}