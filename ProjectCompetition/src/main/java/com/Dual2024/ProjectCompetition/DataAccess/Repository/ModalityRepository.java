package com.Dual2024.ProjectCompetition.DataAccess.Repository;

import com.Dual2024.ProjectCompetition.DataAccess.Model.Modality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ModalityRepository extends JpaRepository<Modality, Long> {
    List<Modality> findByNumberPlayers(int numberPlayers);

    Modality findByName(String name);

}
