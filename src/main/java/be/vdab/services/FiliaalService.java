package be.vdab.services;

import java.util.List;

import be.vdab.entities.Filiaal;
import be.vdab.exceptions.FiliaalNietLeegException;
import be.vdab.valueobjects.PostcodeReeks;

public interface FiliaalService {

	void create(Filiaal filiaal);
	Filiaal read(long id);
	void update(Filiaal filiaal);
	/**
	 * Attempt to delete a filiaal with given ID from the repository.
	 * 
	 * @throws FiliaalNietLeegException	When the Filiaal still has Werknemers, an exception is thrown.
	 * Remove all werknemers before deleting the filiaal.
	 */
	void delete(long id) throws FiliaalNietLeegException;
	List<Filiaal> findAll();
	long findAantalFilialen();
	List<Filiaal> findByPostcodeReeks(PostcodeReeks reeks);
	List<Filiaal> findNietAfgeschreven();
	void afschrijven(Iterable<Filiaal> filialen);
	
}
