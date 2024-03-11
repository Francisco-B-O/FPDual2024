package com.dual2024.projectcompetition.Repository;

import com.dual2024.projectcompetition.dataaccess.model.Format;
import com.dual2024.projectcompetition.dataaccess.repository.FormatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    @DisplayName("findById operation")
    public void givenId_whenFindById_theReturnFormat() {

        Format savedFormat = formatRepository.save(format);

        Format foundFormat = formatRepository.findById(format.getId()).get();

        assertThat(foundFormat).isNotNull();
        assertThat(foundFormat).isEqualTo(savedFormat);
    }

    @Test
    @DisplayName("save operation")
    public void givenFormatObject_whenSave_theReturnSavedFormat() {

        Format savedFormat = formatRepository.save(format);

        assertThrows(DataIntegrityViolationException.class, () -> formatRepository.save(duplicatedNameFormat));
        assertThat(savedFormat).isNotNull();
        assertThat(savedFormat.getId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("findAll operation")
    public void givenFormatList_whenSave_theReturnFormatList() {

        formatRepository.save(format);
        formatRepository.save(format2);

        List<Format> formats = formatRepository.findAll();

        assertThat(formats).isNotNull();
        assertThat(formats.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("findByName operation")
    public void givenFormat_whenFindByName_theReturnFormat() {

        Format savedFormat = formatRepository.save(format);

        Format foundFormat = formatRepository.findByName("torneo").get();

        assertThat(foundFormat).isNotNull();
        assertThat(foundFormat).isEqualTo(savedFormat);
    }

    @Test
    @DisplayName("update operation")
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
    @DisplayName("delete operation")
    public void givenFormat_whenDeleteById_thenDeletedFormat() {

        formatRepository.save(format);

        formatRepository.deleteById(format.getId());
        Optional<Format> deletedFormat = formatRepository.findByName("torneo");

        assertThat(deletedFormat).isNotPresent();
    }
}
