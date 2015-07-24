package com.inbravo.esws.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface EmployeeService
{
	@WebMethod
	public List<Employee> readAll() throws Exception;

	@WebMethod
	public Employee read(final int employeeId) throws Exception;

	@WebMethod
	public void create(final Employee employee) throws Exception;

	@WebMethod
	public void update(final Employee employee) throws Exception;

	@WebMethod
	public void delete(final int employeeId) throws Exception;
}
