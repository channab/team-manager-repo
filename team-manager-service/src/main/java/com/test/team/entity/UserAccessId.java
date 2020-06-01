package com.test.team.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@Getter 
@Setter 
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserAccessId implements Serializable{

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "empid", referencedColumnName = "empid")
	private User employee;
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subuser", referencedColumnName = "empid")
	private User subUser;
	
}
