package com.inbravo.erws.service;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Employees")
public final class Employees
{
	@XmlElement(name = "Employee")
	public List<Employee> employees;

	public Employees()
	{

	}

	public Employees(final List<Employee> employees)
	{
		this.employees = employees;
	}
}
