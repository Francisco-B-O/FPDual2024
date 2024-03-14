package com.dual2024.projectcompetition.dao;

import com.dual2024.projectcompetition.dataaccess.dao.FormatDAO;
import com.dual2024.projectcompetition.dataaccess.dataexception.DataException;
import com.dual2024.projectcompetition.dataaccess.model.Format;
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
@ComponentScan(basePackages = "com.dual2024.projectcompetition.dataAccess.dao")
public class FormatDAOTest {
    @Autowired
    private FormatDAO formatDAO;
    private Format format, format2, duplicatedNameFormat;

    @BeforeEach
    public void setup() {
        format = Format.builder().name("torneo").build();
        format2 = Format.builder().name("liga").build();
        duplicatedNameFormat = Format.builder().name("torneo").build();

    }

    @Test
    @DisplayName("findById operation")
    public void givenId_whenFindById_theReturnFormat() throws DataException {

        Format savedFormat = null;
        savedFormat = formatDAO.save(format);

        Format foundFormat = formatDAO.findById(format.getId());

        assertThat(foundFormat).isNotNull();
        assertThat(foundFormat).isEqualTo(savedFormat);
    }

    @Test
    @DisplayName("save operation")
    public void givenFormatObject_whenSave_theReturnSavedFormat() throws DataException {

        Format savedFormat = formatDAO.save(format);

        assertThrows(DataException.class, () -> formatDAO.save(duplicatedNameFormat));
        assertThat(savedFormat).isNotNull();
        assertThat(savedFormat.getId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("findAll operation")
    public void givenFormatList_whenSave_theReturnFormatList() throws DataException {

        formatDAO.save(format);
        formatDAO.save(format2);

        List<Format> formats = formatDAO.findAll();

        assertThat(formats).isNotNull();
        assertThat(formats.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("findByName operation")
    public void givenFormat_whenFindByName_theReturnFormat() throws DataException {

        Format savedFormat = formatDAO.save(format);

        Format foundFormat = formatDAO.findByName("torneo");

        assertThat(foundFormat).isNotNull();
        assertThat(foundFormat).isEqualTo(savedFormat);
    }

    @Test
    @DisplayName("update operation")
    public void givenFormat_whenUpdate_theReturnUpdatedFormat() throws DataException {

        formatDAO.save(format);
        Format updatedFormat = new Format();
        updatedFormat.setId(format.getId());
        updatedFormat.setName("playOff");

        Format savedUpdatedFormat = formatDAO.save(updatedFormat);

        assertThat(savedUpdatedFormat).isNotNull();
        assertThat(savedUpdatedFormat).isEqualTo(updatedFormat);
    }

    @Test
    @DisplayName("delete operation")
    public void givenFormat_whenDelete_thenDeletedFormat() throws DataException {

        formatDAO.save(format);

        formatDAO.delete(format.getId());

        assertThrows(DataException.class, () -> formatDAO.findByName("torneo"));
    }
}
