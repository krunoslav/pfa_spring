package hr.ponge.pfa.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

public class LoggerAopImpl {

	private static final XLogger log = XLoggerFactory
			.getXLogger(LoggerAopImpl.class);

	/**
	 * This is the method which I would like to execute before a selected method
	 * execution.
	 */
	public void beforeAdvice() {
		log.debug("beforeAdvice");
	}

	/**
	 * This is the method which I would like to execute after a selected method
	 * execution.
	 */
	public void afterAdvice() {
		log.debug("afterAdvice");
	}

	/**
	 * This is the method which I would like to execute when any method returns.
	 */
	public void afterReturningAdvice(Object retVal) {
		log.debug("afterReturningAdvice Returning:" + retVal);
	}

	/**
	 * This is the method which I would like to execute if there is an exception
	 * raised.
	 */
	public void afterThrowingAdvice(Throwable ex) {
		log.debug("There has been an exception: " + ex.toString());
	}

	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis();
		Object retVal = pjp.proceed();
		long stop = System.currentTimeMillis();
		long delta = stop - start;

		log.debug("TIME TO EXECUTE METHOD "
				+ pjp.getSignature().toShortString() + " ON TARGET "
				+ pjp.getTarget() + "  TIME:" + delta);
		return retVal;
	}
}
