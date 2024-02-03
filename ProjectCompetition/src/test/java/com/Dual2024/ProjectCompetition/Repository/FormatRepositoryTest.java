package com.Dual2024.ProjectCompetition.Repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.Dual2024.ProjectCompetition.Model.Format;

@DataJpaTest(showSql = false)
@Order(5)
public class FormatRepositoryTest {
	@Autowired
	FormatRepository formatRepository;
	private Format format;

	@BeforeEach
	public void setup() {
		format = new Format();
		format.setName("torneo");
	}

	@Test
	@DisplayName("JUnit test for save operation")
	public void givenFormatObject_whenSave_theReturnSavedFormat() {
		Format savedFormat = formatRepository.save(format);
		assertThat(savedFormat).isNotNull();
		assertThat(savedFormat.getId()).isGreaterThan(0);
	}

	@Test
	@DisplayName("JUnit test for findAll operation")
	public void givenFormatList_whenSave_theReturnFormatList() {
		Format format2 = new Format();
		format2.setName("liga");
		formatRepository.save(format);
		formatRepository.save(format2);
		List<Format> formats = formatRepository.findAll();
		assertThat(formats).isNotNull();
		assertThat(formats.size()).isEqualTo(2);
	}

	@Test
	@DisplayName("JUnit test for findByName operation")
	public void givenFormat_whenFindByName_theReturnFormat() {
		formatRepository.save(format);
		Format foundFormat = formatRepository.findByName("torneo");
		assertThat(foundFormat).isNotNull();
		assertThat(foundFormat.getId()).isEqualTo(format.getId());
	}

	@Test
	@DisplayName("JUnit test for update operation")
	public void givenFormat_whenUpdate_theReturnUpdatedFormat() {
		formatRepository.save(format);
		Format updatedFormat = new Format();
		updatedFormat.setId(format.getId());
		updatedFormat.setName("liga");
		Format savedUpdatedFormat = formatRepository.save(updatedFormat);
		assertThat(savedUpdatedFormat).isNotNull();
		assertThat(savedUpdatedFormat.getId()).isEqualTo(format.getId());
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
