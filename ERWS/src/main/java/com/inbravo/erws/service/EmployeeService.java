package com.inbravo.erws.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.inbravo.erws.dao.EmployeeDAO;

@WebService
public interface EmployeeService
{
	@WebMethod
	public List<EmployeeDAO> read() throws Exception;

	@WebMethod
	public void create(final EmployeeDAO employee) throws Exception;

	@WebMethod
	public void update(final EmployeeDAO employee) throws Exception;

	@WebMethod
	public void delete(final int employeeId) throws Exception;
}
