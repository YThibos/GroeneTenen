package be.vdab.web;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.entities.Filiaal;
import be.vdab.exceptions.FiliaalNietLeegException;
import be.vdab.services.FiliaalService;
import be.vdab.valueobjects.PostcodeReeks;

@Controller
@RequestMapping("/filialen")
class FiliaalController {

	// VIEW PATHS
	private static final String FILIALEN_VIEW = "filialen/filialen";
	private static final String TOEVOEGEN_VIEW = "filialen/toevoegen";
	private static final String FILIAALDETAIL_VIEW = "filialen/filiaal";
	private static final String VERWIJDERD_VIEW = "filialen/verwijderd";
	private static final String PER_POSTCODE_VIEW = "filialen/perpostcode";
	
	// REDIRECT PATHS
	private static final String REDIRECT_URL_NA_TOEVOEGEN = "redirect:/filialen";
	private static final String REDIRECT_URL_FILIAAL_NIET_GEVONDEN = "redirect:/filialen";
	private static final String REDIRECT_URL_NA_VERWIJDEREN = "redirect:/filialen/{id}/verwijderd";
	private static final String REDIRECT_URL_HEEFT_NOG_WERKNEMERS = "redirect:/filialen/{id}";
	
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
	
	// INITBINDER
	@InitBinder("postcodeReeks")
	void initBinderPostcodeReeks(DataBinder dataBinder) {
		dataBinder.setRequiredFields("vanpostcode", "totpostcode");
	}
	
	// REQUEST HANDLING METHODS
	@RequestMapping(method = RequestMethod.GET)
	ModelAndView findAll() {
		return new ModelAndView(FILIALEN_VIEW, "filialen", filiaalService.findAll())
				.addObject("aantalFilialen", filiaalService.findAantalFilialen());
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
	
	@RequestMapping(method = RequestMethod.GET, path = "{id}")
	ModelAndView read(@PathVariable long id) {
		ModelAndView modelAndView = new ModelAndView(FILIAALDETAIL_VIEW);
		Filiaal filiaal = filiaalService.read(id);
		if (filiaal != null) {
			modelAndView.addObject(filiaal);
		}
		return modelAndView;
	}
	
	@RequestMapping(path = "{id}/verwijderen", method = RequestMethod.POST)
	String delete(@PathVariable long id, RedirectAttributes redirectAttributes) {
		Filiaal filiaal = filiaalService.read(id);
		if (filiaal == null) {
			return REDIRECT_URL_FILIAAL_NIET_GEVONDEN;
		}
		try {
			filiaalService.delete(id);
			redirectAttributes
				.addAttribute("id", id)
				.addAttribute("naam", filiaal.getNaam());
			return REDIRECT_URL_NA_VERWIJDEREN;
		}
		catch (FiliaalNietLeegException ex) {
			redirectAttributes
					.addAttribute("id", id)
					.addAttribute("fout", "Filiaal heeft nog werknemers");
			return REDIRECT_URL_HEEFT_NOG_WERKNEMERS;
		}
	}
	
	@RequestMapping(path = "{id}/verwijderd", method = RequestMethod.GET)
	String deleted(String naam) {
		return VERWIJDERD_VIEW;
	}
	
	@RequestMapping(path ="perpostcode", method = RequestMethod.GET)
	ModelAndView findByPostcodeReek() {
		ModelAndView mav = new ModelAndView(PER_POSTCODE_VIEW);
		
		PostcodeReeks reeks = new PostcodeReeks();
		reeks.setVanpostcode(1000);
		reeks.setTotpostcode(9999);
		mav.addObject(reeks);
		
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.GET, params = {"vanpostcode", "totpostcode"})
	ModelAndView findByPostcodeReeks(PostcodeReeks reeks, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView(PER_POSTCODE_VIEW);
		if (!bindingResult.hasErrors()) {
			List<Filiaal> filialen = filiaalService.findByPostcodeReeks(reeks);
			if (filialen.isEmpty()) {
				bindingResult.reject("geenFilialen");
			}
			else {
				mav.addObject("filialen", filialen);
			}
		}
		return mav;
	}
	
}
