package com.inbravo.ss.wsclients;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inbravo.esws.service.Employee;
import com.inbravo.ss.service.EmployeeInfo;

/**
 * 
 * @author amit.dixit
 * 
 */
public final class EmployeeInfoAtERWS implements EmployeeInfo {

	private final static Logger logger = LoggerFactory.getLogger(EmployeeInfoAtERWS.class);

	/* Name of service class */
	public static final String SERVICE_NAME = "EmployeeInfoAtERWS";

	private static final int port = 27017;
	private static final String host = "localhost";

	private static boolean initializationStatus;

	public EmployeeInfoAtERWS() throws Exception {

		this.init();
	}

	@Override
	public final void init() throws Exception {
		this.initESWSClient();
	}

	public final void initESWSClient() throws Exception {

		/* Initialization is completed */
		initializationStatus = true;
	}

	@Override
	public void create(final Employee dao) throws Exception {

		if (initializationStatus) {

		} else {
			/* Throw runtime error */
			throw new RuntimeException("EmployeeInfoAtERWS client is not initialized");
		}
	}

	@Override
	public final Employee read(final int employeeId) throws Exception {

		if (initializationStatus) {

		} else {
			/* Throw runtime error */
			throw new RuntimeException("EmployeeInfoAtERWS client is not initialized");
		}
		return null;
	}

	@Override
	public final List<Employee> readAll() throws Exception {

		if (initializationStatus) {

		} else {
			/* Throw runtime error */
			throw new RuntimeException("EmployeeInfoAtERWS client is not initialized");
		}
		return null;
	}

	@Override
	public final void delete(final int employeeId) throws Exception {

		if (initializationStatus) {

		} else {
			/* Throw runtime error */
			throw new RuntimeException("EmployeeInfoAtERWS client is not initialized");
		}
	}

	@Override
	final public void update(final Employee dao) throws Exception {

		if (initializationStatus) {

		} else {
			/* Throw runtime error */
			throw new RuntimeException("EmployeeInfoAtERWS client is not initialized");
		}
	}
}
