package com.app.endpoints.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee
{
	@Id
	@Column(name= "employee_code",unique = true)
	private String employeeCode;
	
	@Column(name = "employee_name")
	private String employeeName;
	
	@Column(name = "location")
	private String location;
	
	@Column(name = "email",unique = true)
	private String email;
	
	@Column(name = "dob")
	private String dob;
	
	public Employee() {

    }
	
	public Employee(String employeeCode, String employeeName, String location, String email, String dob) {
		super();
		this.employeeCode = employeeCode;
		this.employeeName = employeeName;
		this.location = location;
		this.email = email;
		this.dob = dob;
	}
	
	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
}