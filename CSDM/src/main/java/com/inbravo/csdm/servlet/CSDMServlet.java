package com.inbravo.csdm.servlet;

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

import com.inbravo.csdm.dto.CSDTO;
import com.inbravo.csdm.exception.CSMException;
import com.inbravo.csdm.jdbc.CSInfoAtMongoDB;
import com.inbravo.csdm.service.CSInfo;

/**
 * 
 * @author amit.dixit
 * 
 */
@WebServlet(name = "CSDMServlet", urlPatterns = { "/csdmService.ser" })
public final class CSDMServlet extends HttpServlet {

	private final static Logger logger = LoggerFactory.getLogger(CSDMServlet.class);

	private static final long serialVersionUID = 1L;

	private CSInfo csInfo;

	@Override
	public void init() throws ServletException {
		super.init();

		try {

			/* Initialize your data base connection utility object */
			csInfo = new CSInfoAtMongoDB();
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

		/* Get case study id */
		final String csId = request.getParameter("csId");

		logger.debug("--Inside doGet : csId : " + csId);

		try {

			/* Start time */
			final long startTime = System.currentTimeMillis();

			/* Create new Employee DTO list */
			final List<CSDTO> csList = this.getCaseStudies(csId);

			/* End time */
			final long endTime = System.currentTimeMillis();

			logger.debug("--Inside doGet : time taken in transaction : " + (endTime - startTime) + " msec(s)");

			/* Set employee DTO in session */
			request.setAttribute("csl", csList);

			/* Forward the request to view JSP */
			request.getRequestDispatcher("/jsp/cs/ViewCS.jsp").forward(request, response);
		} catch (final Exception e) {

			/* Send to error page */
			this.showServerError(request, response, e);
		}
	}

	@Override
	protected final void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

		logger.debug("--Inside doPost");

		/* Call process request */
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
			csInfo.update(new CSDTO(request));

			/* End time */
			final long endTime = System.currentTimeMillis();

			logger.debug("--Inside doPut : time taken in transaction : " + (endTime - startTime) + " msec(s)");
		} catch (final CSMException e) {

			/* Throw user error */
			this.showValidationError(request, e.getMessage());
		} catch (final Exception e) {

			/* Send to error page */
			this.showServerError(request, response, e);
		}

		/* Check if updated employee information found in session */
		if (request.getSession().getAttribute("cs_updated") != null) {

			final int updatedEmpCount = Integer.parseInt((String) request.getSession().getAttribute("cs_updated"));

			/* Increment the count and put back in session */
			request.getSession().setAttribute("cs_updated", String.valueOf(updatedEmpCount + 1));
		} else {

			/* Set session variable */
			request.getSession().setAttribute("cs_updated", String.valueOf(1));
		}

		/* Forward the request to view employee page */
		this.doGet(request, response);
	}

	@Override
	protected final void doDelete(final HttpServletRequest request, final HttpServletResponse response) throws ServletException,
			IOException {

		/* Get employee id */
		final int csId = Integer.parseInt(request.getParameter("csId"));

		logger.debug("--Inside doDelete : csId : " + csId);

		try {
			/* Start time */
			final long startTime = System.currentTimeMillis();

			/* Delete the employee from DB */
			csInfo.delete(csId);

			/* End time */
			final long endTime = System.currentTimeMillis();

			logger.debug("--Inside doDelete : time taken in transaction : " + (endTime - startTime) + " msec(s)");
		} catch (final CSMException e) {

			/* Throw user error */
			this.showValidationError(request, e.getMessage());
		} catch (final Exception e) {

			/* Send to error page */
			this.showServerError(request, response, e);
		}

		/* Check if deleted employee information found in session */
		if (request.getSession().getAttribute("cs_deleted") != null) {

			final int deletedEmpCount = Integer.parseInt((String) request.getSession().getAttribute("cs_deleted"));

			/* Increment the count and put back in session */
			request.getSession().setAttribute("cs_deleted", String.valueOf(deletedEmpCount + 1));
		} else {

			/* Set session variable */
			request.getSession().setAttribute("cs_deleted", String.valueOf(1));
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
				csInfo.create(new CSDTO(request));

				/* Check if created employee information found in session */
				if (request.getSession().getAttribute("cs_created") != null) {

					final int createdCSCount = Integer.parseInt((String) request.getSession().getAttribute("cs_created"));

					/* Increment the count and put back in session */
					request.getSession().setAttribute("cs_created", String.valueOf(createdCSCount + 1));
				} else {

					/* Set session variable */
					request.getSession().setAttribute("cs_created", String.valueOf(1));
				}

				/* Forward the request to view employee page */
				this.doGet(request, response);
			} catch (final CSMException e) {

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
	 * @param csId
	 * @return
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	private final List<CSDTO> getCaseStudies(final String csId) throws NumberFormatException, Exception {

		/* Create new case studies DTO list */
		final List<CSDTO> csList = new ArrayList<CSDTO>();

		/* If there is no employee id in request */
		if (csId != null && "".equals(csId)) {

			/* Create new DTO and add in list */
			csList.add((CSDTO) csInfo.read(Integer.parseInt(csId)));

		} else {

			/* Add all employee in list */
			csList.addAll(csInfo.readAll());
		}

		logger.debug("--Inside getCaseStudies : case studies list : " + csList);

		return csList;
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
