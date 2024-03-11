package com.Dual2024.ProjectCompetition.Business.Service;

import com.Dual2024.ProjectCompetition.Business.BusinessException.BusinessException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.DuplicatedNameException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.ModalityNotFoundException;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.Converters.BOToModelConverter;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.Converters.ModelToBOConverter;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.ModalityBO;
import com.Dual2024.ProjectCompetition.DataAccess.DAO.ModalityDAO;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.EntityNotFoundException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Modality;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the ModalityService interface.
 *
 * @author Francisco Balonero Olivera
 * @see ModalityService
 * @see ModalityDAO
 * @see BOToModelConverter
 * @see ModelToBOConverter
 */
@Slf4j
@Service
@Transactional
public class ModalityServiceImpl implements ModalityService {
    /**
     * The Modality DAO.
     */
    @Autowired
    ModalityDAO modalityDAO;

    /**
     * The BO to model converter.
     */
    @Autowired
    BOToModelConverter boToModelConverter;

    /**
     * The Model to BO converter.
     */
    @Autowired
    ModelToBOConverter modelToBOConverter;

    @Override
    @Transactional
    public ModalityBO addModality(ModalityBO modalityBO) throws BusinessException {
        try {
            modalityDAO.findByName(modalityBO.getName());
            log.error("This name is already registered: {}", modalityBO.getName());
            throw new DuplicatedNameException("This name is already registered");
        } catch (DataException ignored) {
        }
        try {
            return modelToBOConverter
                    .modalityModelToBO(modalityDAO.save(boToModelConverter.modalityBOToModel(modalityBO)));
        } catch (DataException e) {
            log.error("Error add modality", e);
            throw new BusinessException("Error add modality", e);
        }
    }

    @Override
    public ModalityBO getModalityById(Long id) throws BusinessException {
        try {
            return modelToBOConverter.modalityModelToBO(modalityDAO.findById(id));
        } catch (EntityNotFoundException e) {
            log.error("Modality not found", e);
            throw new ModalityNotFoundException("Modality not found", e);
        } catch (DataException e) {
            log.error("Error when trying to find modality", e);
            throw new BusinessException("Error when trying to find modality", e);
        }
    }

    @Override
    public List<ModalityBO> getAllModalities() throws BusinessException {
        List<ModalityBO> listModalitiesBO = new ArrayList<>();
        try {
            modalityDAO.findAll().forEach(
                    (Modality modality) -> listModalitiesBO.add(modelToBOConverter.modalityModelToBO(modality)));
            return listModalitiesBO;
        } catch (EntityNotFoundException e) {
            log.error("Modalities not found", e);
            throw new ModalityNotFoundException("Modalities not found", e);
        } catch (DataException e) {
            log.error("Error when trying to find modalities", e);
            throw new BusinessException("Error when trying to find modalities", e);
        }
    }

    @Override
    public ModalityBO getModalityByName(String name) throws BusinessException {
        try {
            return modelToBOConverter.modalityModelToBO(modalityDAO.findByName(name));
        } catch (EntityNotFoundException e) {
            log.error("Modality not found", e);
            throw new ModalityNotFoundException("Modality not found", e);
        } catch (DataException e) {
            log.error("Error when trying to find modality", e);
            throw new BusinessException("Error when trying to find modality", e);
        }
    }

    @Override
    public List<ModalityBO> getModalitiesByNumberPlayers(int numberPlayers) throws BusinessException {
        List<ModalityBO> listModalitiesBO = new ArrayList<>();
        try {
            modalityDAO.findByNumberPlayers(numberPlayers).forEach(
                    (Modality modality) -> listModalitiesBO.add(modelToBOConverter.modalityModelToBO(modality)));
            return listModalitiesBO;
        } catch (EntityNotFoundException e) {
            log.error("Modalities not found", e);
            throw new ModalityNotFoundException("Modalities not found", e);
        } catch (DataException e) {
            log.error("Error when trying to find modalities", e);
            throw new BusinessException("Error when trying to find modalities", e);
        }
    }

    @Override
    @Transactional
    public void deleteModality(Long id) throws BusinessException {
        try {
            modalityDAO.findById(id);
            modalityDAO.delete(id);
        } catch (EntityNotFoundException e) {
            log.error("Modality not found", e);
            throw new ModalityNotFoundException("Modality not found", e);
        } catch (DataException e) {
            log.error("Modality not deleted", e);
            throw new BusinessException("Modality not deleted", e);
        }
    }

    @Override
    @Transactional
    public ModalityBO updateModality(ModalityBO modalityBO) throws BusinessException {
        ModalityBO modality;
        try {
            modalityDAO.findById(modalityBO.getId());
            modality = modalityBO;
        } catch (DataException e) {
            log.error("This modality not exists", e);
            throw new ModalityNotFoundException("This modality not exists", e);
        }
        try {
            return modelToBOConverter
                    .modalityModelToBO(modalityDAO.save(boToModelConverter.modalityBOToModel(modality)));
        } catch (DataException e) {
            log.error("Modality could not be updated", e);
            throw new BusinessException("Modality could not be updated", e);
        }
    }
}
