package com.Dual2024.ProjectCompetition.Repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import com.Dual2024.ProjectCompetition.DataAccess.Model.Format;
import com.Dual2024.ProjectCompetition.DataAccess.Repository.FormatRepository;

@DataJpaTest(showSql = false)
public class FormatRepositoryTest {
	@Autowired
	FormatRepository formatRepository;
	private Format format, format2, duplicatedNameFormat;

	@BeforeEach
	public void setup() {
		format = Format.builder().name("torneo").build();
		format2 = Format.builder().name("liga").build();
		duplicatedNameFormat = Format.builder().name("torneo").build();

	}

	@Test
	@DisplayName("JUnit test for findById operation")
	public void givenId_whenFindById_theReturnFormat() {

		Format savedFormat = formatRepository.save(format);

		Format foundFormat = formatRepository.findById(format.getId()).get();

		assertThat(foundFormat).isNotNull();
		assertThat(foundFormat).isEqualTo(savedFormat);
	}

	@Test
	@DisplayName("JUnit test for save operation")
	public void givenFormatObject_whenSave_theReturnSavedFormat() {

		Format savedFormat = formatRepository.save(format);

		try {
			formatRepository.save(duplicatedNameFormat);
		} catch (DataIntegrityViolationException e) {
			assertThat(e).isNotNull();
		}

		assertThat(savedFormat).isNotNull();
		assertThat(savedFormat.getId()).isGreaterThan(0);
	}

	@Test
	@DisplayName("JUnit test for findAll operation")
	public void givenFormatList_whenSave_theReturnFormatList() {

		formatRepository.save(format);
		formatRepository.save(format2);

		List<Format> formats = formatRepository.findAll();

		assertThat(formats).isNotNull();
		assertThat(formats.size()).isEqualTo(2);
	}

	@Test
	@DisplayName("JUnit test for findByName operation")
	public void givenFormat_whenFindByName_theReturnFormat() {

		Format savedFormat = formatRepository.save(format);

		Format foundFormat = formatRepository.findByName("torneo");

		assertThat(foundFormat).isNotNull();
		assertThat(foundFormat).isEqualTo(savedFormat);
	}

	@Test
	@DisplayName("JUnit test for update operation")
	public void givenFormat_whenUpdate_theReturnUpdatedFormat() {

		formatRepository.save(format);
		Format updatedFormat = new Format();
		updatedFormat.setId(format.getId());
		updatedFormat.setName("playOff");

		Format savedUpdatedFormat = formatRepository.save(updatedFormat);

		assertThat(savedUpdatedFormat).isNotNull();
		assertThat(savedUpdatedFormat).isEqualTo(updatedFormat);
	}

	@Test
	@DisplayName("JUnit test for delete operation")
	public void givenFormat_whenDelete_thenDeletedFormat() {

		formatRepository.save(format);

		formatRepository.delete(format);
		Format deletedFormat = formatRepository.findByName("torneo");

		assertThat(deletedFormat).isNull();
	}
}
