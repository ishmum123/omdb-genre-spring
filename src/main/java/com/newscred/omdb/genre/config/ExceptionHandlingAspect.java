package com.newscred.omdb.genre.config;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class ExceptionHandlingAspect {

    public static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlingAspect.class);

    @AfterThrowing(pointcut = "execution(* com.synesis.bcc.structure.*.*.*(..))", throwing = "ex")
    public void logBasePackageErrors(Exception ex) {
        logError(ex);
    }

    @AfterThrowing(pointcut = "execution(* com.synesis.bcc.structure.*.*.*.*(..))", throwing = "ex")
    public void logSubPackageErrors(Exception ex) {
        logError(ex);
    }

    private void logError(Exception ex) {
        LOGGER.error(ex.getClass().getSimpleName() + ": (" + ex.getMessage() + ") " + ex.getStackTrace()[0].toString());
    }
}
