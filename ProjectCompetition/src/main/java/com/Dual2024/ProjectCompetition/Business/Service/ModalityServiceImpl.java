package com.Dual2024.ProjectCompetition.Business.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Dual2024.ProjectCompetition.Business.BusinessException.BusinessException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.DuplicatedNameException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.ModalityNotFoundException;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.BOToModelConverter;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.ModalityBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.ModelToBOConverter;
import com.Dual2024.ProjectCompetition.DataAccess.DAO.ModalityDAO;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.NotFoundException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Modality;

@Service
@Transactional
public class ModalityServiceImpl implements ModalityService {
	@Autowired
	ModalityDAO modalityDAO;

	@Autowired
	BOToModelConverter boToModelConverter;

	@Autowired
	ModelToBOConverter modelToBOConverter;

	@Override
	@Transactional
	public ModalityBO addModality(ModalityBO modalityBO) throws BusinessException {
		try {
			modalityDAO.findByName(modalityBO.getName());
			throw new DuplicatedNameException("This name is already registered");
		} catch (DataException e) {
		}
		try {
			return modelToBOConverter
					.modalityModelToBO(modalityDAO.save(boToModelConverter.modalityBOToModel(modalityBO)));
		} catch (DataException e) {
			throw new BusinessException("Error add modality", e);
		}
	}

	@Override
	public ModalityBO getModalityById(Long id) throws BusinessException {
		try {
			return modelToBOConverter.modalityModelToBO(modalityDAO.findById(id));
		} catch (NotFoundException e) {
			throw new ModalityNotFoundException("Modality not found", e);
		} catch (DataException e) {
			throw new BusinessException("Error when trying to find modality", e);
		}
	}

	@Override
	public List<ModalityBO> getAllModalities() throws BusinessException {
		List<ModalityBO> listModalitiesBO = new ArrayList<ModalityBO>();
		try {
			modalityDAO.findAll().forEach(
					(Modality modality) -> listModalitiesBO.add(modelToBOConverter.modalityModelToBO(modality)));
			return listModalitiesBO;
		} catch (NotFoundException e) {
			throw new ModalityNotFoundException("Modalities not found", e);
		} catch (DataException e) {
			throw new BusinessException("Error when trying to find modalities", e);
		}
	}

	@Override
	public ModalityBO getModalityByName(String name) throws BusinessException {
		try {
			return modelToBOConverter.modalityModelToBO(modalityDAO.findByName(name));
		} catch (NotFoundException e) {
			throw new ModalityNotFoundException("Modality not found", e);
		} catch (DataException e) {
			throw new BusinessException("Error when trying to find modality", e);
		}
	}

	@Override
	public List<ModalityBO> getModalitiesByNumberPlayers(int numberPlayers) throws BusinessException {
		List<ModalityBO> listModalitiesBO = new ArrayList<ModalityBO>();
		try {
			modalityDAO.findByNumberPlayers(numberPlayers).forEach(
					(Modality modality) -> listModalitiesBO.add(modelToBOConverter.modalityModelToBO(modality)));
			return listModalitiesBO;
		} catch (NotFoundException e) {
			throw new ModalityNotFoundException("Modalities not found", e);
		} catch (DataException e) {
			throw new BusinessException("Error when trying to find modalities", e);
		}
	}

	@Override
	@Transactional
	public void deleteModality(Long id) throws BusinessException {
		try {
			modalityDAO.findById(id);
			modalityDAO.delete(id);
		} catch (NotFoundException e) {
			throw new ModalityNotFoundException("Modality not found", e);
		} catch (DataException e) {
			throw new BusinessException("Modality not deleted", e);
		}

	}

	@Override
	@Transactional
	public ModalityBO updateModality(ModalityBO modalityBO) throws BusinessException {
		ModalityBO modality = null;
		try {
			modality = modelToBOConverter.modalityModelToBO(modalityDAO.findById(modalityBO.getId()));
			modality = modalityBO;

		} catch (DataException e) {
			throw new ModalityNotFoundException("This modality not exists", e);
		}
		try {

			return modelToBOConverter
					.modalityModelToBO(modalityDAO.save(boToModelConverter.modalityBOToModel(modality)));
		} catch (DataException e) {
			throw new BusinessException("Modality could not be updated", e);
		}
	}
}
