package com.dual2024.projectcompetition.business.service;

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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the FormatService interface.
 *
 * @author Francisco Balonero Olivera
 * @see FormatService
 * @see FormatDAO
 * @see BOToModelConverter
 * @see ModelToBOConverter
 */
@Slf4j
@Service
public class FormatServiceImpl implements FormatService {
    /**
     * The Format DAO.
     */
    @Autowired
    FormatDAO formatDAO;

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
    public FormatBO addFormat(FormatBO formatBO) throws BusinessException {
        try {
            formatDAO.findByName(formatBO.getName());
            log.error("This name is already registered: {}", formatBO.getName());
            throw new DuplicatedNameException("This name is already registered");
        } catch (DataException ignored) {
        }
        try {
            Format savedFormat = formatDAO.save(boToModelConverter.formatBOToModel(formatBO));
            FormatBO savedFormatBO = modelToBOConverter.formatModelToBO(savedFormat);
            log.info("Format added successfully with id: {}", savedFormatBO.getId());
            return savedFormatBO;
        } catch (DataException e) {
            log.error("Error adding format", e);
            throw new BusinessException("Error add format", e);
        }
    }

    @Override
    public FormatBO getFormatById(Long id) throws BusinessException {
        try {
            FormatBO formatBO = modelToBOConverter.formatModelToBO(formatDAO.findById(id));
            log.info("Format found by id: {}", id);
            return formatBO;
        } catch (EntityNotFoundException e) {
            log.error("Format not found by id: {}", id, e);
            throw new FormatNotFoundException("Format not found", e);
        } catch (DataException e) {
            log.error("Error finding format by id: {}", id, e);
            throw new BusinessException("Error when trying to find format", e);
        }
    }

    @Override
    public List<FormatBO> getAllFormats() throws BusinessException {
        List<FormatBO> listFormatsBO = new ArrayList<>();
        try {
            formatDAO.findAll().forEach(format -> {
                FormatBO formatBO = modelToBOConverter.formatModelToBO(format);
                listFormatsBO.add(formatBO);
            });
            log.info("Found {} formats", listFormatsBO.size());
            return listFormatsBO;
        } catch (EntityNotFoundException e) {
            log.warn("No formats found", e);
            throw new FormatNotFoundException("Formats not found", e);
        } catch (DataException e) {
            log.error("Error finding all formats", e);
            throw new BusinessException("Error when trying to find formats", e);
        }
    }

    @Override
    public FormatBO getFormatByName(String name) throws BusinessException {
        try {
            FormatBO formatBO = modelToBOConverter.formatModelToBO(formatDAO.findByName(name));
            log.info("Format found by name: {}", name);
            return formatBO;
        } catch (EntityNotFoundException e) {
            log.error("Format not found by name: {}", name, e);
            throw new FormatNotFoundException("Format not found", e);
        } catch (DataException e) {
            log.error("Error finding format by name: {}", name, e);
            throw new BusinessException("Error when trying to find format", e);
        }
    }

    @Override
    @Transactional
    public void deleteFormat(Long id) throws BusinessException {
        try {
            formatDAO.findById(id);
            formatDAO.delete(id);
            log.info("Format deleted successfully with id: {}", id);
        } catch (EntityNotFoundException e) {
            log.error("Format not found by id: {}", id, e);
            throw new FormatNotFoundException("Format not found", e);
        } catch (DataException e) {
            log.error("Error deleting format with id: {}", id, e);
            throw new FormatNotFoundException("Format not deleted", e);
        }
    }

    @Override
    @Transactional
    public FormatBO updateFormat(FormatBO formatBO) throws BusinessException {

        try {
            formatDAO.findById(formatBO.getId());
            log.info("Format found by id: {}", formatBO.getId());
        } catch (DataException e) {
            log.error("Format not found by id: {}", formatBO.getId(), e);
            throw new FormatNotFoundException("This format not exists", e);
        }
        try {
            Format savedFormat = formatDAO.save(boToModelConverter.formatBOToModel(formatBO));
            FormatBO savedFormatBO = modelToBOConverter.formatModelToBO(savedFormat);
            log.info("Format updated successfully with id: {}", savedFormatBO.getId());
            return savedFormatBO;
        } catch (DataException e) {
            log.error("Error updating format", e);
            throw new BusinessException("Format could not be updated", e);
        }
    }
}
