package com.Dual2024.ProjectCompetition.DAO;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import com.Dual2024.ProjectCompetition.DataAccess.DAO.ModalityDAO;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Modality;

@DataJpaTest(showSql = false)
@ComponentScan(basePackages = "com.Dual2024.ProjectCompetition.DataAccess.DAO")
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
	@DisplayName("JUnit test for findById operation")
	public void givenId_whenFindById_theReturnUser() {

		Modality savedModality = null;
		try {
			savedModality = modalityDAO.save(modality);
		} catch (DataException e) {
			e.printStackTrace();
		}

		Modality foundModality = null;
		try {
			foundModality = modalityDAO.findById(modality.getId()).get();
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThat(foundModality).isNotNull();
		assertThat(foundModality).isEqualTo(savedModality);
	}

	@Test
	@DisplayName("JUnit test for save operation")
	public void givenModalityObject_whenSave_theReturnSavedUser() {

		Modality savedModality = null;
		try {
			savedModality = modalityDAO.save(modality);
		} catch (DataException e) {
			e.printStackTrace();
		}

		try {
			modalityDAO.save(duplicatedNameModality);
		} catch (DataException e) {
			assertThat(e).isNotNull();
			e.printStackTrace();
		}

		assertThat(savedModality).isNotNull();
		assertThat(savedModality.getId()).isGreaterThan(0);
	}

	@Test
	@DisplayName("JUnit test for findAll operation")
	public void givenModalitiesList_whenFindAll_theReturnModalitiesList() {

		try {
			modalityDAO.save(modality);
		} catch (DataException e) {
			e.printStackTrace();
		}
		try {
			modalityDAO.save(modality2);
		} catch (DataException e) {
			e.printStackTrace();
		}

		List<Modality> modalities = null;
		try {
			modalities = modalityDAO.findAll();
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThat(modalities).isNotNull();
		assertThat(modalities.size()).isEqualTo(2);
	}

	@Test
	@DisplayName("JUnit test for findByName operation")
	public void givenModality_whenFindByName_theReturnModality() {

		Modality savedModality = null;
		try {
			savedModality = modalityDAO.save(modality);
		} catch (DataException e) {
			e.printStackTrace();
		}

		Modality foundModality = null;
		try {
			foundModality = modalityDAO.findByName("modality1");
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThat(foundModality).isNotNull();
		assertThat(foundModality).isEqualTo(savedModality);
	}

	@Test
	@DisplayName("JUnit test for findByNumberPlayers operation")
	public void givenNumberPlayers_whenFindByNumberPlayers_theReturnModalitiesList() {

		try {
			modalityDAO.save(modality);
		} catch (DataException e) {
			e.printStackTrace();
		}
		try {
			modalityDAO.save(modality2);
		} catch (DataException e) {
			e.printStackTrace();
		}

		List<Modality> modalities = null;
		try {
			modalities = modalityDAO.findByNumberPlayers(2);
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThat(modalities).isNotNull();
		assertThat(modalities.size()).isEqualTo(2);
	}

	@Test
	@DisplayName("JUnit test for update operation")
	public void givenModality_whenUpdate_theReturnUpdatedModality() {

		try {
			modalityDAO.save(modality);
		} catch (DataException e) {
			e.printStackTrace();
		}
		Modality updatedModality = new Modality();
		updatedModality.setId(modality.getId());
		updatedModality.setName("futbol");
		updatedModality.setNumberPlayers(11);

		Modality savedUpdatedModality = null;
		try {
			savedUpdatedModality = modalityDAO.save(updatedModality);
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThat(savedUpdatedModality).isNotNull();
		assertThat(savedUpdatedModality).isEqualTo(updatedModality);
	}

	@Test
	@DisplayName("JUnit test for delete operation")
	public void givenUser_whenDelete_thenDeleteUser() {

		try {
			modalityDAO.save(modality);
		} catch (DataException e) {
			e.printStackTrace();
		}

		try {
			modalityDAO.delete(modality);
		} catch (DataException e) {
			e.printStackTrace();
		}

		Modality deletedModality = null;
		try {
			deletedModality = modalityDAO.findByName("modality1");
		} catch (DataException e) {
			assertThat(e).isNotNull();
			e.printStackTrace();
		}
		assertThat(deletedModality).isNull();
	}
}
