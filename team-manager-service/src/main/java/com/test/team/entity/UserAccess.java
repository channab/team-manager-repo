package com.test.team.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter 
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "useraccess")
public class UserAccess {

	@EmbeddedId
	private UserAccessId userAccessId;
	@Column(name="accesskey", length = 50)
	private String accessKey;
	@Column(length = 5)
	private String country;
	@Column(name="subuser_accesskey", length = 50)
	private String subUserAccessKey;
	@Column(name="subuser_country", length = 5)
	private String subUserCountry;
}
