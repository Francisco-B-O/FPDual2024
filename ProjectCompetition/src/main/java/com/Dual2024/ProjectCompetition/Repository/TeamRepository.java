package com.Dual2024.ProjectCompetition.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Dual2024.ProjectCompetition.Model.Modality;
import com.Dual2024.ProjectCompetition.Model.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
	Team findByName(String name);

	List<Team> findByModality(Modality modality);

}
