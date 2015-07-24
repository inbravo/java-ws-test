package com.inbravo.ss.wsclients;

import java.util.List;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inbravo.esws.service.Employee;
import com.inbravo.ss.service.EmployeeInfo;

/**
 * 
 * @author amit.dixit
 * 
 */
public final class EmployeeInfoAtESWS implements EmployeeInfo {

	private final static Logger logger = LoggerFactory.getLogger(EmployeeInfoAtESWS.class);

	/* Name of service class */
	public static final String SERVICE_NAME = "EmployeeInfoAtESWS";

	private static final int port = 8080;
	private static final String host = "localhost";

	private Client client;

	private static boolean initializationStatus;

	public EmployeeInfoAtESWS() throws Exception {

		this.init();
	}

	@Override
	public final void init() throws Exception {
		this.initESWSClient();
	}

	public final void initESWSClient() throws Exception {

		/* Create client factory */
		final JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();

		/* Create WS client */
		client = dcf.createClient("http://" + host + ":" + port + "/esws/services/employeeService?wsdl");

		/* Initialization is completed */
		initializationStatus = true;
	}

	@Override
	public void create(final Employee employee) throws Exception {

		if (initializationStatus) {

			/* Call update method */
			client.invoke("create", new Object[] { employee });
		} else {
			/* Throw runtime error */
			throw new RuntimeException("EmployeeInfoAtESWS is not initialized");
		}
	}

	@Override
	public final Employee read(final int employeeId) throws Exception {

		if (initializationStatus) {

			/* Call read method */
			final Object[] res = client.invoke("read", new Object[] { "" + employeeId });

			/* Get all employees from response */
			final Employee employee = (Employee) res[0];

			/* Create new employee list */
			return employee;
		} else {

			/* Throw runtime error */
			throw new RuntimeException("EmployeeInfoAtESWS is not initialized");
		}
	}

	@Override
	public final List<Employee> readAll() throws Exception {

		if (initializationStatus) {

			/* Call read method */
			final Object[] res = client.invoke("readAll");

			/* Get all employees from response */
			@SuppressWarnings("unchecked")
			final List<Employee> employees = (List<Employee>) res[0];

			if (logger.isDebugEnabled()) {
				logger.debug("readAll, employees count : " + employees);
			}

			/* Create new employee list */
			return employees;

		} else {
			/* Throw runtime error */
			throw new RuntimeException("EmployeeInfoAtESWS is not initialized");
		}
	}

	@Override
	public final void delete(final int employeeId) throws Exception {

		if (initializationStatus) {

			/* Call update method */
			client.invoke("delete", new Object[] { employeeId });
		} else {
			/* Throw runtime error */
			throw new RuntimeException("EmployeeInfoAtESWS is not initialized");
		}
	}

	@Override
	final public void update(final Employee employee) throws Exception {

		if (initializationStatus) {

			/* Call update method */
			client.invoke("update", new Object[] { employee });
		} else {
			/* Throw runtime error */
			throw new RuntimeException("EmployeeInfoAtESWS is not initialized");
		}
	}
}
