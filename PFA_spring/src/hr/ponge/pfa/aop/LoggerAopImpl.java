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
		
	}

	/**
	 * This is the method which I would like to execute after a selected method
	 * execution.
	 */
	public void afterAdvice() {
		
	}

	/**
	 * This is the method which I would like to execute when any method returns.
	 */
	public void afterReturningAdvice(Object retVal) {
		
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
		String name = null;
		if (log.isTraceEnabled()) {
			name = pjp.getTarget().getClass().getCanonicalName() + "."
					+ pjp.getSignature().getName();
			log.trace("ENTRY " + name);
			for (Object arg : pjp.getArgs()) {
				log.trace("ENTRY ARG:" + arg.toString());
			}
		}
		Object retVal = pjp.proceed();

		if (log.isTraceEnabled()) {
			long stop = System.currentTimeMillis();
			long delta = stop - start;
			log.trace("TIME TO EXECUTE METHOD " + name + "  TIME:" + delta);
			if (retVal != null) {
				log.trace("EXIT " + name + "  RETURN VALUE("
						+ retVal.toString() + ")");
			} else {
				log.trace("EXIT " + name);
			}
		}
		return retVal;
	}
}
