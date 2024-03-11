package com.dual2024.projectcompetition.Service;

import com.dual2024.projectcompetition.dataaccess.dao.ModalityDAO;
import com.dual2024.projectcompetition.dataaccess.dataexception.DataException;
import com.dual2024.projectcompetition.dataaccess.dataexception.EntityNotFoundException;
import com.dual2024.projectcompetition.dataaccess.model.Modality;
import com.dual2024.projectcompetition.business.businessexception.BusinessException;
import com.dual2024.projectcompetition.business.businessexception.DuplicatedNameException;
import com.dual2024.projectcompetition.business.businessexception.ModalityNotFoundException;
import com.dual2024.projectcompetition.business.businessobject.ModalityBO;
import com.dual2024.projectcompetition.business.businessobject.converters.BOToModelConverter;
import com.dual2024.projectcompetition.business.businessobject.converters.ModelToBOConverter;
import com.dual2024.projectcompetition.business.service.ModalityServiceImpl;
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
public class ModalityServiceTest {
    private ModalityBO modalityBO;
    private Modality modality;

    @Mock
    ModalityDAO modalityDAO;
    @Mock
    ModelToBOConverter modelToBOConverter;
    @Mock
    BOToModelConverter boToModelConverter;
    @InjectMocks
    ModalityServiceImpl modalityService;

    @BeforeEach
    public void setup() {
        modalityBO = ModalityBO.builder().id(1L).name("modality1").numberPlayers(2).build();
        modality = Modality.builder().id(1L).name("modality1").numberPlayers(2).build();
    }

    @Test
    @DisplayName("addModality operation : correct case")
    public void givenModalityBO_whenAddModality_thenReturnModalityBO() throws DataException, BusinessException {

        BDDMockito.given(boToModelConverter.modalityBOToModel(modalityBO)).willReturn(modality);
        BDDMockito.given(modelToBOConverter.modalityModelToBO(modality)).willReturn(modalityBO);
        BDDMockito.given(modalityDAO.findByName(modalityBO.getName())).willThrow(DataException.class);
        BDDMockito.given(modalityDAO.save(modality)).willReturn(modality);

        ModalityBO savedModality = modalityService.addModality(modalityBO);

        assertThat(savedModality).isNotNull();
        assertThat(savedModality).isEqualTo(modalityBO);
    }

    @Test
    @DisplayName("addModality operation : incorrect case -> Duplicated name")
    public void givenModalityBO_whenAddModality_thenThrowDuplicatedNameException() throws DataException {

        BDDMockito.given(modalityDAO.findByName(modalityBO.getName())).willReturn(modality);

        assertThrows(DuplicatedNameException.class, () -> modalityService.addModality(modalityBO));
    }

    @Test
    @DisplayName("addModality operation : incorrect case -> Modality not saved")
    public void givenModalityBO_whenAddModality_thenThrowBusinessException() throws DataException {

        BDDMockito.given(boToModelConverter.modalityBOToModel(modalityBO)).willReturn(modality);
        BDDMockito.given(modalityDAO.findByName(modalityBO.getName())).willThrow(DataException.class);
        BDDMockito.given(modalityDAO.save(modality)).willThrow(DataException.class);

        assertThrows(BusinessException.class, () -> modalityService.addModality(modalityBO));

    }

    @Test
    @DisplayName("getModalityById operation : correct case")
    public void givenId_whenGetModalityById_thenReturnModalityBO() throws DataException, BusinessException {

        BDDMockito.given(modelToBOConverter.modalityModelToBO(modality)).willReturn(modalityBO);
        BDDMockito.given(modalityDAO.findById(1L)).willReturn(modality);

        ModalityBO foundModality = modalityService.getModalityById(1L);

        assertThat(foundModality).isNotNull();
        assertThat(foundModality).isEqualTo(modalityBO);
    }

    @Test
    @DisplayName("getModalityById operation : incorrect case -> Not found")
    public void givenId_whenGetModalityById_thenThrowBusinessException() throws DataException {

        BDDMockito.given(modalityDAO.findById(1L)).willThrow(EntityNotFoundException.class);

        assertThrows(ModalityNotFoundException.class, () -> modalityService.getModalityById(1L));

    }

    @Test
    @DisplayName("getModalityByName operation : correct case")
    public void givenModalityName_whenGetModalityByName_thenReturnModalityBO() throws DataException, BusinessException {

        BDDMockito.given(modelToBOConverter.modalityModelToBO(modality)).willReturn(modalityBO);
        BDDMockito.given(modalityDAO.findByName("modality1")).willReturn(modality);

        ModalityBO foundModality = modalityService.getModalityByName("modality1");

        assertThat(foundModality).isNotNull();
        assertThat(foundModality).isEqualTo(modalityBO);
    }

    @Test
    @DisplayName("getModalityByName operation : incorrect case -> Not found")
    public void givenModalityName_whenGetModalityByName_thenThrowBusinessException() throws DataException {

        BDDMockito.given(modalityDAO.findByName("modality1")).willThrow(EntityNotFoundException.class);

        assertThrows(ModalityNotFoundException.class, () -> modalityService.getModalityByName("modality1"));
    }

    @Test
    @DisplayName("getModalityByNumberPlayers operation : correct case")
    public void givenNumberPlayers_whenGetModalityByNumberPlayers_thenReturnListModalityBO() throws BusinessException, DataException {

        List<Modality> modalities = new ArrayList<Modality>();
        modalities.add(modality);
        BDDMockito.given(modelToBOConverter.modalityModelToBO(modality)).willReturn(modalityBO);
        BDDMockito.given(modalityDAO.findByNumberPlayers(2)).willReturn(modalities);

        List<ModalityBO> modalitiesBO = modalityService.getModalitiesByNumberPlayers(2);

        assertThat(modalitiesBO).isNotNull();
        assertThat(modalitiesBO.getFirst()).isEqualTo(modalityBO);
    }

    @Test
    @DisplayName("getModalityByNumberPlayers operation : incorrect case -> not found")
    public void givenNumberPlayers_whenGetModalityByNumberPlayers_thenThrowBusinessException() throws DataException {

        BDDMockito.given(modalityDAO.findByNumberPlayers(2)).willThrow(EntityNotFoundException.class);

        assertThrows(ModalityNotFoundException.class, () -> modalityService.getModalitiesByNumberPlayers(2));

    }

    @Test
    @DisplayName("getAllModalitiess operation : correct case")
    public void givenNumberPlayers_whenGetAllModalities_thenReturnListModalityBO() throws DataException, BusinessException {

        List<Modality> modalities = new ArrayList<Modality>();
        modalities.add(modality);
        BDDMockito.given(modelToBOConverter.modalityModelToBO(modality)).willReturn(modalityBO);
        BDDMockito.given(modalityDAO.findAll()).willReturn(modalities);

        List<ModalityBO> modalitiesBO = modalityService.getAllModalities();

        assertThat(modalitiesBO).isNotNull();
        assertThat(modalitiesBO.getFirst()).isEqualTo(modalityBO);
    }

    @Test
    @DisplayName("getAllModalities operation : incorrect case -> not found")
    public void givenNothing_whenGetAllModalities_thenThrowBusinessException() throws DataException {

        BDDMockito.given(modalityDAO.findAll()).willThrow(EntityNotFoundException.class);

        assertThrows(ModalityNotFoundException.class, () -> modalityService.getAllModalities());

    }

    @Test
    @DisplayName("deleteModality operation : correct case")
    public void givenId_whenDeleteModality_thenDeleteModality() throws BusinessException, DataException {

        BDDMockito.willDoNothing().given(modalityDAO).delete(1L);

        modalityService.deleteModality(1L);

        verify(modalityDAO, times(1)).delete(1L);

    }

}
