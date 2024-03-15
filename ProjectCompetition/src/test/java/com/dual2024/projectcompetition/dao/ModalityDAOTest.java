package com.dual2024.projectcompetition.dao;

import com.dual2024.projectcompetition.dataaccess.dao.ModalityDAO;
import com.dual2024.projectcompetition.dataaccess.dataexception.DataException;
import com.dual2024.projectcompetition.dataaccess.model.Modality;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest(showSql = false)
@ComponentScan(basePackages = "com.dual2024.projectcompetition.dataaccess.dao")
public class ModalityDAOTest {
    @Autowired
    private ModalityDAO modalityDAO;
    private Modality modality, modality2, duplicatedNameModality;

    @BeforeEach
    public void setup() {
        modality = Modality.builder().name("modality1").numberPlayers(2).build();
        modality2 = Modality.builder().name("modality2").numberPlayers(2).build();
        duplicatedNameModality = Modality.builder().name("modality1").numberPlayers(2).build();

    }

    @Test
    @DisplayName("findById operation")
    public void givenId_whenFindById_theReturnUser() throws DataException {

        Modality savedModality = modalityDAO.save(modality);

        Modality foundModality = modalityDAO.findById(modality.getId());

        assertThat(foundModality).isNotNull();
        assertThat(foundModality).isEqualTo(savedModality);
    }

    @Test
    @DisplayName("save operation")
    public void givenModalityObject_whenSave_theReturnSavedUser() throws DataException {

        Modality savedModality = modalityDAO.save(modality);

        assertThrows(DataException.class, () -> modalityDAO.save(duplicatedNameModality));
        assertThat(savedModality).isNotNull();
        assertThat(savedModality.getId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("findAll operation")
    public void givenModalitiesList_whenFindAll_theReturnModalitiesList() throws DataException {

        modalityDAO.save(modality);
        modalityDAO.save(modality2);


        List<Modality> modalities = modalityDAO.findAll();

        assertThat(modalities).isNotNull();
        assertThat(modalities.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("findByName operation")
    public void givenModality_whenFindByName_theReturnModality() throws DataException {

        Modality savedModality = modalityDAO.save(modality);

        Modality foundModality = modalityDAO.findByName("modality1");

        assertThat(foundModality).isNotNull();
        assertThat(foundModality).isEqualTo(savedModality);
    }

    @Test
    @DisplayName("findByNumberPlayers operation")
    public void givenNumberPlayers_whenFindByNumberPlayers_theReturnModalitiesList() throws DataException {

        modalityDAO.save(modality);
        modalityDAO.save(modality2);

        List<Modality> modalities = modalityDAO.findByNumberPlayers(2);

        assertThat(modalities).isNotNull();
        assertThat(modalities.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("update operation")
    public void givenModality_whenUpdate_theReturnUpdatedModality() throws DataException {

        modalityDAO.save(modality);
        Modality updatedModality = new Modality();
        updatedModality.setId(modality.getId());
        updatedModality.setName("futbol");
        updatedModality.setNumberPlayers(11);

        Modality savedUpdatedModality = modalityDAO.save(updatedModality);

        assertThat(savedUpdatedModality).isNotNull();
        assertThat(savedUpdatedModality).isEqualTo(updatedModality);
    }

    @Test
    @DisplayName("delete operation")
    public void givenUser_whenDelete_thenDeleteUser() throws DataException {

        modalityDAO.save(modality);

        modalityDAO.delete(modality.getId());

        assertThrows(DataException.class, () -> modalityDAO.findByName("modality1"));
    }
}
