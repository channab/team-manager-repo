package com.test.team.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor
@Entity
@Table(name = "teamheirarchy")
public class TeamHeirarchy {

	@EmbeddedId
	private TeamHeirarchyKey teamHeirarchyId;
}
