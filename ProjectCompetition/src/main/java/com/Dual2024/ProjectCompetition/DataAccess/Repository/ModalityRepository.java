package com.Dual2024.ProjectCompetition.DataAccess.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Dual2024.ProjectCompetition.DataAccess.Model.Modality;

@Repository
public interface ModalityRepository extends JpaRepository<Modality, Long> {
	List<Modality> findByNumberPlayers(int numberPlayers);

	Modality findByName(String name);

}
