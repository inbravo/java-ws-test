package com.inbravo.erws.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.inbravo.erws.dto.Employee;

@WebService
public interface EmployeeService
{
	@WebMethod
	public List<Employee> read() throws Exception;

	@WebMethod
	public void create(final Employee employee) throws Exception;

	@WebMethod
	public void update(final Employee employee) throws Exception;

	@WebMethod
	public void delete(final int employeeId) throws Exception;
}
