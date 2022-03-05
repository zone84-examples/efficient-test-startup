package tech.zone84.examples.efficientteststartup.product;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.List;

@Controller("/products")
@ExecuteOn(TaskExecutors.IO)
public class ProductEndpoint {
    private static final Logger log = LoggerFactory.getLogger(ProductEndpoint.class);
    private final ProductRepository repository;

    @Inject
    public ProductEndpoint(ProductRepository repository) {
        this.repository = repository;
    }

    @Post("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public HttpResponse<Void> insertProduct(@Body Product product) {
        log.info("Received product: " + product);
        var id = this.repository.save(product);
        return HttpResponse.created(URI.create(id));
    }

    @Get("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> listProducts() {
        return repository.listAll();
    }

    @Get("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product fetchProduct(@PathVariable(name = "id") String id) {
        return repository.find(id);
    }
}
