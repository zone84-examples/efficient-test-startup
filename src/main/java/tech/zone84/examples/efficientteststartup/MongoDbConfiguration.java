package tech.zone84.examples.efficientteststartup;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.core.annotation.NonNull;

@ConfigurationProperties("repository")
public interface MongoDbConfiguration {
    @NonNull
    String getDatabaseName();

    @NonNull
    String getArticleCollectionName();

    @NonNull
    String getProductCollectionName();
}
