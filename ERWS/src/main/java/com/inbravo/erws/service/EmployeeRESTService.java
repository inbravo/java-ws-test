package com.inbravo.erws.service;

import java.util.List;

import javax.naming.OperationNotSupportedException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inbravo.erws.dto.Employee;
import com.inbravo.erws.jdbc.EmployeeInfoAtMongoDB;

/**
 * 
 * @author amit.dixit
 *
 */
@Path("/employeeService/")
@Produces("application/xml")
public final class EmployeeRESTService implements EmployeeService
{
	private final static Logger logger = LoggerFactory.getLogger(EmployeeRESTService.class);

	private EmployeeInfoAtMongoDB employeeInfo;

	public EmployeeRESTService() throws Exception
	{
		employeeInfo = new EmployeeInfoAtMongoDB();
	}

	@Override
	@GET
	@Path("/")
	@Produces(
	{ "application/xml" })
	public final List<Employee> read() throws Exception
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
	public final void create(final Employee employee) throws Exception
	{
		/* TODO */
		throw new OperationNotSupportedException("Employee create is not supported");
	}

	@Override
	public final void update(final Employee employee) throws Exception
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
