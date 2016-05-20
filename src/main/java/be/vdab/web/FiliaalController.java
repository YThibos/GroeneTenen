package be.vdab.web;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/filialen")
class FiliaalController {

	// VIEW PATHS
	private static final String FILIALEN_VIEW = "filialen/filialen";
	private static final String TOEVOEGEN_VIEW = "filialen/toevoegen";
	
	// REDIRECT PATHS
	private static final String REDIRECT_URL_NA_TOEVOEGEN = "redirect:/filialen";
	
	// OTHERS
	private static final Logger logger =
			Logger.getLogger(FiliaalController.class.getName());
	
	
	// REQUEST HANDLING METHODS
	@RequestMapping(method = RequestMethod.GET)
	String findAll() {
		return FILIALEN_VIEW;
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
	
}
