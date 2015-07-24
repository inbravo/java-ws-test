package com.inbravo.ss.wsclients;

import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.cxf.jaxrs.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inbravo.erws.service.Employees;
import com.inbravo.erws.service.Employee;
import com.inbravo.ss.dto.EmployeeDTO;
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

	private static final int port = 8080;
	private static final String host = "localhost";

	/* Create JAXBContext for the employee object */
	private JAXBContext employeeContext;

	/* Create marshaler */
	private Unmarshaller employeeUnmarshaller;

	/* Create JAXBContext for the employees object */
	private JAXBContext employeesContext;

	/* Create marshaler */
	private Unmarshaller employeesUnmarshaller;

	private WebClient client;

	private static boolean initializationStatus;

	public EmployeeInfoAtERWS() throws Exception {

		this.init();
	}

	@Override
	public final void init() throws Exception {
		this.initESWSClient();
	}

	public final void initESWSClient() throws Exception {

		/* Create web client */
		client = WebClient.create("http://" + host + ":" + port + "/erws/services/employeeService");

		employeeContext = JAXBContext.newInstance(Employee.class);
		employeeUnmarshaller = employeeContext.createUnmarshaller();

		employeesContext = JAXBContext.newInstance(Employees.class);
		employeesUnmarshaller = employeesContext.createUnmarshaller();

		/* Initialization is completed */
		initializationStatus = true;
	}

	@Override
	public void create(final EmployeeDTO dto) throws Exception {

		if (initializationStatus) {

		} else {
			/* Throw runtime error */
			throw new RuntimeException("EmployeeInfoAtERWS client is not initialized");
		}
	}

	@Override
	public final EmployeeDTO read(final int employeeId) throws Exception {

		logger.debug("read >>>>");

		if (initializationStatus) {

			/* Set response content settings */
			client.path("/read/" + employeeId).type("text/xml");

			/* Get response */
			final Response response = client.get();

			/* Convert to employee */
			final Employee employee = (Employee) employeeUnmarshaller.unmarshal((InputStream) response.getEntity());

			return new EmployeeDTO(employee);
		} else {
			/* Throw runtime error */
			throw new RuntimeException("EmployeeInfoAtERWS client is not initialized");
		}
	}

	@Override
	public final List<EmployeeDTO> readAll() throws Exception {

		logger.debug("readAll >>>>");

		if (initializationStatus) {

			/* Set response content settings */
			client.path("/readall/").type("text/xml");

			/* Get response */
			final Response response = client.get();

			logger.debug("readAll : response status : " + response.getStatus());

			/* Convert to employee */
			final Employees employees = (Employees) employeesUnmarshaller.unmarshal((InputStream) response.getEntity());

			/* Create new Employee DTO list */
			final List<EmployeeDTO> employeeList = new ArrayList<EmployeeDTO>();

			/* Convert to standard employee */
			for (final Employee employee : employees.employees) {

				employeeList.add(new EmployeeDTO(employee));
			}

			return employeeList;

		} else {

			/* Throw runtime error */
			throw new RuntimeException("EmployeeInfoAtERWS client is not initialized");
		}
	}

	@Override
	public final void delete(final int employeeId) throws Exception {

		if (initializationStatus) {

			/* Set response content settings */
			client.path("/delete/" + employeeId).type("text/xml");

			/* Call delete */
			client.delete();

		} else {
			/* Throw runtime error */
			throw new RuntimeException("EmployeeInfoAtERWS client is not initialized");
		}
	}

	@Override
	final public void update(final EmployeeDTO employeeDTO) throws Exception {

		if (initializationStatus) {

			/* Set response content settings */
			client.path("/update").type("text/xml");

			/* Create Employee Object of WS service */
			final Employee employee = new Employee(employeeDTO.getEmpId(), employeeDTO.getEmpName(), employeeDTO.getPhone(),
					employeeDTO.getEmail(), employeeDTO.getSalary(), employeeDTO.getDesignation());

			/* Call update */
			client.put(employee);

		} else {
			/* Throw runtime error */
			throw new RuntimeException("EmployeeInfoAtERWS client is not initialized");
		}
	}

	public static void main(String[] args) throws Exception {

		EmployeeInfoAtERWS info = new EmployeeInfoAtERWS();
		System.out.println(info.read(1));
	}
}
