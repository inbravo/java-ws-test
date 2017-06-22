package com.inbravo.csdm.jdbc;

import com.inbravo.csdm.service.CSInfo;

/**
 * 
 * @author amit.dixit
 * 
 */
public interface CSInfoAtDB extends CSInfo {

	public void initDB() throws Exception;
}
