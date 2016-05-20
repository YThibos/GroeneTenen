package be.vdab.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.vdab.entities.Filiaal;
import be.vdab.exceptions.FiliaalNietLeegException;
import be.vdab.repositories.FiliaalRepository;

@Service
class FiliaalServiceImpl implements FiliaalService {
	
	private final FiliaalRepository filiaalRepository;

	@Autowired
	FiliaalServiceImpl(FiliaalRepository filiaalRepository) {
		this.filiaalRepository = filiaalRepository;
	}

	@Override
	public void create(Filiaal filiaal) {
		filiaalRepository.create(filiaal);
	}

	@Override
	public Filiaal read(long id) {
		return filiaalRepository.read(id);
	}

	@Override
	public void update(Filiaal filiaal) {
		filiaalRepository.update(filiaal);
	}

	@Override
	public void delete(long id) throws FiliaalNietLeegException {
		if (filiaalRepository.findAantalWerknemers(id) != 0) {
			throw new FiliaalNietLeegException("Filiaal heeft nog werknemers. Deze moeten eerst verwijderd worden bij delete.");
		}
		filiaalRepository.delete(id);
	}

	@Override
	public List<Filiaal> findAll() {
		return filiaalRepository.findAll();
	}

	@Override
	public long findAantalFilialen() {
		return filiaalRepository.findAantalFilialen();
	}
}