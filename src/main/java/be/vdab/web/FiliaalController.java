package be.vdab.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
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
	private static final String WIJZIGEN_VIEW = "filialen/wijzigen";
	
	// REDIRECT PATHS
	private static final String REDIRECT_URL_NA_TOEVOEGEN = "redirect:/filialen";
	private static final String REDIRECT_URL_FILIAAL_NIET_GEVONDEN = "redirect:/filialen";
	private static final String REDIRECT_URL_NA_VERWIJDEREN = "redirect:/filialen/{id}/verwijderd";
	private static final String REDIRECT_URL_HEEFT_NOG_WERKNEMERS = "redirect:/filialen/{id}";
	private static final String REDIRECT_URL_NA_WIJZIGEN = "redirect:/filialen";
	
	// OTHERS
	private final FiliaalService filiaalService;
	
	
	// CONSTRUCTOR
	@Autowired
	public FiliaalController(FiliaalService filiaalService) {
		// Auto constructor injection door @Autowired
		// Spring zoekt een implementatie (FiliaalServiceImpl) en geeft die als param
		// (Indien meerdere implementaties: @Qualifier!
		this.filiaalService = filiaalService;
	}
	
	//-----------------------------------------------------------
	// ----------------------INITBINDERS-------------------
	//-----------------------------------------------------------
//	@InitBinder("postcodeReeks")
//	void initBinderPostcodeReeks(DataBinder dataBinder) {
//		dataBinder.setRequiredFields("vanpostcode", "totpostcode");
//	}
	
	@InitBinder("postcodeReeks")
	void initBinderPostcodeReeks(WebDataBinder binder) {
		binder.initDirectFieldAccess();
	}
	
	@InitBinder("filiaal")
	void initBinderFiliaal(WebDataBinder binder) {
		binder.initDirectFieldAccess();
	}
	
	//-----------------------------------------------------------
	// ---------------REQUEST HANDLING METHODS-------------------
	//-----------------------------------------------------------
	
	@RequestMapping(method = RequestMethod.GET)
	ModelAndView findAll() {
		return new ModelAndView(FILIALEN_VIEW, "filialen", filiaalService.findAll())
				.addObject("aantalFilialen", filiaalService.findAantalFilialen());
	}

	
	//--------------FILIAAL TOEVOEGEN------------------- 
	
	@RequestMapping(path = "toevoegen", method = RequestMethod.GET)
	ModelAndView createForm() {
		return new ModelAndView(TOEVOEGEN_VIEW, "filiaal", new Filiaal());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	String create(@Valid Filiaal filiaal, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return TOEVOEGEN_VIEW;
		}
		filiaalService.create(filiaal);
		return REDIRECT_URL_NA_TOEVOEGEN;
	}
	
	//--------------FILIAAL WIJZIGEN-------------------
	@RequestMapping(path = "{id}/wijzigen", method = RequestMethod.GET)
	ModelAndView updateForm(@PathVariable long id) {
		Filiaal filiaal = filiaalService.read(id);
		if (filiaal == null) {
			return new ModelAndView(REDIRECT_URL_FILIAAL_NIET_GEVONDEN);
		}
		return new ModelAndView(WIJZIGEN_VIEW).addObject(filiaal);
	}
	
	@RequestMapping(path = "{id}/wijzigen", method = RequestMethod.POST)
	String update(@Valid Filiaal filiaal, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return WIJZIGEN_VIEW;
		}
		filiaalService.update(filiaal);
		return REDIRECT_URL_NA_WIJZIGEN;
	}
	
	//-------------------FILIAAL LEZEN OP ID----------------
	
	@RequestMapping(method = RequestMethod.GET, path = "{id}")
	ModelAndView read(@PathVariable long id) {
		ModelAndView modelAndView = new ModelAndView(FILIAALDETAIL_VIEW);
		Filiaal filiaal = filiaalService.read(id);
		if (filiaal != null) {
			modelAndView.addObject(filiaal);
		}
		return modelAndView;
	}
	
	//-------------------FILIAAL VERWIJDEREN-----------------------
	
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
	
	//--------------------ZOEKEN OP POSTCODE------------------------
	
	@RequestMapping(path ="perpostcode", method = RequestMethod.GET)
	ModelAndView findByPostcodeReek() {
		ModelAndView mav = new ModelAndView(PER_POSTCODE_VIEW);
		
		PostcodeReeks reeks = new PostcodeReeks();
		mav.addObject(reeks);
		
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.GET, params = {"vanpostcode", "totpostcode"})
	ModelAndView findByPostcodeReeks(@Valid PostcodeReeks reeks, BindingResult bindingResult) {
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
