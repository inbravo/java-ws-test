package com.inbravo.esws.service;

import java.util.List;

import javax.jws.WebService;
import javax.naming.OperationNotSupportedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inbravo.esws.dao.EmployeeDAO;
import com.inbravo.esws.jdbc.EmployeeInfoAtMongoDB;

@WebService(endpointInterface = "com.inbravo.esws.service.EmployeeService", serviceName = "employeeService")
public final class EmployeeSOAPService implements EmployeeService
{
	private final static Logger logger = LoggerFactory.getLogger(EmployeeSOAPService.class);

	private EmployeeInfoAtMongoDB employeeInfo;

	public EmployeeSOAPService() throws Exception
	{
		employeeInfo = new EmployeeInfoAtMongoDB();
	}

	@Override
	public final List<EmployeeDAO> read() throws Exception
	{
		try
		{
			logger.info(">>>> read");

			/* Get all employees from DB */
			return employeeInfo.read();
		} catch (final Exception e)
		{
			logger.error("Exception inside employee read operation", e);
		}

		return null;
	}

	@Override
	public final void create(final EmployeeDAO employee) throws Exception
	{
		/* TODO */
		throw new OperationNotSupportedException("Employee create is not supported");
	}

	@Override
	public final void update(final EmployeeDAO employee) throws Exception
	{
		/* TODO */
		throw new OperationNotSupportedException("Employee update is not supported");
	}

	@Override
	public final void delete(final int employeeId) throws Exception
	{
		/* TODO */
		throw new OperationNotSupportedException("Employee delete is not supported");
	}
}
