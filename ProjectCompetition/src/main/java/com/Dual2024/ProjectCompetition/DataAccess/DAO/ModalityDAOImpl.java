package com.Dual2024.ProjectCompetition.DataAccess.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Modality;

import com.Dual2024.ProjectCompetition.DataAccess.Repository.ModalityRepository;

import jakarta.validation.ConstraintViolationException;
@Component
public class ModalityDAOImpl implements ModalityDAO {
	@Autowired
	private ModalityRepository modalityRepository;

	@Override
	public Modality save(Modality modality) throws DataException {
		try {
			return modalityRepository.save(modality);
		} catch (DataIntegrityViolationException dive) {
			throw new DataException("Modality not saved", dive);
		} catch (NestedRuntimeException nre) {
			throw new DataException("Modality not saved", nre);
		} catch (ConstraintViolationException cve) {
			throw new DataException("Modality not saved", cve);
		}

	}

	@Override
	public Optional<Modality> findById(Long id) throws DataException {
		try {
			return modalityRepository.findById(id);
		} catch (NestedRuntimeException nre) {
			throw new DataException("Modality not found", nre);
		}

	}

	@Override
	public List<Modality> findAll() throws DataException {
		try {
			return modalityRepository.findAll();
		} catch (NestedRuntimeException nre) {
			throw new DataException("Modalities not found", nre);
		}

	}

	@Override
	public void delete(Modality modality) throws DataException {
		try {
			modalityRepository.delete(modality);
		} catch (NestedRuntimeException nre) {
			throw new DataException("Modality not deleted", nre);
		}

	}

	@Override
	public Modality findByName(String name) throws DataException {
		try {
			Modality modality = modalityRepository.findByName(name);
			if (modality == null) {
				throw new DataException("Modality not found");
			} else {
				return modality;
			}
		} catch (NestedRuntimeException nre) {
			throw new DataException("Modality not found", nre);
		}
	}

	@Override
	public List<Modality> findByNumberPlayers(int numberPlayers) throws DataException {
		try {
			return modalityRepository.findByNumberPlayers(numberPlayers);
		} catch (NestedRuntimeException nre) {
			throw new DataException("Modalities not found", nre);
		}
	}
}
