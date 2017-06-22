package com.inbravo.csdm.jdbc;

import java.net.UnknownHostException;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inbravo.csdm.dto.CSDTO;
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
public final class CSInfoAtMongoDB implements CSInfoAtDB {

	private final static Logger logger = LoggerFactory.getLogger(CSInfoAtMongoDB.class);

	/* Name of service class */
	public static final String SERVICE_NAME = "JDBC";

	private static final int port = 27017;
	private static final String host = "localhost";
	private static Mongo mongo = null;
	private static boolean initializationStatus;

	public CSInfoAtMongoDB() throws Exception {
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
	public void create(final CSDTO dto) throws Exception {

		if (initializationStatus) {

			/* Get collection from DB */
			final DBCollection coll = mongo.getDB("test").getCollection("CaseStudy");

			try {

				/* Start the request */
				mongo.getDB("test").requestStart();

				/* Create new DB object at DB */
				final BasicDBObject dbObj = new BasicDBObject("obj_name", "CaseStudy");
				
				dbObj.append("version", "1.0");
				dbObj.append("id", dto.getCsId());
				dbObj.append("clientInfo", dto.getClientInfo());
				dbObj.append("requirements", dto.getRequirements());
				dbObj.append("solution", dto.getSolution());
				dbObj.append("businessValue", dto.getBusinessValue());
				dbObj.append("howChallengesHandled", dto.getHowChallengesHandled());
				dbObj.append("nfr", dto.getNfr());
				dbObj.append("stack", dto.getStack());
				dbObj.append("duration", dto.getDuration());
				dbObj.append("team", dto.getTeam());
				dbObj.append("results", dto.getResults());						

				/* Insert case study */
				coll.insert(dbObj);

				logger.debug("New case study is created : [" + dbObj + "]");

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
	public final CSDTO read(final int employeeId) throws Exception {

		if (initializationStatus) {

			/* Get collection from DB */
			final DBCollection coll = mongo.getDB("test").getCollection("CaseStudy");

			try {

				/* Start the request */
				mongo.getDB("test").requestStart();

				/* Create new DB object at Mongo DB */
				final BasicDBObject mainQuery = new BasicDBObject("obj_name", "CaseStudy");
				mainQuery.put("id", employeeId);

				/* Find the Case study */
				final DBCursor cursor = coll.find(mainQuery);

				DBObject object = null;

				/* Get the data from cursor */
				while (cursor.hasNext()) {

					/* Get record collection */
					object = cursor.next();

					logger.debug("Found single Case study : [" + object.toMap() + "]");
				}

				return new CSDTO(object);

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
	public final List<CSDTO> readAll() throws Exception {

		if (initializationStatus) {

			logger.debug("Reading all case studies");

			/* Get collection from DB */
			final DBCollection coll = mongo.getDB("test").getCollection("CaseStudy");

			/* Create new Case study list */
			final List<CSDTO> employeeList = new ArrayList<CSDTO>();

			try {

				/* Start the request */
				mongo.getDB("test").requestStart();

				/* Create new DB object at Mongo DB */
				final BasicDBObject mainQuery = new BasicDBObject("obj_name", "CaseStudy");

				/* Find the Case study */
				final DBCursor cursor = coll.find(mainQuery);

				/* Get the data from cursor */
				while (cursor.hasNext()) {

					/* Get record collection */
					final DBObject object = cursor.next();
					employeeList.add(new CSDTO(object));

					logger.debug("Added Case study : [" + object.toMap() + " in list]");
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
			final DBCollection coll = mongo.getDB("test").getCollection("CaseStudy");

			try {

				/* Start the request */
				mongo.getDB("test").requestStart();

				/* Create new DB object at Mongo DB */
				final BasicDBObject mainQuery = new BasicDBObject("obj_name", "CaseStudy");
				mainQuery.put("id", employeeId);

				/* Delete the Case study */
				coll.remove(mainQuery);

				logger.debug("Case study is deleted : [" + mainQuery + "]");

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
	final public void update(final CSDTO dto) throws Exception {

		if (initializationStatus) {

			/* Get collection from DB */
			final DBCollection coll = mongo.getDB("test").getCollection("CaseStudy");

			try {

				/* Start the request */
				mongo.getDB("test").requestStart();

				/* Refer existing DB object at Mongo DB */
				final BasicDBObject existingObject = new BasicDBObject("obj_name", "CaseStudy").append("version", "1.0").append("id",
						dto.getCsId());

				/* Create new DB object at Mongo DB */
				final BasicDBObject newObject = new BasicDBObject("obj_name", "Employee");
				
				newObject.append("version", "1.0");
				newObject.append("id", dto.getCsId());
				newObject.append("clientInfo", dto.getClientInfo());
				newObject.append("requirements", dto.getRequirements());
				newObject.append("solution", dto.getSolution());
				newObject.append("businessValue", dto.getBusinessValue());
				newObject.append("howChallengesHandled", dto.getHowChallengesHandled());
				newObject.append("nfr", dto.getNfr());
				newObject.append("stack", dto.getStack());
				newObject.append("duration", dto.getDuration());
				newObject.append("team", dto.getTeam());
				newObject.append("results", dto.getResults());		

				/* Update the case study */
				coll.update(existingObject, newObject);

				logger.debug("Case study is updated : [" + newObject + "]");

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
