package be.vdab.restservices;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import be.vdab.entities.Filiaal;
import be.vdab.exceptions.FiliaalNietGevondenException;
import be.vdab.services.FiliaalService;

@RestController
@RequestMapping("/filialen")
class FiliaalRestController {

	private final FiliaalService filiaalService;
	
	@Autowired
	public FiliaalRestController(FiliaalService filiaalService) {
		this.filiaalService = filiaalService;
	}
	
	@RequestMapping(path = "{filiaal}", method = RequestMethod.GET)
	Filiaal read(@PathVariable Filiaal filiaal) {
		if (filiaal == null) {
			throw new FiliaalNietGevondenException();
		}
		return filiaal;
	}
	
	@RequestMapping(path = "{filiaal}", method = RequestMethod.DELETE)
	void delete(@PathVariable Filiaal filiaal) {
		if (filiaal == null) {
			throw new FiliaalNietGevondenException();
		}
		filiaalService.delete(filiaal.getId());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	void create(@RequestBody @Valid Filiaal filiaal) {
		filiaalService.create(filiaal);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String filiaalMetVerkeerdeProperties(MethodArgumentNotValidException ex) {
		StringBuilder fouten = new StringBuilder();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			fouten.append(error.getField()).append(':')
			.append(error.getDefaultMessage()).append('\n');
		}
		fouten.deleteCharAt(fouten.length() - 1);
		return fouten.toString();
	}
	
	@ExceptionHandler(FiliaalNietGevondenException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	void filiaalNietGevonden() {
		
	}
	
}
