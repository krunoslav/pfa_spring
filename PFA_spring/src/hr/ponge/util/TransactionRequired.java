package hr.ponge.util;

import hr.ponge.pfa.PfaException;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Transactional(rollbackFor=PfaException.class,propagation=Propagation.REQUIRED)
public @interface TransactionRequired {

}
