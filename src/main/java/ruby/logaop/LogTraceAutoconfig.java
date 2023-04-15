package ruby.logaop;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@ConditionalOnProperty(name = "log-trace", havingValue = "on")
public class LogTraceAutoconfig {

    @Bean
    public LogTraceAspect logTraceAspect() {
        return new LogTraceAspect();
    }
}
