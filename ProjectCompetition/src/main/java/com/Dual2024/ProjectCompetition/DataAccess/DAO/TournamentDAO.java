package com.Dual2024.ProjectCompetition.DataAccess.DAO;

import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Format;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Modality;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Tournament;
import com.Dual2024.ProjectCompetition.Utils.TournamentState;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Interface containing the TournamentDAO methods
 *
 * @author Francisco Balonero Olivera
 */
public interface TournamentDAO {
    /**
     * Method that saves a tournament
     *
     * @param tournament Tournament to save
     * @return Saved tournament
     * @throws DataException the data exception
     */
    Tournament save(Tournament tournament) throws DataException;

    /**
     * Method that searches a tournament by id
     *
     * @param id The id of the tournament you are looking for
     * @return The tournament found
     * @throws DataException the data exception
     */
    Tournament findById(Long id) throws DataException;

    /**
     * Method that returns a list with all the tournaments
     *
     * @return A list with all the tournaments
     * @throws DataException the data exception
     */
    List<Tournament> findAll() throws DataException;

    /**
     * Method that deletes a tournament by id
     *
     * @param id The id of the tournament to be deleted
     * @throws DataException the data exception
     */
    void delete(Long id) throws DataException;

    /**
     * Method that searches a list with tournaments with this name
     *
     * @param name The name of the tournaments you are looking for
     * @return A list with found tournaments
     * @throws DataException the data exception
     */
    List<Tournament> findByName(String name) throws DataException;

    /**
     * Method that searches a list with tournaments with this format
     *
     * @param format The format of the tournaments you are looking for
     * @return A list with found tournaments
     * @throws DataException the data exception
     */
    List<Tournament> findByFormat(Format format) throws DataException;

    /**
     * Method that searches a list with tournaments with this size
     *
     * @param size The size of the tournaments you are looking for
     * @return A list with found tournaments
     * @throws DataException the data exception
     */
    List<Tournament> findBySize(int size) throws DataException;

    /**
     * Method that searches a list with tournaments with this start date
     *
     * @param startDate The start date of the tournaments you are looking for
     * @return A list with found tournaments
     * @throws DataException the data exception
     */
    List<Tournament> findByStartDate(LocalDateTime startDate) throws DataException;

    /**
     * Method that searches a list with tournaments with this end date
     *
     * @param endDate The end date of the tournaments you are looking for
     * @return A list with found tournaments
     * @throws DataException the data exception
     */
    List<Tournament> findByEndDate(LocalDateTime endDate) throws DataException;

    /**
     * Method that searches a list with tournaments with this state
     *
     * @param state The state of the tournaments you are looking for
     * @return A list with found tournaments
     * @throws DataException the data exception
     */
    List<Tournament> findByState(TournamentState state) throws DataException;

    /**
     * Method that searches a list with tournaments with this modality
     *
     * @param modality The modality of the tournaments you are looking for
     * @return A list with found tournaments
     * @throws DataException the data exception
     */
    List<Tournament> findByModality(Modality modality) throws DataException;

}
