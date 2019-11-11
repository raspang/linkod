package com.websystique.springmvc.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Voter")
public class Voter implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2969658059794026367L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private boolean printed = false;
	
	
	@NotEmpty
	@Column(name="code", unique=true, nullable=false)
	private String code;
	
	@JsonIgnore
	private String firstName ="";
	
	@JsonIgnore
	private String middleName ="";
	
	@JsonIgnore
	private String lastName ="";
	
	private String company;
	
	private String designation;
	
	private String contact;

	private Integer age = 0;
	
	private String gender;
	
	private String status;
	
	private String business;
	
	private String attended;
	
	
	
	@Transient
	private String completeName;
	

	public Voter() {		
		this.code = "R12"+UUID.randomUUID().toString().substring(23).toUpperCase();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public boolean isPrinted() {
		return printed;
	}

	public void setPrinted(boolean printed) {
		this.printed = printed;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getGender() {
		return gender;
	}

	public String getContact() {
		return contact;
	}

	public String getAttended() {
		return attended;
	}

	public void setAttended(String attended) {
		this.attended = attended;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCompleteName() {
		if(lastName.isEmpty() && firstName.isEmpty())
			return "";
		if(middleName.length() > 0)
			return lastName.toUpperCase()+", "+firstName.toUpperCase()+" "+middleName.substring(0, 1).toUpperCase();
		return lastName.toUpperCase()+", "+firstName.toUpperCase();
	}

	public void setCompleteName(String completeName) {
		this.completeName = completeName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}



	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}


	
}
