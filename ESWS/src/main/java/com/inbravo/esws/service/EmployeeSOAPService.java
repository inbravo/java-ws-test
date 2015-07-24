package com.inbravo.esws.service;

import java.util.List;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inbravo.esws.exception.ESWSException;
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
	public final Employee read(final int employeeId) throws Exception
	{
		try
		{
			logger.info(">>>> read");

			/* Get employee from DB */
			return employeeInfo.read(employeeId);
		} catch (final Exception e)
		{
			logger.error("Error in read operation", e);
			throw new ESWSException("Error in read operation", e);
		}
	}

	@Override
	public final List<Employee> readAll() throws Exception
	{
		try
		{
			logger.info(">>>> read");

			/* Get all employees from DB */
			return employeeInfo.read();
		} catch (final Exception e)
		{
			logger.error("Error in read operation", e);
			throw new ESWSException("Error in read operation", e);
		}
	}

	@Override
	public final void create(final Employee employee) throws Exception
	{
		try
		{
			logger.info(">>>> create");

			/* Create employee in DB */
			employeeInfo.create(employee);
		} catch (final Exception e)
		{
			logger.error("Error in create operation", e);
			throw new ESWSException("Error in create operation", e);
		}
	}

	@Override
	public final void update(final Employee employee) throws Exception
	{
		try
		{
			logger.info(">>>> update");

			/* Update employee in DB */
			employeeInfo.update(employee);
		} catch (final Exception e)
		{
			logger.error("Error in update operation", e);
			throw new ESWSException("Error in update operation", e);
		}
	}

	@Override
	public final void delete(final int employeeId) throws Exception
	{
		try
		{
			logger.info(">>>> delete");

			/* Delete employees from DB */
			employeeInfo.delete(employeeId);
		} catch (final Exception e)
		{
			logger.error("Error in delete operation", e);
			throw new ESWSException("Error in delete operation", e);
		}
	}
}
