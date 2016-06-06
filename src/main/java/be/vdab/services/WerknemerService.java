package be.vdab.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import be.vdab.entities.Werknemer;

public interface WerknemerService {
	
	public List<Werknemer> findAll();
	public Page<Werknemer> findAll(Pageable pageable);
	

}
