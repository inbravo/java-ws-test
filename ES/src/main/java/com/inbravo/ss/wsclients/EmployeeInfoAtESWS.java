package com.inbravo.ss.wsclients;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inbravo.ss.dao.EmployeeDAO;
import com.inbravo.ss.service.EmployeeInfo;

/**
 * 
 * @author amit.dixit
 * 
 */
public final class EmployeeInfoAtESWS implements EmployeeInfo {

	private final static Logger logger = LoggerFactory.getLogger(EmployeeInfoAtESWS.class);

	/* Name of service class */
	public static final String SERVICE_NAME = EmployeeInfoAtESWS.class.getName();

	private static final int port = 27017;
	private static final String host = "localhost";

	private static boolean initializationStatus;

	public EmployeeInfoAtESWS() throws Exception {

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
	public void create(final EmployeeDAO dao) throws Exception {

		if (initializationStatus) {

		} else {
			/* Throw runtime error */
			throw new RuntimeException("EmployeeInfoAtESWS is not initialized");
		}
	}

	@Override
	public final EmployeeDAO read(final int employeeId) throws Exception {

		if (initializationStatus) {

		} else {
			/* Throw runtime error */
			throw new RuntimeException("EmployeeInfoAtESWS is not initialized");
		}
		return null;
	}

	@Override
	public final List<EmployeeDAO> read() throws Exception {

		if (initializationStatus) {

		} else {
			/* Throw runtime error */
			throw new RuntimeException("EmployeeInfoAtESWS is not initialized");
		}
		return null;
	}

	@Override
	public final void delete(final int employeeId) throws Exception {

		if (initializationStatus) {

		} else {
			/* Throw runtime error */
			throw new RuntimeException("EmployeeInfoAtESWS is not initialized");
		}
	}

	@Override
	final public void update(final EmployeeDAO dao) throws Exception {

		if (initializationStatus) {

		} else {
			/* Throw runtime error */
			throw new RuntimeException("EmployeeInfoAtESWS is not initialized");
		}
	}

	public static void main(String[] args) throws Exception {

		final EmployeeDAO dao = new EmployeeDAO(2, "Amit", "9873139660", "amit.dixit@impetus.co.in", 10000f, "Engineer");

		final EmployeeInfoAtESWS eamdb = new EmployeeInfoAtESWS();

		eamdb.create(dao);
		System.out.println(eamdb.read(2));
		// eamdb.delete(2);
		// eamdb.read(2);
	}
}
