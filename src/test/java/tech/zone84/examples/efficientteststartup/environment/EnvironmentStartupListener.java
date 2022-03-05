package tech.zone84.examples.efficientteststartup.environment;

import io.micronaut.context.env.ActiveEnvironment;
import io.micronaut.context.env.PropertySource;
import io.micronaut.context.env.PropertySourceLoader;
import io.micronaut.core.io.ResourceLoader;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestPlan;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

public class EnvironmentStartupListener implements TestExecutionListener, PropertySourceLoader {
    private static volatile Environment environment;

    @Override
    public void testPlanExecutionStarted(TestPlan testPlan) {
        environment = new Environment();
        environment.startServices();
    }

    @Override
    public void testPlanExecutionFinished(TestPlan testPlan) {
        environment.stopServices();
    }

    @Override
    public Optional<PropertySource> load(String resourceName, ResourceLoader resourceLoader) {
        if (resourceName.equals("application")) {
            return Optional.of(PropertySource.of(Map.of(
                    "mongodb.uri", "mongodb://localhost:" + environment.getMongoDbPort()
                )));
        }
        return Optional.empty();
    }

    @Override
    public Optional<PropertySource> loadEnv(String resourceName, ResourceLoader resourceLoader, ActiveEnvironment activeEnvironment) {
        return Optional.empty();
    }

    @Override
    public Map<String, Object> read(String name, InputStream input) throws IOException {
        return null;
    }
}
