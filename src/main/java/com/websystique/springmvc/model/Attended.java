package com.websystique.springmvc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Attended implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5358721163337443521L;
	
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column(unique=true, nullable=false)
    private Date date;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="voter_id", updatable = false, nullable = false)
    @JsonIgnore
    private Voter voter;
    

	public Attended() {
		super();
		date = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Voter getVoter() {
		return voter;
	}

	public void setVoter(Voter voter) {
		this.voter = voter;
	}
    
    

}
