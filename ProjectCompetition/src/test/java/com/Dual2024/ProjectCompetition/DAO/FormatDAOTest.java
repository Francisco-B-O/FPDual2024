package com.Dual2024.ProjectCompetition.DAO;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import com.Dual2024.ProjectCompetition.DataAccess.DAO.FormatDAO;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Format;

@DataJpaTest(showSql = false)
@ComponentScan(basePackages = "com.Dual2024.ProjectCompetition.DataAccess.DAO")
public class FormatDAOTest {
	@Autowired
	FormatDAO formatDAO;
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

		Format savedFormat = null;
		try {
			savedFormat = formatDAO.save(format);
		} catch (DataException e) {
			e.printStackTrace();
		}

		Format foundFormat = null;
		try {
			foundFormat = formatDAO.findById(format.getId()).get();
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThat(foundFormat).isNotNull();
		assertThat(foundFormat).isEqualTo(savedFormat);
	}

	@Test
	@DisplayName("JUnit test for save operation")
	public void givenFormatObject_whenSave_theReturnSavedFormat() {

		Format savedFormat = null;
		try {
			savedFormat = formatDAO.save(format);
		} catch (DataException e) {
			e.printStackTrace();
		}

		try {
			formatDAO.save(duplicatedNameFormat);
		} catch (DataException e) {
			assertThat(e).isNotNull();
			e.printStackTrace();
		}

		assertThat(savedFormat).isNotNull();
		assertThat(savedFormat.getId()).isGreaterThan(0);
	}

	@Test
	@DisplayName("JUnit test for findAll operation")
	public void givenFormatList_whenSave_theReturnFormatList() {

		try {
			formatDAO.save(format);
		} catch (DataException e) {
			e.printStackTrace();
		}
		try {
			formatDAO.save(format2);
		} catch (DataException e) {
			e.printStackTrace();
		}

		List<Format> formats = null;
		try {
			formats = formatDAO.findAll();
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThat(formats).isNotNull();
		assertThat(formats.size()).isEqualTo(2);
	}

	@Test
	@DisplayName("JUnit test for findByName operation")
	public void givenFormat_whenFindByName_theReturnFormat() {

		Format savedFormat = null;
		try {
			savedFormat = formatDAO.save(format);
		} catch (DataException e) {
			e.printStackTrace();
		}

		Format foundFormat = null;
		try {
			foundFormat = formatDAO.findByName("torneo");
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThat(foundFormat).isNotNull();
		assertThat(foundFormat).isEqualTo(savedFormat);
	}

	@Test
	@DisplayName("JUnit test for update operation")
	public void givenFormat_whenUpdate_theReturnUpdatedFormat() {

		try {
			formatDAO.save(format);
		} catch (DataException e) {
			e.printStackTrace();
		}
		Format updatedFormat = new Format();
		updatedFormat.setId(format.getId());
		updatedFormat.setName("playOff");

		Format savedUpdatedFormat = null;
		try {
			savedUpdatedFormat = formatDAO.save(updatedFormat);
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThat(savedUpdatedFormat).isNotNull();
		assertThat(savedUpdatedFormat).isEqualTo(updatedFormat);
	}

	@Test
	@DisplayName("JUnit test for delete operation")
	public void givenFormat_whenDelete_thenDeletedFormat() {

		try {
			formatDAO.save(format);
		} catch (DataException e) {
			e.printStackTrace();
		}

		try {
			formatDAO.delete(format);
		} catch (DataException e) {
			e.printStackTrace();
		}
		Format deletedFormat = null;
		try {
			deletedFormat = formatDAO.findByName("torneo");
		} catch (DataException e) {
			assertThat(e).isNotNull();
			e.printStackTrace();
		}

		assertThat(deletedFormat).isNull();
	}
}
