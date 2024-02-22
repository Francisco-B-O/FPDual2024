package com.Dual2024.ProjectCompetition.Business.Service;

import java.util.List;

import com.Dual2024.ProjectCompetition.Business.BusinessException.BusinessException;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.FormatBO;

public interface FormatService {
	FormatBO addFormat(FormatBO formatBO) throws BusinessException;

	FormatBO getFormatById(Long id) throws BusinessException;

	List<FormatBO> getAllFormats() throws BusinessException;

	void deleteFormat(FormatBO formatBO) throws BusinessException;

	FormatBO getFormatByName(String name) throws BusinessException;

	FormatBO updateFormat(FormatBO formatBO) throws BusinessException;
}
