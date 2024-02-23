package com.Dual2024.ProjectCompetition.Business.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Dual2024.ProjectCompetition.Business.BusinessException.BusinessException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.DuplicatedNameException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.FormatNotFoundException;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.BOToModelConverter;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.FormatBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.ModelToBOConverter;
import com.Dual2024.ProjectCompetition.DataAccess.DAO.FormatDAO;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Format;

@Service
@Transactional
public class FormatServiceImpl implements FormatService {
	@Autowired
	FormatDAO formatDAO;

	@Autowired
	BOToModelConverter boToModelConverter;

	@Autowired
	ModelToBOConverter modelToBOConverter;

	@Override
	public FormatBO addFormat(FormatBO formatBO) throws BusinessException {
		try {
			formatDAO.findByName(formatBO.getName());
			throw new DuplicatedNameException("This name is already registered");
		} catch (DataException e) {
		}
		try {
			return modelToBOConverter.formatModelToBO(formatDAO.save(boToModelConverter.formatBOToModel(formatBO)));
		} catch (DataException e) {
			throw new BusinessException("Error add format", e);
		}
	}

	@Override
	public FormatBO getFormatById(Long id) throws BusinessException {
		try {
			return modelToBOConverter.formatModelToBO(formatDAO.findById(id));
		} catch (DataException e) {
			throw new FormatNotFoundException("Format not found", e);
		}
	}

	@Override
	public List<FormatBO> getAllFormats() throws BusinessException {
		List<FormatBO> listFormatsBO = new ArrayList<FormatBO>();
		try {
			formatDAO.findAll()
					.forEach((Format format) -> listFormatsBO.add(modelToBOConverter.formatModelToBO(format)));
			return listFormatsBO;
		} catch (DataException e) {
			throw new FormatNotFoundException("Formats not found", e);
		}
	}

	@Override
	public FormatBO getFormatByName(String name) throws BusinessException {
		try {
			return modelToBOConverter.formatModelToBO(formatDAO.findByName(name));
		} catch (DataException e) {
			throw new FormatNotFoundException("Format not found", e);
		}
	}

	@Override
	public void deleteFormat(FormatBO formatBO) throws BusinessException {
		try {
			modelToBOConverter.formatModelToBO(formatDAO.findById(formatBO.getId()));
			formatDAO.delete(boToModelConverter.formatBOToModel(formatBO));
		} catch (DataException e) {
			throw new FormatNotFoundException("Format not deleted", e);
		}
	}

	@Override
	public FormatBO updateFormat(FormatBO formatBO) throws BusinessException {
		FormatBO format = null;
		try {
			format = modelToBOConverter.formatModelToBO(formatDAO.findById(formatBO.getId()));
			format = formatBO;
		} catch (DataException e) {
			throw new FormatNotFoundException("This format not exists", e);
		}
		try {

			return modelToBOConverter.formatModelToBO(formatDAO.save(boToModelConverter.formatBOToModel(format)));
		} catch (DataException e) {
			throw new BusinessException("format could not be updated", e);
		}
	}

}
