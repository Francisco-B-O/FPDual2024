package com.Dual2024.ProjectCompetition.DataAccess.Repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Dual2024.ProjectCompetition.DataAccess.Model.Format;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Modality;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Tournament;
import com.Dual2024.ProjectCompetition.DataAccess.Model.TournamentState;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {
	Tournament findByName(String name);

	List<Tournament> findByFormat(Format format);

	List<Tournament> findBySize(int size);

	List<Tournament> findByStartDate(LocalDateTime startDate);

	List<Tournament> findByEndDate(LocalDateTime endDate);

	List<Tournament> findByState(TournamentState state);

	List<Tournament> findByModality(Modality modality);

}
