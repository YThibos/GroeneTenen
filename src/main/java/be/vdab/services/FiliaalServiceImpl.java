package be.vdab.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import be.vdab.entities.Filiaal;
import be.vdab.exceptions.FiliaalNietLeegException;
import be.vdab.repositories.FiliaalRepository;
import be.vdab.valueobjects.PostcodeReeks;

@EnableTransactionManagement
@ReadOnlyTransactionalService
class FiliaalServiceImpl implements FiliaalService {
	
	private final FiliaalRepository filiaalRepository;

	@Autowired
	FiliaalServiceImpl(FiliaalRepository filiaalRepository) {
		this.filiaalRepository = filiaalRepository;
	}

	@Override
	@ModifyingTransactionalServiceMethod
	public void create(Filiaal filiaal) {
		filiaalRepository.save(filiaal);
	}

	@Override
	public Filiaal read(long id) {
		return filiaalRepository.findOne(id);
	}

	@Override
	@ModifyingTransactionalServiceMethod
	public void update(Filiaal filiaal) {
		filiaalRepository.save(filiaal);
	}

	@Override
	@ModifyingTransactionalServiceMethod
	public void delete(long id) throws FiliaalNietLeegException {
		Filiaal filiaal = filiaalRepository.findOne(id);
		if (filiaal != null) {
			if (!filiaal.getWerknemers().isEmpty()) {
				throw new FiliaalNietLeegException("Filiaal heeft nog werknemers. Deze moeten eerst verwijderd worden bij delete.");
			}
		}
		filiaalRepository.delete(id);
	}

	@Override
	public List<Filiaal> findAll() {
		return filiaalRepository.findAll();
	}

	@Override
	public long findAantalFilialen() {
		return filiaalRepository.count();
	}

	@Override
	public List<Filiaal> findByPostcodeReeks(PostcodeReeks reeks) {
		return filiaalRepository.findByAdresPostcodeBetweenOrderByNaam(reeks.getVanpostcode(), reeks.getTotpostcode());
		
	}

	@Override
	public List<Filiaal> findNietAfgeschreven() {
		return filiaalRepository.findByWaardeGebouwNot(BigDecimal.ZERO);
	}

	@Override
	@ModifyingTransactionalServiceMethod
	public void afschrijven(Iterable<Filiaal> filialen) {
		for (Filiaal filiaal : filialen) {
			filiaal.afschrijven();
		}
	}
}