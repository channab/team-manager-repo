package com.test.team.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.test.team.entity.TeamHeirarchy;
import com.test.team.entity.TeamHeirarchyKey;
import com.test.team.entity.User;

public interface TeamHeirarchyRepository extends CrudRepository<TeamHeirarchy, TeamHeirarchyKey> {

	List<TeamHeirarchy> findByTeamHeirarchyIdEmployee(User employee);

	@Query("SELECT th from TeamHeirarchy th WHERE teamHeirarchyId.manager IN (:managers)")
	public List<TeamHeirarchy> findByTeamHeiraychyIdManagers(@Param("managers") List<User> managers);

	@Query("SELECT th from TeamHeirarchy th WHERE teamHeirarchyId.manager = :manager AND teamHeirarchyId.employee != :employee")
	public List<TeamHeirarchy> findPeersByManager(@Param("manager") User manager, @Param("employee") User employee);

	@Query("SELECT COUNT(th) from TeamHeirarchy th WHERE teamHeirarchyId.manager = :manager")
	public long countImmediateEmployees(@Param("manager") User manager);

}
