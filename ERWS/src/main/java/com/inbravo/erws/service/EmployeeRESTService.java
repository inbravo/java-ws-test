package com.inbravo.erws.service;

import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inbravo.erws.jdbc.EmployeeInfoAtMongoDB;

/**
 * 
 * @author amit.dixit
 *
 */

@WebService(serviceName = "/")
@Produces(
{ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public final class EmployeeRESTService
{
	private final static Logger logger = LoggerFactory.getLogger(EmployeeRESTService.class);

	private EmployeeInfoAtMongoDB employeeInfo;

	public EmployeeRESTService() throws Exception
	{
		/* Initialize JDBC class */
		employeeInfo = new EmployeeInfoAtMongoDB();
	}

	@GET
	@Path("/read/{id}")
	@Produces(
	{ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public final Response read(@PathParam("id") final Integer employeeId) throws Exception
	{
		try
		{
			logger.info(">>>> read");

			/* Get employee from DB */
			final Employee employee = employeeInfo.read(employeeId);

			/* Return the response */
			return Response.ok(employee).build();
		} catch (final Exception e)
		{
			logger.error("Error in read operation", e);
			Response.status(Response.Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_XML).entity("<ERWS><Error>Error in read operation</Error></ERWS>").build();
		}
		return null;
	}

	@GET
	@Path("/readall")
	@Produces(
	{ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public final Response readAll() throws Exception
	{
		try
		{
			logger.info(">>>> read All");

			/* Get all employees from DB */
			final List<Employee> employees = employeeInfo.readAll();

			/* Return the response */
			return Response.ok(new Employees(employees)).build();
		} catch (final Exception e)
		{
			logger.error("Error in read operation", e);
			Response.status(Response.Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_XML).entity("<ERWS><Error>Error in read operation</Error></ERWS>").build();
		}
		return null;
	}

	@POST
	@Path("/create")
	@Produces(
	{ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public final Response create(final Employee employee) throws Exception
	{
		try
		{
			logger.info(">>>> create");

			/* Get employee from DB */
			employeeInfo.create(employee);

			/* Return the response */
			return Response.ok(employee).build();
		} catch (final Exception e)
		{
			logger.error("Error in create operation", e);
			Response.status(Response.Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_XML).entity("<ERWS><Error>Error in create operation</Error></ERWS>").build();
		}
		return null;
	}

	@PUT
	@Path("/update")
	@Produces(
	{ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public final Response update(final Employee employee) throws Exception
	{
		try
		{
			logger.info(">>>> read All");

			/* Update employees at DB */
			employeeInfo.update(employee);

			/* Return the response */
			return Response.ok(employee).build();
		} catch (final Exception e)
		{
			logger.error("Error in update operation", e);
			Response.status(Response.Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_XML).entity("<ERWS><Error>Error in update operation</Error></ERWS>").build();
		}
		return null;
	}

	@DELETE
	@Path("/delete/{id}")
	@Produces(
	{ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public final void delete(@PathParam("id") final Integer employeeId) throws Exception
	{
		try
		{
			logger.info(">>>> delete");

			/* Delete employees from DB */
			employeeInfo.delete(employeeId);

			/* Return the response */
			Response.status(Response.Status.OK).type(MediaType.APPLICATION_XML).entity("<ERWS>Employee is deleted</ERWS>").build();
		} catch (final Exception e)
		{
			logger.error("Error in delete operation", e);
			Response.status(Response.Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_XML).entity("<ERWS><Error>Error in delete operation</Error></ERWS>").build();
		}
	}
}
