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

import com.inbravo.ss.dao.EmployeeDAO;
import com.inbravo.ss.dto.EmployeeDTO;
import com.inbravo.ss.execption.EmployeeException;
import com.inbravo.ss.jdbc.EmployeeInfoAtDB;
import com.inbravo.ss.jdbc.EmployeeInfoAtMongoDB;

/**
 * 
 * @author amit.dixit
 * 
 */
@WebServlet(name = "EmployeeServlet", urlPatterns = { "/employeeService.ser" })
public final class EmployeeServlet extends HttpServlet {

	private final static Logger logger = LoggerFactory.getLogger(EmployeeServlet.class);

	private static final long serialVersionUID = 1L;

	private EmployeeInfoAtDB employeeInfoAtDB;

	@Override
	public void init() throws ServletException {
		super.init();

		try {

			/* Initialize your data base connection utility object */
			employeeInfoAtDB = new EmployeeInfoAtMongoDB();
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

			/* Set employee DTO in session */
			request.setAttribute("employees", this.getEmployees(employeeId));

			logger.debug("--Inside doGet : employees list is set in request");

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
		this.processRequest(request, response);
	}

	@Override
	protected final void doPut(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

		logger.debug("--Inside doPut");

		try {
			/* Create new Employee type data transfer (DTO) object */
			final EmployeeDTO dto = new EmployeeDTO(request);

			/* Create new Employee type data access (DAO) object from DTO */
			final EmployeeDAO dao = new EmployeeDAO(dto);

			/* Add new employee in DB */
			employeeInfoAtDB.update(dao);
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
			/* Delete the employee from DB */
			employeeInfoAtDB.delete(employeeId);
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
	 * @throws IOException
	 * @throws ServletException
	 */
	private final void processRequest(final HttpServletRequest request, final HttpServletResponse response) throws ServletException,
			IOException {

		if (request.getParameter("operation") != null && "delete".equalsIgnoreCase(request.getParameter("operation"))) {

			/* Call Employee Delete */
			this.doDelete(request, response);
		}
		if (request.getParameter("operation") != null && "update".equalsIgnoreCase(request.getParameter("operation"))) {

			/* Call Employee Update */
			this.doPut(request, response);
		} else if (request.getParameter("operation") != null && "create".equalsIgnoreCase(request.getParameter("operation"))) {

			try {

				/* Create new Employee type data transfer (DTO) object */
				final EmployeeDTO dto = new EmployeeDTO(request);

				/* Create new Employee type data access (DAO) object from DTO */
				final EmployeeDAO dao = new EmployeeDAO(dto);

				/* Add new employee in DB */
				employeeInfoAtDB.create(dao);

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
	private final List<EmployeeDTO> getEmployees(final String employeeId) throws NumberFormatException, Exception {

		/* Create new Employee DTO list */
		final List<EmployeeDTO> dtos = new ArrayList<EmployeeDTO>();

		/* If there is no employee id in request */
		if (employeeId != null && "".equals(employeeId)) {

			/* Get employee information from DB */
			final EmployeeDAO dao = employeeInfoAtDB.read(Integer.parseInt(employeeId));

			/* Create new DTO and add in list */
			dtos.add(new EmployeeDTO(dao));

		} else {

			/* Get employee information from DB */
			final List<EmployeeDAO> daos = employeeInfoAtDB.read();

			/* Iterate over DAO and populate DTO */
			for (final EmployeeDAO dao : daos) {

				/* Create new DTO and add in list */
				dtos.add(new EmployeeDTO(dao));
			}
		}

		return dtos;
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

	@Override
	public final String getServletInfo() {
		return "This service is responsible for processing Employee information";
	}

	@Override
	public final void destroy() {
		super.destroy();
	}
}
