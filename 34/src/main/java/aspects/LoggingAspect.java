package aspects;

import model.Comment;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {
    private Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @Around("@annotation(ToLog)")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object [] arguments = joinPoint.getArgs();

        logger.info("Method " + methodName +
                " with parameters " + Arrays.asList(arguments) +
                " will execute");

        Object returnedByMethod = joinPoint.proceed();

        logger.info("Method executed and returned " + returnedByMethod);

        return returnedByMethod;
    }







    /*
    @Around("execution(* services.*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {

        String methodName = joinPoint.getSignature().getName();
        Object [] arguments = joinPoint.getArgs();

        String av = "";
        String tex = "";
        Comment comment = null;
        for (Object ar : arguments){
            comment = (Comment) ar;
             av = comment.getAuthor();
             tex = comment.getText();
        }

        logger.info("Method " + methodName +
                " with parameters: " + av + " ==> " + tex + " " +
                " will execute");

        System.out.println("parameters: " + comment.toString() + Thread.currentThread().getName());

        Comment cmt = new Comment();
        cmt.setText("Some other text!");
        cmt.setAuthor("New_Author");
        Object [] newArguments = {cmt};

        Object returnedByMethod = joinPoint.proceed(newArguments);
        logger.info("Method executed and returned " + returnedByMethod);
        logger.info("Thread = " + Thread.currentThread().getName());
        return "SUCCESS_new";
    */

    public void setLogger(Logger logger) {
        this.logger = logger;
    }


}
