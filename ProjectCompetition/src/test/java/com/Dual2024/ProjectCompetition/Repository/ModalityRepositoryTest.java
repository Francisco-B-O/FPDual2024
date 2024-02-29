package com.Dual2024.ProjectCompetition.Repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import com.Dual2024.ProjectCompetition.DataAccess.Model.Modality;
import com.Dual2024.ProjectCompetition.DataAccess.Repository.ModalityRepository;

@DataJpaTest(showSql = false)
public class ModalityRepositoryTest {
	@Autowired
	private ModalityRepository modalityRepository;
	private Modality modality, modality2, duplicatedNameModality;

	@BeforeEach
	public void setup() {
		modality = Modality.builder().name("modality1").numberPlayers(2).build();
		modality2 = Modality.builder().name("modality2").numberPlayers(2).build();
		duplicatedNameModality = Modality.builder().name("modality1").numberPlayers(2).build();

	}

	@Test
	@DisplayName("findById operation")
	public void givenId_whenFindById_theReturnUser() {

		Modality savedModality = modalityRepository.save(modality);

		Modality foundModality = modalityRepository.findById(modality.getId()).get();

		assertThat(foundModality).isNotNull();
		assertThat(foundModality).isEqualTo(savedModality);
	}

	@Test
	@DisplayName("save operation")
	public void givenModalityObject_whenSave_theReturnSavedUser() {

		Modality savedModality = modalityRepository.save(modality);

		assertThrows(DataIntegrityViolationException.class, () -> modalityRepository.save(duplicatedNameModality));
		assertThat(savedModality).isNotNull();
		assertThat(savedModality.getId()).isGreaterThan(0);
	}

	@Test
	@DisplayName("findAll operation")
	public void givenModalitiesList_whenFindAll_theReturnModalitiesList() {

		modalityRepository.save(modality);
		modalityRepository.save(modality2);

		List<Modality> modalities = modalityRepository.findAll();

		assertThat(modalities).isNotNull();
		assertThat(modalities.size()).isEqualTo(2);
	}

	@Test
	@DisplayName("findByName operation")
	public void givenModality_whenFindByName_theReturnModality() {

		Modality savedModality = modalityRepository.save(modality);

		Modality foundModality = modalityRepository.findByName("modality1");

		assertThat(foundModality).isNotNull();
		assertThat(foundModality).isEqualTo(savedModality);
	}

	@Test
	@DisplayName("findByNumberPlayers operation")
	public void givenNumberPlayers_whenFindByNumberPlayers_theReturnModalitiesList() {

		modalityRepository.save(modality);
		modalityRepository.save(modality2);

		List<Modality> modalities = modalityRepository.findByNumberPlayers(2);

		assertThat(modalities).isNotNull();
		assertThat(modalities.size()).isEqualTo(2);
	}

	@Test
	@DisplayName("update operation")
	public void givenModality_whenUpdate_theReturnUpdatedModality() {

		modalityRepository.save(modality);
		Modality updatedModality = new Modality();
		updatedModality.setId(modality.getId());
		updatedModality.setName("futbol");
		updatedModality.setNumberPlayers(11);

		Modality savedUpdatedModality = modalityRepository.save(updatedModality);

		assertThat(savedUpdatedModality).isNotNull();
		assertThat(savedUpdatedModality).isEqualTo(updatedModality);
	}

	@Test
	@DisplayName("delete operation")
	public void givenUser_whenDeleteById_thenDeleteUser() {

		modalityRepository.save(modality);

		modalityRepository.deleteById(modality.getId());

		Modality deletedModality = modalityRepository.findByName("modality1");
		assertThat(deletedModality).isNull();
	}
}
