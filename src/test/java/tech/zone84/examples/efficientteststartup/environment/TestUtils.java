package tech.zone84.examples.efficientteststartup.environment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

public class TestUtils {
    private static final Logger log = LoggerFactory.getLogger(TestUtils.class);

    public static void step(String name, Runnable step) {
        log.info("Starting test step: '" + name + "'");
        step.run();
        log.info("Finished test step: '" + name + "'");
    }

    public static <R> R step(String name, Supplier<R> step) {
        log.info("Starting test step: '" + name + "'");
        var result = step.get();
        log.info("Finished test step: '" + name + "' with return value: " + result);
        return result;
    }
}
