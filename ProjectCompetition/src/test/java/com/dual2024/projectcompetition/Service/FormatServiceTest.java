package com.dual2024.projectcompetition.Service;

import com.dual2024.projectcompetition.dataaccess.dao.FormatDAO;
import com.dual2024.projectcompetition.dataaccess.dataexception.DataException;
import com.dual2024.projectcompetition.dataaccess.dataexception.EntityNotFoundException;
import com.dual2024.projectcompetition.dataaccess.model.Format;
import com.dual2024.projectcompetition.business.businessexception.BusinessException;
import com.dual2024.projectcompetition.business.businessexception.DuplicatedNameException;
import com.dual2024.projectcompetition.business.businessexception.FormatNotFoundException;
import com.dual2024.projectcompetition.business.businessobject.FormatBO;
import com.dual2024.projectcompetition.business.businessobject.converters.BOToModelConverter;
import com.dual2024.projectcompetition.business.businessobject.converters.ModelToBOConverter;
import com.dual2024.projectcompetition.business.service.FormatServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class FormatServiceTest {
    private FormatBO formatBO;
    private Format format;

    @Mock
    FormatDAO FormatDAO;
    @Mock
    ModelToBOConverter modelToBOConverter;
    @Mock
    BOToModelConverter boToModelConverter;
    @InjectMocks
    FormatServiceImpl FormatService;

    @BeforeEach
    public void setup() {
        formatBO = FormatBO.builder().id(1L).name("Format1").build();
        format = Format.builder().id(1L).name("Format1").build();
    }

    @Test
    @DisplayName("addFormat operation : correct case")
    public void givenFormatBO_whenAddFormat_thenReturnFormatBO() throws BusinessException, DataException {

        BDDMockito.given(boToModelConverter.formatBOToModel(formatBO)).willReturn(format);
        BDDMockito.given(modelToBOConverter.formatModelToBO(format)).willReturn(formatBO);
        BDDMockito.given(FormatDAO.findByName(formatBO.getName())).willThrow(EntityNotFoundException.class);
        BDDMockito.given(FormatDAO.save(format)).willReturn(format);

        FormatBO savedFormat = FormatService.addFormat(formatBO);

        assertThat(savedFormat).isNotNull();
        assertThat(savedFormat).isEqualTo(formatBO);
    }

    @Test
    @DisplayName("addFormat operation : incorrect case -> Duplicated name")
    public void givenFormatBO_whenAddFormat_thenThrowDuplicatedNameException() throws DataException {

        BDDMockito.given(FormatDAO.findByName(formatBO.getName())).willReturn(format);

        assertThrows(DuplicatedNameException.class, () -> FormatService.addFormat(formatBO));
    }

    @Test
    @DisplayName("addFormat operation : incorrect case -> Format not saved")
    public void givenFormatBO_whenAddFormat_thenThrowBusinessException() throws DataException {

        BDDMockito.given(boToModelConverter.formatBOToModel(formatBO)).willReturn(format);
        BDDMockito.given(FormatDAO.findByName(formatBO.getName())).willThrow(EntityNotFoundException.class);
        BDDMockito.given(FormatDAO.save(format)).willThrow(DataException.class);

        assertThrows(BusinessException.class, () -> FormatService.addFormat(formatBO));

    }

    @Test
    @DisplayName("getFormatById operation : correct case")
    public void givenId_whenGetFormatById_thenReturnFormatBO() throws DataException, BusinessException {

        BDDMockito.given(modelToBOConverter.formatModelToBO(format)).willReturn(formatBO);
        BDDMockito.given(FormatDAO.findById(1L)).willReturn(format);

        FormatBO foundFormat = FormatService.getFormatById(1L);

        assertThat(foundFormat).isNotNull();
        assertThat(foundFormat).isEqualTo(formatBO);
    }

    @Test
    @DisplayName("getFormatById operation : incorrect case -> Not found")
    public void givenId_whenGetFormatById_thenThrowBusinessException() throws DataException {

        BDDMockito.given(FormatDAO.findById(1L)).willThrow(EntityNotFoundException.class);

        assertThrows(FormatNotFoundException.class, () -> FormatService.getFormatById(1L));

    }

    @Test
    @DisplayName("getFormatByName operation : correct case")
    public void givenFormatName_whenGetFormatByName_thenReturnFormatBO() throws DataException, BusinessException {

        BDDMockito.given(modelToBOConverter.formatModelToBO(format)).willReturn(formatBO);
        BDDMockito.given(FormatDAO.findByName("Format1")).willReturn(format);

        FormatBO foundFormat = FormatService.getFormatByName("Format1");

        assertThat(foundFormat).isNotNull();
        assertThat(foundFormat).isEqualTo(formatBO);
    }

    @Test
    @DisplayName("getFormatByName operation : incorrect case -> Not found")
    public void givenFormatName_whenGetFormatByName_thenThrowBusinessException() throws DataException {

        BDDMockito.given(FormatDAO.findByName("Format1")).willThrow(EntityNotFoundException.class);

        assertThrows(FormatNotFoundException.class, () -> FormatService.getFormatByName("Format1"));
    }

    @Test
    @DisplayName("getAllFormats operation : correct case")
    public void givenNumberPlayers_whenGetAllFormats_thenReturnListFormatBO() throws DataException, BusinessException {

        List<Format> formats = new ArrayList<Format>();
        formats.add(format);
        BDDMockito.given(modelToBOConverter.formatModelToBO(format)).willReturn(formatBO);
        BDDMockito.given(FormatDAO.findAll()).willReturn(formats);

        List<FormatBO> formatsBO = FormatService.getAllFormats();

        assertThat(formatsBO).isNotNull();
        assertThat(formatsBO.getFirst()).isEqualTo(formatBO);
    }

    @Test
    @DisplayName("getAllFormatss operation : incorrect case -> not found")
    public void givenNothing_whenGetAllFormats_thenThrowBusinessException() throws DataException {

        BDDMockito.given(FormatDAO.findAll()).willThrow(EntityNotFoundException.class);

        assertThrows(FormatNotFoundException.class, () -> FormatService.getAllFormats());

    }

    @Test
    @DisplayName("deleteFormat operation : correct case")
    public void givenId_whenDeleteFormat_thenDeleteFormat() throws BusinessException, DataException {

        BDDMockito.willDoNothing().given(FormatDAO).delete(1L);

        FormatService.deleteFormat(1L);

        verify(FormatDAO, times(1)).delete(1L);

    }

}
