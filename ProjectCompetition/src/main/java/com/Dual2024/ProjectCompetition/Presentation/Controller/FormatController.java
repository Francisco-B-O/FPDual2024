package com.Dual2024.ProjectCompetition.Presentation.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.Dual2024.ProjectCompetition.Business.BusinessException.BusinessException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.DuplicatedNameException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.FormatNotFoundException;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.FormatBO;
import com.Dual2024.ProjectCompetition.Business.Service.FormatService;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.BOToDTOConverter;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.DTOToBOConverter;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.FormatDTO;
import com.Dual2024.ProjectCompetition.Presentation.Exception.NotFoundException;
import com.Dual2024.ProjectCompetition.Presentation.Exception.PresentationException;

import jakarta.validation.Valid;

@RequestMapping("format")
@RestController
public class FormatController {
	@Autowired
	BOToDTOConverter boToDTOConverter;
	@Autowired
	DTOToBOConverter dtoToBOConverter;
	@Autowired
	private FormatService formatService;

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/all")
	public List<FormatDTO> getAllFormats() throws PresentationException {
		List<FormatDTO> listFormatDTO = new ArrayList<FormatDTO>();
		try {
			formatService.getAllFormats()
					.forEach((FormatBO format) -> listFormatDTO.add(boToDTOConverter.formatBOToDTO(format)));
			return listFormatDTO;
		} catch (FormatNotFoundException e) {
			throw new NotFoundException(e.getMessage(), e);
		} catch (BusinessException e) {
			throw new PresentationException(e.getMessage(), e);
		}
	}

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/{id}")
	public FormatDTO getFormatById(@PathVariable("id") Long id) throws PresentationException {
		try {
			return boToDTOConverter.formatBOToDTO(formatService.getFormatById(id));
		} catch (FormatNotFoundException e) {
			throw new NotFoundException(e.getMessage(), e);
		} catch (BusinessException e) {
			throw new PresentationException(e.getMessage(), e);
		}

	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/add")
	public FormatDTO addformat(@RequestBody @Valid FormatDTO format) throws PresentationException {

		try {
			return boToDTOConverter.formatBOToDTO(formatService.addFormat(dtoToBOConverter.formatDTOToBO(format)));
		} catch (DuplicatedNameException e) {
			throw new PresentationException(e.getMessage());
		} catch (BusinessException e) {
			throw new PresentationException(e.getMessage(), e);
		}

	}

	@ResponseStatus(code = HttpStatus.ACCEPTED)
	@DeleteMapping("/delete")
	public void deleteformat(@RequestParam Long id) {
		try {
			formatService.deleteFormat(id);
		} catch (FormatNotFoundException e) {
			throw new NotFoundException(e.getMessage(), e);
		} catch (BusinessException e) {
			throw new PresentationException(e.getMessage(), e);
		}
	}

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/name/{name}")
	public FormatDTO getformatByName(@PathVariable("name") String name) throws PresentationException {
		try {
			return boToDTOConverter.formatBOToDTO(formatService.getFormatByName(name));
		} catch (FormatNotFoundException e) {
			throw new NotFoundException(e.getMessage(), e);
		} catch (BusinessException e) {
			throw new PresentationException(e.getMessage(), e);
		}

	}

	@ResponseStatus(code = HttpStatus.ACCEPTED)
	@PutMapping("/update")
	public void updateformat(@RequestBody @Valid FormatDTO format) {
		try {
			formatService.updateFormat(dtoToBOConverter.formatDTOToBO(format));
		} catch (FormatNotFoundException e) {
			throw new NotFoundException(e.getMessage(), e);
		} catch (BusinessException e) {
			throw new PresentationException(e.getMessage(), e);
		}
	}
}
