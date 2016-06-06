package be.vdab.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import be.vdab.entities.Werknemer;
import be.vdab.repositories.WerknemerRepository;

@ReadOnlyTransactionalService
class WerknemerServiceImpl implements WerknemerService {
	
	private final WerknemerRepository werknemerRepository;
	
	@Autowired
	WerknemerServiceImpl(WerknemerRepository werknemerRepository) {
		this.werknemerRepository = werknemerRepository;
	}

	@Override
	public List<Werknemer> findAll() {
		return werknemerRepository.findAll(new Sort("familienaam", "voornaam"));
	}

	@Override
	public Page<Werknemer> findAll(Pageable pageable) {
		return werknemerRepository.findAll(pageable);
	}
	
}
