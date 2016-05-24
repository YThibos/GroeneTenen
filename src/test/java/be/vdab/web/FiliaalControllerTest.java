package be.vdab.web;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import be.vdab.entities.Filiaal;
import be.vdab.services.FiliaalService;
import be.vdab.valueobjects.Adres;

public class FiliaalControllerTest {
	
	private FiliaalController filiaalController;
	private List<Filiaal> filialen;
	private FiliaalService filiaalService;
	private Filiaal filiaal;

	@Before
	public void before() {
		filialen = Collections.emptyList();
		filiaalService = Mockito.mock(FiliaalService.class);
		Mockito.when(filiaalService.findAll()).thenReturn(filialen);
		
		filiaalController = new FiliaalController(filiaalService);

		filiaal = new Filiaal("naam1", true, BigDecimal.ONE, LocalDate.now(), 
				new Adres("straat1", "huisnr1", 1, "gemeente1"));
		Mockito.when(filiaalService.read(1)).thenReturn(filiaal);
	}
	
	@Test
	public void find_all_activeert_juiste_view() {
		assertEquals("filialen/filialen", filiaalController.findAll().getViewName());
	}
	
	@Test
	public void find_all_maakt_request_attribuut_filialen() {
		assertSame(filialen,
				filiaalController.findAll().getModelMap().get("filialen"));
	}
	
	@Test
	public void read_activeert_juiste_view() {
		assertEquals("filialen/filiaal", filiaalController.read(1L).getViewName());
	}
	
	@Test
	public void read_met_bestaande_id_geeft_filiaal_terug() {
		assertSame(filiaal, filiaalController.read(1L).getModelMap().get("filiaal"));
	}
	
	@Test
	public void read_met_onbestaande_id_geeft_null_terug() {
		assertNull(filiaalController.read(-1L).getModelMap().get("filiaal"));
	}

}
