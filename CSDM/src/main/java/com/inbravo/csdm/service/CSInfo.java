package com.inbravo.csdm.service;

import java.util.List;

import com.inbravo.csdm.dto.CSDTO;

/**
 * 
 * @author amit.dixit
 *
 */
public interface CSInfo {

	public void init() throws Exception;

	public void create(final CSDTO dao) throws Exception;

	public List<CSDTO> readAll() throws Exception;

	public CSDTO read(final int csId) throws Exception;

	public void update(final CSDTO dao) throws Exception;

	public void delete(final int csId) throws Exception;
}
