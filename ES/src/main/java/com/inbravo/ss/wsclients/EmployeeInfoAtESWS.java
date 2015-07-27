package com.inbravo.ss.wsclients;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inbravo.esws.service.Employee;
import com.inbravo.ss.dto.EmployeeDTO;
import com.inbravo.ss.service.EmployeeInfo;

/**
 * 
 * @author amit.dixit
 * 
 */
public final class EmployeeInfoAtESWS implements EmployeeInfo {

	private final static Logger logger = LoggerFactory.getLogger(EmployeeInfoAtESWS.class);

	/* Name of service class */
	public static final String SERVICE_NAME = "SOAP";

	private static final int port = 8888;
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

		logger.info("SOAP Client conected at : " + "http://" + host + ":" + port + "/esws/services/employeeService?wsdl");

		/* Initialization is completed */
		initializationStatus = true;
	}

	@Override
	public void create(final EmployeeDTO employeeDTO) throws Exception {

		if (initializationStatus) {

			/* Create Employee Object of WS service */
			final Employee employee = new Employee(employeeDTO.getEmpId(), employeeDTO.getEmpName(), employeeDTO.getPhone(),
					employeeDTO.getEmail(), employeeDTO.getSalary(), employeeDTO.getDesignation());

			/* Call update method */
			client.invoke("create", new Object[] { employee });
		} else {
			/* Throw runtime error */
			throw new RuntimeException("EmployeeInfoAtESWS is not initialized");
		}
	}

	@Override
	public final EmployeeDTO read(final int employeeId) throws Exception {

		if (initializationStatus) {

			/* Call read method */
			final Object[] res = client.invoke("read", new Object[] { "" + employeeId });

			/* Get all employees from response */
			final Employee employee = (Employee) res[0];

			/* Create new employee */
			return new EmployeeDTO(employee);
		} else {

			/* Throw runtime error */
			throw new RuntimeException("EmployeeInfoAtESWS is not initialized");
		}
	}

	@Override
	public final List<EmployeeDTO> readAll() throws Exception {

		if (initializationStatus) {

			/* List of employee DTO */
			final List<EmployeeDTO> employeeDTOList = new ArrayList<EmployeeDTO>();

			/* Call read method */
			final Object[] res = client.invoke("readAll");

			/* Get all employees from response */
			@SuppressWarnings("unchecked")
			final List<Employee> employees = (List<Employee>) res[0];

			if (logger.isDebugEnabled()) {
				logger.debug("readAll, employees count : " + employees);
			}

			for (final Employee employee : employees) {

				employeeDTOList.add(new EmployeeDTO(employee));
			}

			/* Return employee list */
			return employeeDTOList;

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
	final public void update(final EmployeeDTO employeeDTO) throws Exception {

		if (initializationStatus) {

			/* Create Employee Object of WS service */
			final Employee employee = new Employee(employeeDTO.getEmpId(), employeeDTO.getEmpName(), employeeDTO.getPhone(),
					employeeDTO.getEmail(), employeeDTO.getSalary(), employeeDTO.getDesignation());

			/* Call update method */
			client.invoke("update", new Object[] { employee });
		} else {
			/* Throw runtime error */
			throw new RuntimeException("EmployeeInfoAtESWS is not initialized");
		}
	}
}
