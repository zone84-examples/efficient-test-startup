package tech.zone84.examples.efficientteststartup.environment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

public class Environment {
    private static final Logger log = LoggerFactory.getLogger(Environment.class);
    private final MongoDBContainer mongoDb = new MongoDBContainer(DockerImageName.parse("mongo:5.0.5"));

    public int getMongoDbPort() {
        return mongoDb.getFirstMappedPort();
    }

    public void startServices() {
        mongoDb.start();
        log.info("Started MongoDB service on mapped port " + getMongoDbPort());
    }

    public void stopServices() {
        log.info("Stopping all services...");
        mongoDb.stop();
        log.info("Stopped all services");
    }
}
