package be.vdab.web;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import be.vdab.entities.Filiaal;
import be.vdab.services.FiliaalService;

public class FiliaalControllerTest {
	
	private FiliaalController filiaalController;
	private List<Filiaal> filialen;
	private FiliaalService filiaalService;

	@Before
	public void before() {
		filialen = Collections.emptyList();
		filiaalService = Mockito.mock(FiliaalService.class);
		Mockito.when(filiaalService.findAll()).thenReturn(filialen);
		filiaalController = new FiliaalController(filiaalService);
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

}
