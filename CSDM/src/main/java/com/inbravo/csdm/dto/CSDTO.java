package com.inbravo.csdm.dto;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import com.inbravo.csdm.exception.CSMException;
import com.mongodb.DBObject;

/**
 * 
 * @author amit.dixit
 * 
 */
public final class CSDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int csId;
	private String clientInfo;
	private String requirements;
	private String solution;
	private String businessValue;
	private String challenges;
	private String howChallengesHandled;
	private String nfr;
	private String stack;
	private String duration;
	private String team;
	private String results;

	public CSDTO() {

	}

	public CSDTO(final HttpServletRequest request) {

		/* Set all the values from request to DTO */
		if (request.getParameter("csId") != null) {
			this.setCsId(Integer.parseInt(request.getParameter("csId")));
		} else {
			throw new CSMException("Case study id is not found");
		}

		this.clientInfo = request.getParameter("clientInfo");
		this.requirements = request.getParameter("requirements");

	}

	public CSDTO(final DBObject object) {

		this.csId = Integer.parseInt("" + object.get("id"));
		this.clientInfo = "" + object.get("clientInfo");
		this.requirements = "" + object.get("requirements");
	}

	public CSDTO(final int csId, final String clientInfo, final String requirements, final String solution, final String businessValue,
			final String challenges, final String howChallengesHandled, final String nfr, final String stack, final String duration,
			final String team, final String results) {

		super();
		this.csId = csId;
		this.clientInfo = clientInfo;
		this.requirements = requirements;
		this.solution = solution;
		this.businessValue = businessValue;
		this.challenges = challenges;
		this.howChallengesHandled = howChallengesHandled;
		this.nfr = nfr;
		this.stack = stack;
		this.duration = duration;
		this.team = team;
		this.results = results;
	}

	public int getCsId() {
		return csId;
	}

	public void setCsId(int csId) {
		this.csId = csId;
	}

	public String getClientInfo() {
		return clientInfo;
	}

	public void setClientInfo(String clientInfo) {
		this.clientInfo = clientInfo;
	}

	public String getRequirements() {
		return requirements;
	}

	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public String getBusinessValue() {
		return businessValue;
	}

	public void setBusinessValue(String businessValue) {
		this.businessValue = businessValue;
	}

	public String getChallenges() {
		return challenges;
	}

	public void setChallenges(String challenges) {
		this.challenges = challenges;
	}

	public String getHowChallengesHandled() {
		return howChallengesHandled;
	}

	public void setHowChallengesHandled(String howChallengesHandled) {
		this.howChallengesHandled = howChallengesHandled;
	}

	public String getNfr() {
		return nfr;
	}

	public void setNfr(String nfr) {
		this.nfr = nfr;
	}

	public String getStack() {
		return stack;
	}

	public void setStack(String stack) {
		this.stack = stack;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getResults() {
		return results;
	}

	public void setResults(String results) {
		this.results = results;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
