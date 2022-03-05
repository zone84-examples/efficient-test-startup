package tech.zone84.examples.efficientteststartup.product;

import io.micronaut.core.annotation.NonNull;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface ProductRepository {
    List<Product> listAll();
    Product find(String id);
    String save(@NonNull @NotNull @Valid Product article);
}
