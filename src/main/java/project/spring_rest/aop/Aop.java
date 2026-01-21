package project.spring_rest.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Aop {

    private  static final Logger logger= LoggerFactory.getLogger(Aop.class);
    @After("execution(* project.spring_rest.JobService.*(..))")
    public void  logs(JoinPoint jp){
        logger.info("method called"+" "+jp.getSignature().getName());
    }
}
