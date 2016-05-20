package be.vdab.exceptions;

public class FiliaalNietLeegException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FiliaalNietLeegException() {
		super();
	}
	
	public FiliaalNietLeegException(String msg) {
		super(msg);
	}
	
	public FiliaalNietLeegException(Throwable ex) {
		super(ex);
	}
	
	public FiliaalNietLeegException(String msg, Throwable ex) {
		super(msg, ex);
	}
	
}
