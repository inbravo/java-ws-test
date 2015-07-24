package com.inbravo.ss.service;

import java.util.List;

import com.inbravo.ss.dto.EmployeeDTO;

/**
 * 
 * @author amit.dixit
 *
 */
public interface EmployeeInfo {

	public void init() throws Exception;

	public void create(final EmployeeDTO dao) throws Exception;

	public List<EmployeeDTO> readAll() throws Exception;

	public EmployeeDTO read(final int employeeId) throws Exception;

	public void update(final EmployeeDTO dao) throws Exception;

	public void delete(final int id) throws Exception;
}
