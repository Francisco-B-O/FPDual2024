package com.Dual2024.ProjectCompetition.Repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.Dual2024.ProjectCompetition.Model.Modality;


@DataJpaTest(showSql = false)
@Order(3)
public class ModalityRepositoryTest {
	@Autowired
	private ModalityRepository modalityRepository;
	private Modality modality;

	@BeforeEach
	public void setup() {
		modality = new Modality();
		modality.setName("modality1");
		modality.setNumberPlayers(2);
	}

	@Test
	@DisplayName("JUnit test for save operation")
	public void givenModalityObject_whenSave_theReturnSavedUser() {
		Modality savedModality = modalityRepository.save(modality);
		assertThat(savedModality).isNotNull();
		assertThat(savedModality.getId()).isGreaterThan(0);
	}

	@Test
	@DisplayName("JUnit test for findAll operation")
	public void givenModalitiesList_whenFindAll_theReturnModalitiesList() {
		Modality modality2 = new Modality();
		modality2.setName("tenis");
		modality2.setNumberPlayers(2);
		modalityRepository.save(modality);
		modalityRepository.save(modality2);
		List<Modality> modalities = modalityRepository.findAll();
		assertThat(modalities).isNotNull();
		assertThat(modalities.size()).isEqualTo(2);
	}

	@Test
	@DisplayName("JUnit test for findByName operation")
	public void givenModality_whenFindByName_theReturnModality() {
		modalityRepository.save(modality);
		Modality foundModality = modalityRepository.findByName("modality1");
		assertThat(foundModality).isNotNull();
		assertThat(foundModality.getId()).isEqualTo(modality.getId());
	}

	@Test
	@DisplayName("JUnit test for findByNumberPlayers operation")
	public void givenNumberPlayers_whenFindByNumberPlayers_theReturnModalitiesList() {
		Modality modality2 = new Modality();
		modality2.setName("tenis");
		modality2.setNumberPlayers(2);
		modalityRepository.save(modality);
		modalityRepository.save(modality2);
		List<Modality> modalities = modalityRepository.findByNumberPlayers(2);
		assertThat(modalities).isNotNull();
		assertThat(modalities.size()).isEqualTo(2);
	}

	@Test
	@DisplayName("JUnit test for update operation")
	public void givenModality_whenUpdate_theReturnUpdatedModality() {
		modalityRepository.save(modality);
		Modality updatedModality = new Modality();
		updatedModality.setId(modality.getId());
		updatedModality.setName("futbol");
		updatedModality.setNumberPlayers(11);
		Modality savedUpdatedModality = modalityRepository.save(updatedModality);
		assertThat(savedUpdatedModality).isNotNull();
		assertThat(savedUpdatedModality.getId()).isEqualTo(modality.getId());
	}

	@Test
	@DisplayName("JUnit test for delete operation")
	public void givenUser_whenDelete_thenDeleteUser() {
		modalityRepository.save(modality);
		modalityRepository.delete(modality);
		Modality deletedModality = modalityRepository.findByName("modality1");
		assertThat(deletedModality).isNull();
	}
}
