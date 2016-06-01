package be.vdab.web;

import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.entities.Filiaal;
import be.vdab.repositories.FiliaalRepository;
import be.vdab.repositories.RepositoriesConfig;
import be.vdab.valueobjects.Adres;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {TestDataSourceConfig.class, RepositoriesConfig.class})
@Transactional
public class FiliaalRepositoryImplTest {
	
	@Autowired
	private FiliaalRepository filiaalRepository;
	
	@Test
	public void create() {
		Filiaal filiaal = new Filiaal("Testnaam", true, BigDecimal.ONE, LocalDate.now(), new Adres("straat", "huisnr", 1000, "gemeente"));
		filiaalRepository.save(filiaal);
		
		// ID wordt ingevuld na het aanmaken in de database
		// -> mag dus niet 0 zijn (autonumber)
		assertNotEquals(0, filiaal.getId());
	}

}
