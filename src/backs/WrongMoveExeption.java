package backs;

public class WrongMoveExeption extends Exception {
	   /* I do not know what serialVersionUID is,
	    * but Eclipse asked me to create it
	    */
	private static final long serialVersionUID = 1L;
	String fault;

	   public WrongMoveExeption(String x) {
	      this.fault = x;
	   }

	   public String toString() {
	      return "WrongMoveExeption[ " + fault + " ]";
	   }
}