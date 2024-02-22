package com.Dual2024.ProjectCompetition.DataAccess.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Dual2024.ProjectCompetition.DataAccess.Model.Modality;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Team;
import com.Dual2024.ProjectCompetition.DataAccess.Model.User;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
	List<Team> findByName(String name);

	List<Team> findByModality(Modality modality);

	List<Team> findByCaptain(User captain);

}
