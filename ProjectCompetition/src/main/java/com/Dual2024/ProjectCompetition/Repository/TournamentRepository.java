package com.Dual2024.ProjectCompetition.Repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Dual2024.ProjectCompetition.Model.Format;
import com.Dual2024.ProjectCompetition.Model.Modality;
import com.Dual2024.ProjectCompetition.Model.Tournament;
import com.Dual2024.ProjectCompetition.Model.TournamentState;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {
	Tournament findByName(String name);

	List<Tournament> findByFormat(Format format);

	List<Tournament> findBySize(Integer size);

	List<Tournament> findByStartDate(LocalDateTime startDate);

	List<Tournament> findByEndDate(LocalDateTime endDate);

	List<Tournament> findByState(TournamentState state);

	List<Tournament> findByModality(Modality modality);

}
