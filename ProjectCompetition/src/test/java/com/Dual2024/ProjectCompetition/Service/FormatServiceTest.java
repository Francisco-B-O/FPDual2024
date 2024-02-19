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
import com.Dual2024.ProjectCompetition.Business.BusinessObject.FormatBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.ModelToBOConverter;
import com.Dual2024.ProjectCompetition.Business.Service.FormatServiceImpl;
import com.Dual2024.ProjectCompetition.DataAccess.DAO.FormatDAO;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Format;

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
	@DisplayName("JUnit test for addFormat operation : correct case")
	public void givenFormatBO_whenAddFormat_thenReturnFormatBO() {

		BDDMockito.given(boToModelConverter.formatBOToModel(formatBO)).willReturn(format);
		BDDMockito.given(modelToBOConverter.formatModelToBO(format)).willReturn(formatBO);
		try {
			BDDMockito.given(FormatDAO.findByName(formatBO.getName())).willThrow(DataException.class);
		} catch (DataException e) {
			e.printStackTrace();
		}
		try {
			BDDMockito.given(FormatDAO.save(format)).willReturn(format);
		} catch (DataException e) {
			e.printStackTrace();
		}

		FormatBO savedFormat = null;
		try {
			savedFormat = FormatService.addFormat(formatBO);
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		assertThat(savedFormat).isNotNull();
		assertThat(savedFormat).isEqualTo(formatBO);
	}

	@Test
	@DisplayName("JUnit test for addFormat operation : incorrect case -> Duplicated name")
	public void givenFormatBO_whenAddFormat_thenThrowDuplicatedNameException() {

		try {
			BDDMockito.given(FormatDAO.findByName(formatBO.getName())).willReturn(format);
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThrows(DuplicatedNameException.class, () -> FormatService.addFormat(formatBO));
	}

	@Test
	@DisplayName("JUnit test for addFormat operation : incorrect case -> Format not saved")
	public void givenFormatBO_whenAddFormat_thenThrowBusinessException() {

		BDDMockito.given(boToModelConverter.formatBOToModel(formatBO)).willReturn(format);
		try {
			BDDMockito.given(FormatDAO.findByName(formatBO.getName())).willThrow(DataException.class);
		} catch (DataException e) {
			e.printStackTrace();
		}
		try {
			BDDMockito.given(FormatDAO.save(format)).willThrow(DataException.class);
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThrows(BusinessException.class, () -> FormatService.addFormat(formatBO));

	}

	@Test
	@DisplayName("JUnit test for getFormatById operation : correct case")
	public void givenId_whenGetFormatById_thenReturnFormatBO() {

		BDDMockito.given(modelToBOConverter.formatModelToBO(format)).willReturn(formatBO);
		try {
			BDDMockito.given(FormatDAO.findById(1L)).willReturn(format);
		} catch (DataException e) {
			e.printStackTrace();
		}

		FormatBO foundFormat = null;
		try {
			foundFormat = FormatService.getFormatById(1L);
		} catch (BusinessException e) {
			e.printStackTrace();

		}

		assertThat(foundFormat).isNotNull();
		assertThat(foundFormat).isEqualTo(formatBO);
	}

	@Test
	@DisplayName("JUnit test for getFormatById operation : incorrect case -> Not found")
	public void givenId_whenGetFormatById_thenThrowBusinessException() {

		try {
			BDDMockito.given(FormatDAO.findById(1L)).willThrow(DataException.class);
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThrows(BusinessException.class, () -> FormatService.getFormatById(1L));

	}

	@Test
	@DisplayName("JUnit test for getFormatByName operation : correct case")
	public void givenFormatName_whenGetFormatByName_thenReturnFormatBO() {

		BDDMockito.given(modelToBOConverter.formatModelToBO(format)).willReturn(formatBO);
		try {
			BDDMockito.given(FormatDAO.findByName("Format1")).willReturn(format);
		} catch (DataException e) {
			e.printStackTrace();
		}

		FormatBO foundFormat = null;
		try {
			foundFormat = FormatService.getFormatByName("Format1");
		} catch (BusinessException e) {
			e.printStackTrace();

		}

		assertThat(foundFormat).isNotNull();
		assertThat(foundFormat).isEqualTo(formatBO);
	}

	@Test
	@DisplayName("JUnit test for getFormatByName operation : incorrect case -> Not found")
	public void givenFormatName_whenGetFormatByName_thenThrowBusinessException() {

		try {
			BDDMockito.given(FormatDAO.findByName("Format1")).willThrow(DataException.class);
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThrows(BusinessException.class, () -> FormatService.getFormatByName("Format1"));
	}

	@Test
	@DisplayName("JUnit test for getAllModalitiess operation : correct case")
	public void givenNumberPlayers_whenGetAllModalities_thenReturnListFormatBO() {

		List<Format> formats = new ArrayList<Format>();
		formats.add(format);
		BDDMockito.given(modelToBOConverter.formatModelToBO(format)).willReturn(formatBO);
		try {
			BDDMockito.given(FormatDAO.findAll()).willReturn(formats);
		} catch (DataException e) {
			e.printStackTrace();
		}
		List<FormatBO> formatsBO = null;
		try {
			formatsBO = FormatService.getAllFormats();
		} catch (BusinessException e) {
			e.printStackTrace();

		}

		assertThat(formatsBO).isNotNull();
		assertThat(formatsBO.getFirst()).isEqualTo(formatBO);
	}

	@Test
	@DisplayName("JUnit test for getAllModalities operation : incorrect case -> Exception")
	public void givenNothing_whenGetAllModalities_thenThrowBusinessException() {

		try {
			BDDMockito.given(FormatDAO.findAll()).willThrow(DataException.class);
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThrows(BusinessException.class, () -> FormatService.getAllFormats());

	}

	@Test
	@DisplayName("JUnit test for deleteFormat operation : correct case")
	public void givenFormatBO_whenDeleteFormat_thenDeleteFormat() {

		try {
			BDDMockito.given(FormatDAO.findById(1L)).willReturn(format);
		} catch (DataException e) {
			e.printStackTrace();
		}
		BDDMockito.given(boToModelConverter.formatBOToModel(formatBO)).willReturn(format);
		BDDMockito.given(modelToBOConverter.formatModelToBO(format)).willReturn(formatBO);
		try {
			BDDMockito.willDoNothing().given(FormatDAO).delete(format);
		} catch (DataException e) {
			e.printStackTrace();
		}

		try {
			FormatService.deleteFormat(formatBO);
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		try {
			verify(FormatDAO, times(1)).delete(format);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	@Test
	@DisplayName("JUnit test for deleteUser operation : incorrect case -> notFound")
	public void givenUserBOThatNotExists_whenDeleteUser_thenThrowBusinessException() {

		try {
			BDDMockito.given(FormatDAO.findById(1L)).willThrow(DataException.class);
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThrows(BusinessException.class, () -> FormatService.deleteFormat(formatBO));

	}
}
