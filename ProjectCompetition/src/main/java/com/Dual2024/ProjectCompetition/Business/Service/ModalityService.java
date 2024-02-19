package com.Dual2024.ProjectCompetition.Business.Service;

import java.util.List;

import com.Dual2024.ProjectCompetition.Business.BusinessException.BusinessException;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.ModalityBO;

public interface ModalityService {
	ModalityBO addModality(ModalityBO modalityBO) throws BusinessException;

	ModalityBO getModalityById(Long id) throws BusinessException;

	List<ModalityBO> getAllModalities() throws BusinessException;

	ModalityBO getModalityByName(String name) throws BusinessException;

	void deleteModality(ModalityBO modalityBO) throws BusinessException;

	List<ModalityBO> getModalityByNumberPlayers(int numberPlayers) throws BusinessException;
}
