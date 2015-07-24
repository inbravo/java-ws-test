package com.inbravo.ss.service;

import java.util.List;

import com.inbravo.dto.StandardDTO;

/**
 * 
 * @author amit.dixit
 *
 */
public interface EmployeeInfo {

	public void init() throws Exception;

	public void create(final StandardDTO dto) throws Exception;

	public List<StandardDTO> readAll() throws Exception;

	public StandardDTO read(final int employeeId) throws Exception;

	public void update(final StandardDTO dto) throws Exception;

	public void delete(final int id) throws Exception;
}
