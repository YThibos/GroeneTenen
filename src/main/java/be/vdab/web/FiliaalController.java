package be.vdab.web;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.entities.Filiaal;
import be.vdab.services.FiliaalService;

@Controller
@RequestMapping("/filialen")
class FiliaalController {

	// VIEW PATHS
	private static final String FILIALEN_VIEW = "filialen/filialen";
	private static final String TOEVOEGEN_VIEW = "filialen/toevoegen";
	private static final String FILIAALDETAIL_VIEW = "filialen/filiaal";
	
	// REDIRECT PATHS
	private static final String REDIRECT_URL_NA_TOEVOEGEN = "redirect:/filialen";
	
	// OTHERS
	private final FiliaalService filiaalService;
	private static final Logger logger =
			Logger.getLogger(FiliaalController.class.getName());
	
	
	// CONSTRUCTOR
	@Autowired
	public FiliaalController(FiliaalService filiaalService) {
		// Auto constructor injection door @Autowired
		// Spring zoekt een implementatie (FiliaalServiceImpl) en geeft die als param
		// (Indien meerdere implementaties: @Qualifier!
		this.filiaalService = filiaalService;
	}
	
	// REQUEST HANDLING METHODS
	@RequestMapping(method = RequestMethod.GET)
	ModelAndView findAll() {
		return new ModelAndView(FILIALEN_VIEW, "filialen", filiaalService.findAll());
	}
	
	@RequestMapping(path = "toevoegen", method = RequestMethod.GET)
	String createForm() {
		return TOEVOEGEN_VIEW;
	}

	@RequestMapping(method = RequestMethod.POST)
	String create() {
		logger.info("Filiaal record toevoegen aan database");
		return REDIRECT_URL_NA_TOEVOEGEN;
	}
	
	@RequestMapping(method = RequestMethod.GET, params = "id")
	ModelAndView read(long id) {
		ModelAndView modelAndView = new ModelAndView(FILIAALDETAIL_VIEW);
		Filiaal filiaal = filiaalService.read(id);
		if (filiaal != null) {
			modelAndView.addObject(filiaal);
		}
		return modelAndView;
	}
	
}
