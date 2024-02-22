package com.Dual2024.ProjectCompetition.DataAccess.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Format;
import com.Dual2024.ProjectCompetition.DataAccess.Repository.FormatRepository;

import jakarta.validation.ConstraintViolationException;

@Component
public class FormatDAOImpl implements FormatDAO {
	@Autowired
	private FormatRepository formatRepository;

	@Override
	public Format save(Format format) throws DataException {
		try {
			return formatRepository.save(format);
		} catch (DataIntegrityViolationException dive) {
			throw new DataException("Format not saved", dive);
		} catch (NestedRuntimeException nre) {
			throw new DataException("Format not saved", nre);
		} catch (ConstraintViolationException cve) {
			throw new DataException("Format not saved", cve);
		}

	}

	@Override
	public Format findById(Long id) throws DataException {
		try {
			Optional<Format> format = formatRepository.findById(id);
			if (format.isPresent()) {
				return format.get();
			} else {
				throw new DataException("Format not found");
			}

		} catch (NestedRuntimeException nre) {
			throw new DataException("Format not found", nre);
		}

	}

	@Override
	public List<Format> findAll() throws DataException {
		try {
			List<Format> formats = formatRepository.findAll();
			if (formats.isEmpty()) {
				throw new DataException("Formats not found");
			} else {
				return formats;
			}
		} catch (NestedRuntimeException nre) {
			throw new DataException("Formats not found", nre);
		}

	}

	@Override
	public void delete(Format format) throws DataException {
		try {
			if (format.equals(null)) {
				throw new DataException("Format not deleted");
			} else {
				formatRepository.delete(format);
			}
		} catch (NestedRuntimeException nre) {
			throw new DataException("Format not deleted", nre);
		}

	}

	@Override
	public Format findByName(String name) throws DataException {
		try {
			Format format = formatRepository.findByName(name);
			if (format == null) {
				throw new DataException("Format not found");
			} else {
				return format;
			}
		} catch (NestedRuntimeException nre) {
			throw new DataException("Format not found", nre);
		}
	}

}
