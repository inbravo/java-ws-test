package com.inbravo.ss.wsclients;

import java.util.List;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
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
	public static final String SERVICE_NAME = "EmployeeInfoAtESWS";

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
			JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
			Client client = dcf.createClient("http://localhost:8080/esws/services/employeeService?wsdl");

			Object[] res = client.invoke("read");
			System.out.println("Echo response: " + res[0]);
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

		final EmployeeInfoAtESWS eamdb = new EmployeeInfoAtESWS();

		System.out.println(eamdb.read());
		// eamdb.delete(2);
		// eamdb.read(2);
	}
}
