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
import com.Dual2024.ProjectCompetition.Business.BusinessObject.BOToModelConverter;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.ModalityBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.ModelToBOConverter;
import com.Dual2024.ProjectCompetition.Business.Service.ModalityServiceImpl;
import com.Dual2024.ProjectCompetition.DataAccess.DAO.ModalityDAO;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
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
	@DisplayName("JUnit test for addModality operation : correct case")
	public void givenModalityBO_whenAddModality_thenReturnModalityBO() {

		BDDMockito.given(boToModelConverter.modalityBOToModel(modalityBO)).willReturn(modality);
		BDDMockito.given(modelToBOConverter.modalityModelToBO(modality)).willReturn(modalityBO);
		try {
			BDDMockito.given(modalityDAO.findByName(modalityBO.getName())).willThrow(DataException.class);
		} catch (DataException e) {
			e.printStackTrace();
		}
		try {
			BDDMockito.given(modalityDAO.save(modality)).willReturn(modality);
		} catch (DataException e) {
			e.printStackTrace();
		}

		ModalityBO savedModality = null;
		try {
			savedModality = modalityService.addModality(modalityBO);
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		assertThat(savedModality).isNotNull();
		assertThat(savedModality).isEqualTo(modalityBO);
	}

	@Test
	@DisplayName("JUnit test for addModality operation : incorrect case -> Duplicated name")
	public void givenModalityBO_whenAddModality_thenThrowDuplicatedNameException() {

		try {
			BDDMockito.given(modalityDAO.findByName(modalityBO.getName())).willReturn(modality);
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThrows(DuplicatedNameException.class, () -> modalityService.addModality(modalityBO));
	}

	@Test
	@DisplayName("JUnit test for addModality operation : incorrect case -> Modality not saved")
	public void givenModalityBO_whenAddModality_thenThrowBusinessException() {

		BDDMockito.given(boToModelConverter.modalityBOToModel(modalityBO)).willReturn(modality);
		try {
			BDDMockito.given(modalityDAO.findByName(modalityBO.getName())).willThrow(DataException.class);
		} catch (DataException e) {
			e.printStackTrace();
		}
		try {
			BDDMockito.given(modalityDAO.save(modality)).willThrow(DataException.class);
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThrows(BusinessException.class, () -> modalityService.addModality(modalityBO));

	}

	@Test
	@DisplayName("JUnit test for getModalityById operation : correct case")
	public void givenId_whenGetModalityById_thenReturnModalityBO() {

		BDDMockito.given(modelToBOConverter.modalityModelToBO(modality)).willReturn(modalityBO);
		try {
			BDDMockito.given(modalityDAO.findById(1L)).willReturn(modality);
		} catch (DataException e) {
			e.printStackTrace();
		}

		ModalityBO foundModality = null;
		try {
			foundModality = modalityService.getModalityById(1L);
		} catch (BusinessException e) {
			e.printStackTrace();

		}

		assertThat(foundModality).isNotNull();
		assertThat(foundModality).isEqualTo(modalityBO);
	}

	@Test
	@DisplayName("JUnit test for getModalityById operation : incorrect case -> Not found")
	public void givenId_whenGetModalityById_thenThrowBusinessException() {

		try {
			BDDMockito.given(modalityDAO.findById(1L)).willThrow(DataException.class);
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThrows(BusinessException.class, () -> modalityService.getModalityById(1L));

	}

	@Test
	@DisplayName("JUnit test for getModalityByName operation : correct case")
	public void givenModalityName_whenGetModalityByName_thenReturnModalityBO() {

		BDDMockito.given(modelToBOConverter.modalityModelToBO(modality)).willReturn(modalityBO);
		try {
			BDDMockito.given(modalityDAO.findByName("modality1")).willReturn(modality);
		} catch (DataException e) {
			e.printStackTrace();
		}

		ModalityBO foundModality = null;
		try {
			foundModality = modalityService.getModalityByName("modality1");
		} catch (BusinessException e) {
			e.printStackTrace();

		}

		assertThat(foundModality).isNotNull();
		assertThat(foundModality).isEqualTo(modalityBO);
	}

	@Test
	@DisplayName("JUnit test for getModalityByName operation : incorrect case -> Not found")
	public void givenModalityName_whenGetModalityByName_thenThrowBusinessException() {

		try {
			BDDMockito.given(modalityDAO.findByName("modality1")).willThrow(DataException.class);
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThrows(BusinessException.class, () -> modalityService.getModalityByName("modality1"));
	}

	@Test
	@DisplayName("JUnit test for getModalityByNumberPlayers operation : correct case")
	public void givenNumberPlayers_whenGetModalityByNumberPlayers_thenReturnListModalityBO() {

		List<Modality> modalities = new ArrayList<Modality>();
		modalities.add(modality);
		BDDMockito.given(modelToBOConverter.modalityModelToBO(modality)).willReturn(modalityBO);
		try {
			BDDMockito.given(modalityDAO.findByNumberPlayers(2)).willReturn(modalities);
		} catch (DataException e) {
			e.printStackTrace();
		}
		List<ModalityBO> modalitiesBO = null;
		try {
			modalitiesBO = modalityService.getModalityByNumberPlayers(2);
		} catch (BusinessException e) {
			e.printStackTrace();

		}

		assertThat(modalitiesBO).isNotNull();
		assertThat(modalitiesBO.getFirst()).isEqualTo(modalityBO);
	}

	@Test
	@DisplayName("JUnit test for getModalityByNumberPlayers operation : incorrect case -> Exception")
	public void givenNumberPlayers_whenGetModalityByNumberPlayers_thenThrowBusinessException() {

		try {
			BDDMockito.given(modalityDAO.findByNumberPlayers(2)).willThrow(DataException.class);
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThrows(BusinessException.class, () -> modalityService.getModalityByNumberPlayers(2));

	}

	@Test
	@DisplayName("JUnit test for getAllModalitiess operation : correct case")
	public void givenNumberPlayers_whenGetAllModalities_thenReturnListModalityBO() {

		List<Modality> modalities = new ArrayList<Modality>();
		modalities.add(modality);
		BDDMockito.given(modelToBOConverter.modalityModelToBO(modality)).willReturn(modalityBO);
		try {
			BDDMockito.given(modalityDAO.findAll()).willReturn(modalities);
		} catch (DataException e) {
			e.printStackTrace();
		}
		List<ModalityBO> modalitiesBO = null;
		try {
			modalitiesBO = modalityService.getAllModalities();
		} catch (BusinessException e) {
			e.printStackTrace();

		}

		assertThat(modalitiesBO).isNotNull();
		assertThat(modalitiesBO.getFirst()).isEqualTo(modalityBO);
	}

	@Test
	@DisplayName("JUnit test for getAllModalities operation : incorrect case -> Exception")
	public void givenNothing_whenGetAllModalities_thenThrowBusinessException() {

		try {
			BDDMockito.given(modalityDAO.findAll()).willThrow(DataException.class);
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThrows(BusinessException.class, () -> modalityService.getAllModalities());

	}

	@Test
	@DisplayName("JUnit test for deleteModality operation : correct case")
	public void givenModalityBO_whenDeleteModality_thenDeleteModality() {

		try {
			BDDMockito.given(modalityDAO.findById(1L)).willReturn(modality);
		} catch (DataException e) {
			e.printStackTrace();
		}
		BDDMockito.given(boToModelConverter.modalityBOToModel(modalityBO)).willReturn(modality);
		BDDMockito.given(modelToBOConverter.modalityModelToBO(modality)).willReturn(modalityBO);
		try {
			BDDMockito.willDoNothing().given(modalityDAO).delete(modality);
		} catch (DataException e) {
			e.printStackTrace();
		}

		try {
			modalityService.deleteModality(modalityBO);
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		try {
			verify(modalityDAO, times(1)).delete(modality);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	@Test
	@DisplayName("JUnit test for deleteUser operation : incorrect case -> notFound")
	public void givenUserBOThatNotExists_whenDeleteUser_thenThrowBusinessException() {

		try {
			BDDMockito.given(modalityDAO.findById(1L)).willThrow(DataException.class);
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThrows(BusinessException.class, () -> modalityService.deleteModality(modalityBO));

	}
}
