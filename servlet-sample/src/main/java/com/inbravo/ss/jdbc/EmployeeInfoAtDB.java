package com.inbravo.ss.jdbc;

import com.inbravo.ss.service.EmployeeInfo;

/**
 * 
 * @author amit.dixit
 * 
 */
public interface EmployeeInfoAtDB extends EmployeeInfo {

	public void initDB() throws Exception;
}
