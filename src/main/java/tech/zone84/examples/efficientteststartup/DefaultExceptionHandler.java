package tech.zone84.examples.efficientteststartup;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Produces
@Singleton
public class DefaultExceptionHandler implements ExceptionHandler<Exception, HttpResponse<Void>> {
    private static final Logger log = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    @Override
    public HttpResponse<Void> handle(HttpRequest request, Exception exception) {
        log.error("An error has occurred", exception);
        return HttpResponse.serverError();
    }
}
