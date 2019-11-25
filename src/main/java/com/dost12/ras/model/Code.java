package com.dost12.ras.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="Code")
public class Code implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2969658059794026367L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty
	@Column(name="code", unique=true, nullable=false)
	private String code;
	
	private Boolean status;
	
	@ManyToOne
	@JoinColumn(name="barangay_id")
	private Barangay barangay;

	public Code() {		
		this.code = UUID.randomUUID().toString().substring(20).toUpperCase();
		status = false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Barangay getBarangay() {
		return barangay;
	}

	public void setBarangay(Barangay barangay) {
		this.barangay = barangay;
	}
	
}
