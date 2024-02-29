package com.Dual2024.ProjectCompetition.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.Dual2024.ProjectCompetition.Business.BusinessException.BusinessException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.DuplicatedNameException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.ModalityNotFoundException;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.BOToModelConverter;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.ModalityBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.ModelToBOConverter;
import com.Dual2024.ProjectCompetition.Business.Service.ModalityServiceImpl;
import com.Dual2024.ProjectCompetition.DataAccess.DAO.ModalityDAO;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.NotFoundException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Modality;

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
	public void givenModalityBO_whenAddModality_thenReturnModalityBO() {

		BDDMockito.given(boToModelConverter.modalityBOToModel(modalityBO)).willReturn(modality);
		BDDMockito.given(modelToBOConverter.modalityModelToBO(modality)).willReturn(modalityBO);
		try {
			BDDMockito.given(modalityDAO.findByName(modalityBO.getName())).willThrow(DataException.class);
		} catch (DataException e) {
		}
		try {
			BDDMockito.given(modalityDAO.save(modality)).willReturn(modality);
		} catch (DataException e) {
		}

		ModalityBO savedModality = null;
		try {
			savedModality = modalityService.addModality(modalityBO);
		} catch (BusinessException e) {
		}

		assertThat(savedModality).isNotNull();
		assertThat(savedModality).isEqualTo(modalityBO);
	}

	@Test
	@DisplayName("addModality operation : incorrect case -> Duplicated name")
	public void givenModalityBO_whenAddModality_thenThrowDuplicatedNameException() {

		try {
			BDDMockito.given(modalityDAO.findByName(modalityBO.getName())).willReturn(modality);
		} catch (DataException e) {
		}

		assertThrows(DuplicatedNameException.class, () -> modalityService.addModality(modalityBO));
	}

	@Test
	@DisplayName("addModality operation : incorrect case -> Modality not saved")
	public void givenModalityBO_whenAddModality_thenThrowBusinessException() {

		BDDMockito.given(boToModelConverter.modalityBOToModel(modalityBO)).willReturn(modality);
		try {
			BDDMockito.given(modalityDAO.findByName(modalityBO.getName())).willThrow(DataException.class);
		} catch (DataException e) {
		}
		try {
			BDDMockito.given(modalityDAO.save(modality)).willThrow(DataException.class);
		} catch (DataException e) {
		}

		assertThrows(BusinessException.class, () -> modalityService.addModality(modalityBO));

	}

	@Test
	@DisplayName("getModalityById operation : correct case")
	public void givenId_whenGetModalityById_thenReturnModalityBO() {

		BDDMockito.given(modelToBOConverter.modalityModelToBO(modality)).willReturn(modalityBO);
		try {
			BDDMockito.given(modalityDAO.findById(1L)).willReturn(modality);
		} catch (DataException e) {
		}

		ModalityBO foundModality = null;
		try {
			foundModality = modalityService.getModalityById(1L);
		} catch (BusinessException e) {
		}

		assertThat(foundModality).isNotNull();
		assertThat(foundModality).isEqualTo(modalityBO);
	}

	@Test
	@DisplayName("getModalityById operation : incorrect case -> Not found")
	public void givenId_whenGetModalityById_thenThrowBusinessException() {

		try {
			BDDMockito.given(modalityDAO.findById(1L)).willThrow(NotFoundException.class);
		} catch (DataException e) {
		}

		assertThrows(ModalityNotFoundException.class, () -> modalityService.getModalityById(1L));

	}

	@Test
	@DisplayName("getModalityByName operation : correct case")
	public void givenModalityName_whenGetModalityByName_thenReturnModalityBO() {

		BDDMockito.given(modelToBOConverter.modalityModelToBO(modality)).willReturn(modalityBO);
		try {
			BDDMockito.given(modalityDAO.findByName("modality1")).willReturn(modality);
		} catch (DataException e) {
		}

		ModalityBO foundModality = null;
		try {
			foundModality = modalityService.getModalityByName("modality1");
		} catch (BusinessException e) {
		}

		assertThat(foundModality).isNotNull();
		assertThat(foundModality).isEqualTo(modalityBO);
	}

	@Test
	@DisplayName("getModalityByName operation : incorrect case -> Not found")
	public void givenModalityName_whenGetModalityByName_thenThrowBusinessException() {

		try {
			BDDMockito.given(modalityDAO.findByName("modality1")).willThrow(NotFoundException.class);
		} catch (DataException e) {
		}

		assertThrows(ModalityNotFoundException.class, () -> modalityService.getModalityByName("modality1"));
	}

	@Test
	@DisplayName("getModalityByNumberPlayers operation : correct case")
	public void givenNumberPlayers_whenGetModalityByNumberPlayers_thenReturnListModalityBO() {

		List<Modality> modalities = new ArrayList<Modality>();
		modalities.add(modality);
		BDDMockito.given(modelToBOConverter.modalityModelToBO(modality)).willReturn(modalityBO);
		try {
			BDDMockito.given(modalityDAO.findByNumberPlayers(2)).willReturn(modalities);
		} catch (DataException e) {
		}

		List<ModalityBO> modalitiesBO = null;
		try {
			modalitiesBO = modalityService.getModalitiesByNumberPlayers(2);
		} catch (BusinessException e) {
		}

		assertThat(modalitiesBO).isNotNull();
		assertThat(modalitiesBO.getFirst()).isEqualTo(modalityBO);
	}

	@Test
	@DisplayName("getModalityByNumberPlayers operation : incorrect case -> not found")
	public void givenNumberPlayers_whenGetModalityByNumberPlayers_thenThrowBusinessException() {

		try {
			BDDMockito.given(modalityDAO.findByNumberPlayers(2)).willThrow(NotFoundException.class);
		} catch (DataException e) {
		}

		assertThrows(ModalityNotFoundException.class, () -> modalityService.getModalitiesByNumberPlayers(2));

	}

	@Test
	@DisplayName("getAllModalitiess operation : correct case")
	public void givenNumberPlayers_whenGetAllModalities_thenReturnListModalityBO() {

		List<Modality> modalities = new ArrayList<Modality>();
		modalities.add(modality);
		BDDMockito.given(modelToBOConverter.modalityModelToBO(modality)).willReturn(modalityBO);
		try {
			BDDMockito.given(modalityDAO.findAll()).willReturn(modalities);
		} catch (DataException e) {
		}

		List<ModalityBO> modalitiesBO = null;
		try {
			modalitiesBO = modalityService.getAllModalities();
		} catch (BusinessException e) {
		}

		assertThat(modalitiesBO).isNotNull();
		assertThat(modalitiesBO.getFirst()).isEqualTo(modalityBO);
	}

	@Test
	@DisplayName("getAllModalities operation : incorrect case -> not found")
	public void givenNothing_whenGetAllModalities_thenThrowBusinessException() {

		try {
			BDDMockito.given(modalityDAO.findAll()).willThrow(NotFoundException.class);
		} catch (DataException e) {
		}

		assertThrows(ModalityNotFoundException.class, () -> modalityService.getAllModalities());

	}

	@Test
	@DisplayName("deleteModality operation : correct case")
	public void givenId_whenDeleteModality_thenDeleteModality() {

		try {
			BDDMockito.willDoNothing().given(modalityDAO).delete(1L);
		} catch (DataException e) {
		}

		try {
			modalityService.deleteModality(1L);
		} catch (BusinessException e) {
		}

		try {
			verify(modalityDAO, times(1)).delete(1L);
		} catch (DataException e) {
		}
	}

}
