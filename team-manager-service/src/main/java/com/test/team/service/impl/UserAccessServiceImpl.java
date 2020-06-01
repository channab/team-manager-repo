package com.test.team.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.team.entity.TeamHeirarchy;
import com.test.team.entity.User;
import com.test.team.entity.UserAccess;
import com.test.team.entity.UserAccessId;
import com.test.team.repository.TeamHeirarchyRepository;
import com.test.team.repository.UserAccessRepository;
import com.test.team.repository.UserRepository;
import com.test.team.service.UserAccessService;

@Service
public class UserAccessServiceImpl implements UserAccessService {

	@Autowired
	private UserAccessRepository userAccessRepository;

	@Autowired
	private TeamHeirarchyRepository teamHeirarchyRepository;

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public void addUserAccess(String empId) {

		List<UserAccess> userAccesses = getUserAccesses(empId);
		
		userAccessRepository.saveAll(userAccesses);

	}

	@Transactional
	public void addAllUsersAccess() {
		
		List<UserAccess> userAccesses = new ArrayList<>();
		
		Iterable<User> users = userRepository.findAll();
		
		users.forEach(u -> userAccesses.addAll(getUserAccesses(u.getEmpId())));
		
		userAccessRepository.saveAll(userAccesses);
	}
	
	private List<UserAccess> getUserAccesses(String empId) {
		Optional<User> currentUser = userRepository.findById(empId);
		
		if(!currentUser.isPresent()) {
			throw new EntityNotFoundException(String.format("User : %s not found.", empId));
		}

		List<UserAccess> userAccesses = new ArrayList<>();
		// get manager

		List<TeamHeirarchy> managerHeirarchies = teamHeirarchyRepository.findByTeamHeirarchyIdEmployee(new User(empId));
		
		if(!managerHeirarchies.isEmpty()) {
			UserAccessId userAccessId = new UserAccessId(managerHeirarchies.get(0).getTeamHeirarchyId().getEmployee(),
					managerHeirarchies.get(0).getTeamHeirarchyId().getManager());

			UserAccess userAccess = new UserAccess(userAccessId,
					managerHeirarchies.get(0).getTeamHeirarchyId().getEmployee().getAccessKey(),
					managerHeirarchies.get(0).getTeamHeirarchyId().getEmployee().getCountry(),
					managerHeirarchies.get(0).getTeamHeirarchyId().getManager().getAccessKey(),
					managerHeirarchies.get(0).getTeamHeirarchyId().getManager().getCountry());

			userAccesses.add(userAccess);
		}
		
		//add self to user access
		
		UserAccessId userAccessId = new UserAccessId(currentUser.get(),
				currentUser.get());

		UserAccess userAccess = new UserAccess(userAccessId,
				currentUser.get().getAccessKey(),
				currentUser.get().getCountry(),
				currentUser.get().getAccessKey(),
				currentUser.get().getCountry());

		userAccesses.add(userAccess);

		// get team members

		List<User> usersCurrentLevel = Stream.of(new User(empId)).collect(Collectors.toList());

		List<TeamHeirarchy> teamHeirarchies = null;

		do {

			teamHeirarchies = teamHeirarchyRepository.findByTeamHeiraychyIdManagers(usersCurrentLevel);

			usersCurrentLevel.clear();

			teamHeirarchies.forEach(th -> {
				usersCurrentLevel.add(th.getTeamHeirarchyId().getEmployee());

				UserAccessId userAccessIdSelf = new UserAccessId(th.getTeamHeirarchyId().getManager(),
						th.getTeamHeirarchyId().getEmployee());

				UserAccess userAccessSelf = new UserAccess(userAccessIdSelf,
						th.getTeamHeirarchyId().getManager().getAccessKey(),
						th.getTeamHeirarchyId().getManager().getCountry(),
						th.getTeamHeirarchyId().getEmployee().getAccessKey(),
						th.getTeamHeirarchyId().getEmployee().getCountry());

				userAccesses.add(userAccessSelf);

			});

		} while (!teamHeirarchies.isEmpty());

		// get peers who are not managers

		if (!managerHeirarchies.isEmpty()) {

			List<TeamHeirarchy> peerHeirarchies = teamHeirarchyRepository
					.findPeersByManager(managerHeirarchies.get(0).getTeamHeirarchyId().getManager(), new User(empId));

			peerHeirarchies.forEach(ph -> {

				// check pear is manager

				long employeeCount = teamHeirarchyRepository
						.countImmediateEmployees(ph.getTeamHeirarchyId().getEmployee());

				if (employeeCount == 0) {
					UserAccessId userAccessIdPeer = new UserAccessId(new User(empId),
							ph.getTeamHeirarchyId().getEmployee());

					UserAccess userAccessPeer = new UserAccess(userAccessIdPeer, currentUser.get().getAccessKey(),
							currentUser.get().getCountry(), ph.getTeamHeirarchyId().getEmployee().getAccessKey(),
							ph.getTeamHeirarchyId().getEmployee().getCountry());

					userAccesses.add(userAccessPeer);
				}

			});
		}
		return userAccesses;
	}

}
