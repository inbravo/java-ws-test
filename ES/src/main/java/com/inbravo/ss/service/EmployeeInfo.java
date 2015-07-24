package com.inbravo.ss.service;

import java.util.List;

import com.inbravo.esws.service.Employee;

/**
 * 
 * @author amit.dixit
 *
 */
public interface EmployeeInfo {

	public void init() throws Exception;

	public void create(final Employee dao) throws Exception;

	public List<Employee> readAll() throws Exception;

	public Employee read(final int employeeId) throws Exception;

	public void update(final Employee dao) throws Exception;

	public void delete(final int id) throws Exception;
}
