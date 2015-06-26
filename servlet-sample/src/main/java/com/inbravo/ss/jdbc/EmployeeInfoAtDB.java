package com.inbravo.ss.jdbc;

import java.util.List;

import com.inbravo.ss.dao.EmployeeDAO;

/**
 * 
 * @author amit.dixit
 * 
 */
public interface EmployeeInfoAtDB {

	public void initDB() throws Exception;

	public void create(final EmployeeDAO dao) throws Exception;

	public List<EmployeeDAO> read() throws Exception;

	public EmployeeDAO read(final int employeeId) throws Exception;

	public void update(final EmployeeDAO dao) throws Exception;

	public void delete(final int id) throws Exception;
}
