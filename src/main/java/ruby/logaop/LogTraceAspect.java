package ruby.logaop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

@Slf4j
@Aspect
public class LogTraceAspect {

    @Before("@annotation(ruby.logaop.LogTrace) || @within(ruby.logaop.LogTrace)")
    public void doTrace(JoinPoint joinPoint) {
        boolean traceState = getTraceState(joinPoint);

        if (traceState) {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Object[] args = joinPoint.getArgs();
            log.info("{} / args = {}", signature, args);
        }
    }

    private boolean getTraceState(JoinPoint joinPoint) {
        LogTrace methodAnnotation = getStuckAnnotationToMethod(joinPoint);
        if (methodAnnotation != null) {
            return methodAnnotation.trace();
        }

        LogTrace classAnnotation = getStuckAnnotationToClass(joinPoint);
        return classAnnotation.trace();
    }

    private LogTrace getStuckAnnotationToMethod(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        return method.getAnnotation(LogTrace.class);
    }

    private LogTrace getStuckAnnotationToClass(JoinPoint joinPoint) {
        Class<?> aClass = joinPoint.getTarget().getClass();
        return aClass.getAnnotation(LogTrace.class);
    }
}
