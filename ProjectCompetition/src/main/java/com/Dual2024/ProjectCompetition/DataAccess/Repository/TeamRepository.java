package com.Dual2024.ProjectCompetition.DataAccess.Repository;

import com.Dual2024.ProjectCompetition.DataAccess.Model.Modality;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Team;
import com.Dual2024.ProjectCompetition.DataAccess.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interface for performing CRUD operations on Team.
 *
 * @author Francisco Balonero Olivera
 */
public interface TeamRepository extends JpaRepository<Team, Long> {

    /**
     * Finds all teams with the specified name.
     *
     * @param name the name of the team
     * @return a list of teams with the specified name
     */
    List<Team> findByName(String name);

    /**
     * Finds all teams with the specified modality.
     *
     * @param modality the modality of the team
     * @return a list of teams with the specified modality
     */
    List<Team> findByModality(Modality modality);

    /**
     * Finds all teams with the specified captain.
     *
     * @param captain the captain of the team
     * @return a list of teams with the specified captain
     */
    List<Team> findByCaptain(User captain);

}