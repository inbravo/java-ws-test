package com.inbravo.ss.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inbravo.dto.StandardDTO;
import com.inbravo.esws.service.Employee;
import com.inbravo.esws.exception.EmployeeException;
import com.inbravo.ss.jdbc.EmployeeInfoAtMongoDB;
import com.inbravo.ss.service.EmployeeInfo;
import com.inbravo.ss.wsclients.EmployeeInfoAtERWS;
import com.inbravo.ss.wsclients.EmployeeInfoAtESWS;

/**
 * 
 * @author amit.dixit
 * 
 */
@WebServlet(name = "EmployeeServlet", urlPatterns = { "/employeeService.ser" })
public final class EmployeeServlet extends HttpServlet {

	private final static Logger logger = LoggerFactory.getLogger(EmployeeServlet.class);

	private static final long serialVersionUID = 1L;

	private EmployeeInfo employeeInfo;

	@Override
	public void init() throws ServletException {
		super.init();

		try {

			/* Initialize your data base connection utility object */
			employeeInfo = new EmployeeInfoAtMongoDB();
		} catch (final Exception e) {

			/* Send error in logs */
			logger.error("--Inside showServerError", e);
		}
	}

	/**
	 * 
	 */
	@Override
	protected final void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

		/* Get employee id */
		final String employeeId = request.getParameter("empId");

		logger.debug("--Inside doGet : employeeId : " + employeeId);

		try {

			/* Start time */
			final long startTime = System.currentTimeMillis();

			/* Create new Employee DTO list */
			final List<StandardDTO> employeeList = this.getEmployees(employeeId);

			/* End time */
			final long endTime = System.currentTimeMillis();

			logger.debug("--Inside doGet : time taken in transaction : " + (endTime - startTime) + " msec(s)");

			/* Set employee DTO in session */
			request.setAttribute("employees", employeeList);

			/* Forward the request to view JSP */
			request.getRequestDispatcher("/jsp/employee/ViewEmployee.jsp").forward(request, response);
		} catch (final Exception e) {

			/* Send to error page */
			this.showServerError(request, response, e);
		}
	}

	@Override
	protected final void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

		logger.debug("--Inside doPost");

		/* Call proces request */
		try {
			/* Start time */
			final long startTime = System.currentTimeMillis();

			this.processRequest(request, response);

			/* End time */
			final long endTime = System.currentTimeMillis();

			logger.debug("--Inside doPost : time taken in transaction : " + (endTime - startTime) + " msec(s)");
		} catch (final Exception e) {

			/* Send to error page */
			this.showServerError(request, response, e);
		}
	}

	@Override
	protected final void doPut(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

		logger.debug("--Inside doPut");

		try {
			/* Start time */
			final long startTime = System.currentTimeMillis();

			/* Add new employee in DB */
			employeeInfo.update(new Employee(request));

			/* End time */
			final long endTime = System.currentTimeMillis();

			logger.debug("--Inside doPut : time taken in transaction : " + (endTime - startTime) + " msec(s)");
		} catch (final EmployeeException e) {

			/* Throw user error */
			this.showValidationError(request, e.getMessage());
		} catch (final Exception e) {

			/* Send to error page */
			this.showServerError(request, response, e);
		}

		/* Check if updated employee information found in session */
		if (request.getSession().getAttribute("emp_updated") != null) {

			final int updatedEmpCount = Integer.parseInt((String) request.getSession().getAttribute("emp_updated"));

			/* Increment the count and put back in session */
			request.getSession().setAttribute("emp_updated", String.valueOf(updatedEmpCount + 1));
		} else {

			/* Set session variable */
			request.getSession().setAttribute("emp_updated", String.valueOf(1));
		}

		/* Forward the request to view employee page */
		this.doGet(request, response);
	}

	@Override
	protected final void doDelete(final HttpServletRequest request, final HttpServletResponse response) throws ServletException,
			IOException {

		/* Get employee id */
		final int employeeId = Integer.parseInt(request.getParameter("empId"));

		logger.debug("--Inside doDelete : employeeId : " + employeeId);

		try {
			/* Start time */
			final long startTime = System.currentTimeMillis();

			/* Delete the employee from DB */
			employeeInfo.delete(employeeId);

			/* End time */
			final long endTime = System.currentTimeMillis();

			logger.debug("--Inside doDelete : time taken in transaction : " + (endTime - startTime) + " msec(s)");
		} catch (final EmployeeException e) {

			/* Throw user error */
			this.showValidationError(request, e.getMessage());
		} catch (final Exception e) {

			/* Send to error page */
			this.showServerError(request, response, e);
		}

		/* Check if deleted employee information found in session */
		if (request.getSession().getAttribute("emp_deleted") != null) {

			final int deletedEmpCount = Integer.parseInt((String) request.getSession().getAttribute("emp_deleted"));

			/* Increment the count and put back in session */
			request.getSession().setAttribute("emp_deleted", String.valueOf(deletedEmpCount + 1));
		} else {

			/* Set session variable */
			request.getSession().setAttribute("emp_deleted", String.valueOf(1));
		}

		/* Forward the request to view employee page */
		this.doGet(request, response);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private final void processRequest(final HttpServletRequest request, final HttpServletResponse response) throws Exception {

		if (request.getParameter("operation") != null && "delete".equalsIgnoreCase(request.getParameter("operation"))) {

			/* Call Employee Delete */
			this.doDelete(request, response);
		}
		if (request.getParameter("operation") != null && "update".equalsIgnoreCase(request.getParameter("operation"))) {

			/* Call Employee Update */
			this.doPut(request, response);
		} else if (request.getParameter("operation") != null && "create".equalsIgnoreCase(request.getParameter("operation"))) {

			try {
				/* Add new employee in DB */
				employeeInfo.create(new Employee(request));

				/* Check if created employee information found in session */
				if (request.getSession().getAttribute("emp_created") != null) {

					final int createdEmpCount = Integer.parseInt((String) request.getSession().getAttribute("emp_created"));

					/* Increment the count and put back in session */
					request.getSession().setAttribute("emp_created", String.valueOf(createdEmpCount + 1));
				} else {

					/* Set session variable */
					request.getSession().setAttribute("emp_created", String.valueOf(1));
				}

				/* Forward the request to view employee page */
				this.doGet(request, response);
			} catch (final EmployeeException e) {

				/* Throw user error */
				this.showValidationError(request, e.getMessage());
			} catch (final Exception e) {

				/* Send to error page */
				this.showServerError(request, response, e);
			}
		}
		if (request.getParameter("operation") != null && "serviceSwitch".equalsIgnoreCase(request.getParameter("operation"))) {

			/* Call service switch */
			this.serviceSwitch(request, response);
		} else {

			/* Show user error */
			this.showValidationError(request, "Invalid request");
		}
	}

	/**
	 * 
	 * @param employeeId
	 * @return
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	private final List<StandardDTO> getEmployees(final String employeeId) throws NumberFormatException, Exception {

		/* Create new Employee DTO list */
		final List<StandardDTO> employeeList = new ArrayList<StandardDTO>();

		/* If there is no employee id in request */
		if (employeeId != null && "".equals(employeeId)) {

			/* Create new DTO and add in list */
			employeeList.add((Employee) employeeInfo.read(Integer.parseInt(employeeId)));

		} else {

			/* Add all employee in list */
			employeeList.addAll(employeeInfo.readAll());
		}

		logger.debug("--Inside getEmployees : employees list : " + employeeList);

		return employeeList;
	}

	/**
	 * 
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private final void showValidationError(final HttpServletRequest request, final String message) throws IOException, ServletException {

		logger.error("--Inside showValidationError : message : " + message);

		/* Set message in request */
		request.setAttribute("error", message);
	}

	/**
	 * 
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private final void showServerError(final HttpServletRequest request, final HttpServletResponse response, final Exception e)
			throws IOException, ServletException {

		logger.error("--Inside showServerError", e);

		if (!response.isCommitted()) {

			/* Set error information in request */
			request.setAttribute("server_error", e.getMessage());

			/* Forward the request */
			request.getRequestDispatcher("/jsp/common/error.jsp").forward(request, response);
		}
	}

	/**
	 * 
	 * @param employeeInfo
	 * @throws Exception
	 */
	final public void serviceSwitch(final HttpServletRequest request, final HttpServletResponse response) throws Exception {

		/* Get service type from request */
		final String serviceType = request.getParameter("serviceType");

		/* Create respective service on the basis of service type */
		if (serviceType != null && EmployeeInfoAtMongoDB.SERVICE_NAME.equalsIgnoreCase(serviceType)) {

			/* Initialize your data base connection utility object */
			this.employeeInfo = new EmployeeInfoAtMongoDB();
		}
		/* Check for SOAP type service */
		else if (serviceType != null && EmployeeInfoAtESWS.SERVICE_NAME.equalsIgnoreCase(serviceType)) {

			/* Initialize your ESWS client */
			this.employeeInfo = new EmployeeInfoAtESWS();
		}
		/* Check for REST type service */
		else if (serviceType != null && EmployeeInfoAtERWS.SERVICE_NAME.equalsIgnoreCase(serviceType)) {

			/* Initialize your ESWS client */
			this.employeeInfo = new EmployeeInfoAtERWS();
		} else {

			/* Throw user error */
			this.showValidationError(request, "Following service '" + serviceType + "' is not available");
		}

		logger.info("--Inside serviceSwitch, backhand service is changed to : " + serviceType);

		/* Forward the request to service switch JSP */
		request.getRequestDispatcher("/jsp/service/ServiceSwitch.jsp").forward(request, response);
	}

	@Override
	public final String getServletInfo() {
		return "This service is responsible for processing Employee information";
	}

	@Override
	public final void destroy() {
		super.destroy();
	}
}
