package com.inbravo.ss.jdbc;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inbravo.dto.StandardDTO;
import com.inbravo.esws.service.Employee;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

/**
 * 
 * @author amit.dixit
 * 
 */
public final class EmployeeInfoAtMongoDB implements EmployeeInfoAtDB {

	private final static Logger logger = LoggerFactory.getLogger(EmployeeInfoAtMongoDB.class);

	/* Name of service class */
	public static final String SERVICE_NAME = "EmployeeInfoAtMongoDB";

	private static final int port = 27017;
	private static final String host = "localhost";
	private static Mongo mongo = null;
	private static boolean initializationStatus;

	public EmployeeInfoAtMongoDB() throws Exception {
		this.initDB();
	}

	@Override
	public final void init() throws Exception {
		this.initDB();
	}

	@Override
	public final void initDB() throws Exception {

		if (mongo == null) {
			try {
				mongo = new Mongo(host, port);
				logger.debug("New Mongo DB client created with [" + host + "] and [" + port + "]");
			} catch (UnknownHostException | MongoException e) {
				logger.error(e.getMessage());
			}
		}

		/* Initialization is completed */
		initializationStatus = true;
	}

	@Override
	public void create(final StandardDTO dto) throws Exception {

		if (initializationStatus) {

			/* Get collection from DB */
			final DBCollection coll = mongo.getDB("test").getCollection("Employee");

			try {

				/* Start the request */
				mongo.getDB("test").requestStart();

				/* Typecast */
				final Employee employee = (Employee) dto;

				/* Create new DB object at Mongo DB */
				final BasicDBObject employeeObj = new BasicDBObject("obj_name", "Employee").append("version", "1.0")
						.append("id", employee.getEmpId()).append("name", employee.getEmpName())
						.append("designation", employee.getDesignation()).append("email", employee.getEmail())
						.append("phone", employee.getPhone()).append("salary", employee.getSalary());

				/* Insert an employee */
				coll.insert(employeeObj);

				logger.debug("New employee is created : [" + employeeObj + "]");

			} catch (final Exception ex) {

				/* Throw runtime error */
				throw new RuntimeException("Unable to do Mongo DB operation", ex);
			} finally {

				/* Complete the request */
				mongo.getDB("test").requestDone();
			}
		} else {
			/* Throw runtime error */
			throw new RuntimeException("Mongo DB client is not initialized");
		}
	}

	@Override
	public final Employee read(final int employeeId) throws Exception {

		if (initializationStatus) {

			/* Get collection from DB */
			final DBCollection coll = mongo.getDB("test").getCollection("Employee");

			try {

				/* Start the request */
				mongo.getDB("test").requestStart();

				/* Create new DB object at Mongo DB */
				final BasicDBObject mainQuery = new BasicDBObject("obj_name", "Employee");
				mainQuery.put("id", employeeId);

				/* Find the employee */
				final DBCursor cursor = coll.find(mainQuery);

				DBObject object = null;

				/* Get the data from cursor */
				while (cursor.hasNext()) {

					/* Get record collection */
					object = cursor.next();

					logger.debug("Found single employee : [" + object.toMap() + "]");
				}

				return new Employee(object);

			} catch (final Exception ex) {

				/* Throw runtime error */
				throw new RuntimeException("Unable to do Mongo DB operation", ex);

			} finally {

				/* Complete the request */
				mongo.getDB("test").requestDone();
			}

		} else {
			/* Throw runtime error */
			throw new RuntimeException("Mongo DB client is not initialized");
		}
	}

	@Override
	public final List<StandardDTO> readAll() throws Exception {

		if (initializationStatus) {

			logger.debug("Reading all employees");

			/* Get collection from DB */
			final DBCollection coll = mongo.getDB("test").getCollection("Employee");

			/* Create new employee list */
			final List<StandardDTO> employeeList = new ArrayList<StandardDTO>();

			try {

				/* Start the request */
				mongo.getDB("test").requestStart();

				/* Create new DB object at Mongo DB */
				final BasicDBObject mainQuery = new BasicDBObject("obj_name", "Employee");

				/* Find the employee */
				final DBCursor cursor = coll.find(mainQuery);

				/* Get the data from cursor */
				while (cursor.hasNext()) {

					/* Get record collection */
					final DBObject object = cursor.next();
					employeeList.add(new Employee(object));

					logger.debug("Added employee : [" + object.toMap() + " in list]");
				}

			} catch (final Exception ex) {

				/* Throw runtime error */
				throw new RuntimeException("Unable to do Mongo DB operation", ex);

			} finally {

				/* Complete the request */
				mongo.getDB("test").requestDone();
			}

			return employeeList;
		} else {
			/* Throw runtime error */
			throw new RuntimeException("Mongo DB client is not initialized");
		}
	}

	@Override
	public final void delete(final int employeeId) throws Exception {

		if (initializationStatus) {

			/* Get collection from DB */
			final DBCollection coll = mongo.getDB("test").getCollection("Employee");

			try {

				/* Start the request */
				mongo.getDB("test").requestStart();

				/* Create new DB object at Mongo DB */
				final BasicDBObject mainQuery = new BasicDBObject("obj_name", "Employee");
				mainQuery.put("id", employeeId);

				/* Delete the employee */
				coll.remove(mainQuery);

				logger.debug("Employee is deleted : [" + mainQuery + "]");

			} catch (final Exception ex) {

				/* Throw runtime error */
				throw new RuntimeException("Unable to do Mongo DB operation", ex);
			} finally {

				/* Complete the request */
				mongo.getDB("test").requestDone();
			}

		} else {

			/* Throw runtime error */
			throw new RuntimeException("Mongo DB client is not initialized");
		}
	}

	@Override
	final public void update(final StandardDTO dto) throws Exception {

		if (initializationStatus) {

			/* Get collection from DB */
			final DBCollection coll = mongo.getDB("test").getCollection("Employee");

			try {

				/* Start the request */
				mongo.getDB("test").requestStart();

				/* Typecast */
				final Employee employee = (Employee) dto;

				/* Refer existing DB object at Mongo DB */
				final BasicDBObject existingObject = new BasicDBObject("obj_name", "Employee").append("version", "1.0").append("id",
						employee.getEmpId());

				/* Create new DB object at Mongo DB */
				final BasicDBObject newObject = new BasicDBObject("obj_name", "Employee").append("version", "1.0")
						.append("id", employee.getEmpId()).append("name", employee.getEmpName())
						.append("designation", employee.getDesignation()).append("email", employee.getEmail())
						.append("phone", employee.getPhone()).append("salary", employee.getSalary());

				/* Update the employee */
				coll.update(existingObject, newObject);

				logger.debug("Employee is updated : [" + newObject + "]");

			} catch (final Exception ex) {

				/* Throw runtime error */
				throw new RuntimeException("Unable to do Mongo DB operation", ex);
			} finally {

				/* Complete the request */
				mongo.getDB("test").requestDone();
			}
		}
	}
}
