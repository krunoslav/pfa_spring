package hr.ponge.pfa.aop;

public interface LoggerAop {
	
	/** 
	    * This is the method which I would like to execute
	    * before a selected method execution.
	    */
	   public void beforeAdvice();

	   /** 
	    * This is the method which I would like to execute
	    * after a selected method execution.
	    */
	   public void afterAdvice();

	   /** 
	    * This is the method which I would like to execute
	    * when any method returns.
	    */
	   public void afterReturningAdvice(Object retVal);

	   /**
	    * This is the method which I would like to execute
	    * if there is an exception raised.
	    */
	   public void afterThrowingAdvice(Throwable ex);

}
