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
import com.Dual2024.ProjectCompetition.Business.BusinessException.ModalityNotFoundException;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.ModalityBO;
import com.Dual2024.ProjectCompetition.Business.Service.ModalityService;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.BOToDTOConverter;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.DTOToBOConverter;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.ModalityDTO;
import com.Dual2024.ProjectCompetition.Presentation.Exception.NotFoundException;
import com.Dual2024.ProjectCompetition.Presentation.Exception.PresentationException;

import jakarta.validation.Valid;

@RequestMapping("modality")
@RestController
public class ModalityController {
	@Autowired
	BOToDTOConverter boToDTOConverter;
	@Autowired
	DTOToBOConverter dtoToBOConverter;
	@Autowired
	private ModalityService modalityService;

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/all")
	public List<ModalityDTO> getAllModalities() throws PresentationException {
		List<ModalityDTO> listModalityDTO = new ArrayList<ModalityDTO>();
		try {
			modalityService.getAllModalities()
					.forEach((ModalityBO modality) -> listModalityDTO.add(boToDTOConverter.modalityBOToDTO(modality)));
			return listModalityDTO;
		} catch (ModalityNotFoundException e) {
			throw new NotFoundException(e.getMessage(), e);
		} catch (BusinessException e) {
			throw new PresentationException(e.getMessage(), e);
		}
	}

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/{id}")
	public ModalityDTO getModalityById(@PathVariable("id") Long id) throws PresentationException {
		try {
			return boToDTOConverter.modalityBOToDTO(modalityService.getModalityById(id));
		} catch (ModalityNotFoundException e) {
			throw new NotFoundException(e.getMessage(), e);
		} catch (BusinessException e) {
			throw new PresentationException(e.getMessage(), e);
		}

	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/add")
	public ModalityDTO addModality(@RequestBody @Valid ModalityDTO modality) throws PresentationException {

		try {
			return boToDTOConverter
					.modalityBOToDTO(modalityService.addModality(dtoToBOConverter.modalityDTOToBO(modality)));
		} catch (DuplicatedNameException e) {
			throw new PresentationException(e.getMessage());
		} catch (BusinessException e) {
			throw new PresentationException(e.getMessage(), e);
		}

	}

	@ResponseStatus(code = HttpStatus.ACCEPTED)
	@DeleteMapping("/delete")
	public void deleteModality(@RequestParam Long id) {
		try {
			modalityService.deleteModality(id);
		} catch (ModalityNotFoundException e) {
			throw new NotFoundException(e.getMessage(), e);
		} catch (BusinessException e) {
			throw new PresentationException(e.getMessage(), e);
		}
	}

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/name/{name}")
	public ModalityDTO getModalityByName(@PathVariable("name") String name) throws PresentationException {
		try {
			return boToDTOConverter.modalityBOToDTO(modalityService.getModalityByName(name));
		} catch (ModalityNotFoundException e) {
			throw new NotFoundException(e.getMessage(), e);
		} catch (BusinessException e) {
			throw new PresentationException(e.getMessage(), e);
		}

	}

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/players/{players}")
	public List<ModalityDTO> getAllModalities(@PathVariable("players") int players) throws PresentationException {
		List<ModalityDTO> listModalityDTO = new ArrayList<ModalityDTO>();
		try {
			modalityService.getModalitiesByNumberPlayers(players)
					.forEach((ModalityBO modality) -> listModalityDTO.add(boToDTOConverter.modalityBOToDTO(modality)));
			return listModalityDTO;
		} catch (ModalityNotFoundException e) {
			throw new NotFoundException(e.getMessage(), e);
		} catch (BusinessException e) {
			throw new PresentationException(e.getMessage(), e);
		}
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PutMapping("/update")
	public void updateModality(@RequestBody @Valid ModalityDTO modality) {
		try {
			modalityService.updateModality(dtoToBOConverter.modalityDTOToBO(modality));
		} catch (ModalityNotFoundException e) {
			throw new NotFoundException(e.getMessage(), e);
		} catch (BusinessException e) {
			throw new PresentationException(e.getMessage(), e);
		}
	}
}
