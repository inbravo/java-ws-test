package com.inbravo.erws.dao;

import javax.xml.bind.annotation.XmlRootElement;


import com.mongodb.DBObject;

/**
 * 
 * @author amit.dixit
 * 
 */
@XmlRootElement
public final class EmployeeDAO {

	private int empId;
	private String empName;
	private String phone;
	private String email;
	private float salary;
	private String designation;

	public EmployeeDAO() {

	}

	public EmployeeDAO(final DBObject object) {

		this.empId = Integer.parseInt("" + object.get("id"));
		this.empName = "" + object.get("name");
		this.designation = "" + object.get("designation");
		this.email = "" + object.get("email");
		this.phone = "" + object.get("phone");
		this.salary = Float.parseFloat("" + object.get("salary"));
	}

	public EmployeeDAO(final int empId, final String empName,
			final String phone, final String email, final float salary,
			final String designation) {

		super();
		this.empId = empId;
		this.empName = empName;
		this.phone = phone;
		this.email = email;
		this.salary = salary;
		this.designation = designation;
	}

	/**
	 * @return the empId
	 */
	public final int getEmpId() {
		return this.empId;
	}

	/**
	 * @param empId
	 *            the empId to set
	 */
	public final void setEmpId(final int empId) {
		this.empId = empId;
	}

	/**
	 * @return the empName
	 */
	public final String getEmpName() {
		return this.empName;
	}

	/**
	 * @param empName
	 *            the empName to set
	 */
	public final void setEmpName(final String empName) {
		this.empName = empName;
	}

	/**
	 * @return the phone
	 */
	public final String getPhone() {
		return this.phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public final void setPhone(final String phone) {
		this.phone = phone;
	}

	/**
	 * @return the email
	 */
	public final String getEmail() {
		return this.email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public final void setEmail(final String email) {
		this.email = email;
	}

	/**
	 * @return the salary
	 */
	public final float getSalary() {
		return this.salary;
	}

	/**
	 * @param salary
	 *            the salary to set
	 */
	public final void setSalary(final float salary) {
		this.salary = salary;
	}

	/**
	 * @return the designation
	 */
	public final String getDesignation() {
		return this.designation;
	}

	/**
	 * @param designation
	 *            the designation to set
	 */
	public final void setDesignation(final String designation) {
		this.designation = designation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		return "EmployeeDAO [empId=" + this.empId + ", empName=" + this.empName
				+ ", phone=" + this.phone + ", email=" + this.email
				+ ", salary=" + this.salary + ", designation="
				+ this.designation + "]";
	}
}