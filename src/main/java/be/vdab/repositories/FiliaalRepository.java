package be.vdab.repositories;

import java.util.List;

import be.vdab.entities.Filiaal;

public interface FiliaalRepository {

	void create(Filiaal filiaal);
	Filiaal read(long id);
	void update(Filiaal filiaal);
	void delete(long id);
	List<Filiaal> findAll();
	long findAantalFilialen();
	long findAantalWerknemers(long id);
	
}
