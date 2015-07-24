package com.inbravo.ss.dto;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import com.inbravo.ss.exception.EmployeeException;
import com.mongodb.DBObject;

/**
 * 
 * @author amit.dixit
 * 
 */
public final class EmployeeDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int empId;
	private String empName;
	private String phone;
	private String email;
	private float salary;
	private String designation;

	public EmployeeDTO() {

	}

	public EmployeeDTO(final HttpServletRequest request) {

		/* Set all the values from user in DTO */
		if (request.getParameter("empId") != null) {
			this.setEmpId(Integer.parseInt(request.getParameter("empId")));
		} else {
			throw new EmployeeException("Employee id is not found");
		}
		if (request.getParameter("empName") != null) {
			this.setEmpName(request.getParameter("empName"));
		} else {
			throw new EmployeeException("Employee name is not found");
		}
		if (request.getParameter("phone") != null) {
			this.setPhone(request.getParameter("phone"));
		} else {
			throw new EmployeeException("Employee phone is not found");
		}
		if (request.getParameter("email") != null) {
			this.setEmail(request.getParameter("email"));
		} else {
			throw new EmployeeException("Employee email is not found");
		}
		if (request.getParameter("salary") != null) {
			this.setSalary(Float.parseFloat(request.getParameter("salary")));
		} else {
			throw new EmployeeException("Employee salary is not found");
		}
		if (request.getParameter("designation") != null) {
			this.setDesignation(request.getParameter("designation"));
		} else {
			throw new EmployeeException("Employee designation is not found");
		}
	}

	public EmployeeDTO(final com.inbravo.esws.service.Employee employee) {

		/* Set all the values from user in DTO */
		this.setEmpId(employee.getEmpId());
		this.setEmpName(employee.getEmpName());
		this.setPhone(employee.getPhone());
		this.setEmail(employee.getEmail());
		this.setSalary(employee.getSalary());
		this.setDesignation(employee.getDesignation());
	}

	public EmployeeDTO(final com.inbravo.erws.service.Employee employee) {

		/* Set all the values from user in DTO */
		this.setEmpId(employee.getEmpId());
		this.setEmpName(employee.getEmpName());
		this.setPhone(employee.getPhone());
		this.setEmail(employee.getEmail());
		this.setSalary(employee.getSalary());
		this.setDesignation(employee.getDesignation());
	}

	public EmployeeDTO(final DBObject object) {

		this.empId = Integer.parseInt("" + object.get("id"));
		this.empName = "" + object.get("name");
		this.designation = "" + object.get("designation");
		this.email = "" + object.get("email");
		this.phone = "" + object.get("phone");
		this.salary = Float.parseFloat("" + object.get("salary"));
	}

	public EmployeeDTO(final int empId, final String empName, final String phone, final String email, final float salary,
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
		return EmployeeDTO.class.getCanonicalName() + " [empId=" + this.empId + ", empName=" + this.empName + ", phone=" + this.phone
				+ ", email=" + this.email + ", salary=" + this.salary + ", designation=" + this.designation + "]";
	}
}
