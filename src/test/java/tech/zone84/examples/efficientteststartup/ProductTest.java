package tech.zone84.examples.efficientteststartup;

import io.micronaut.http.HttpStatus;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tech.zone84.examples.efficientteststartup.product.Product;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.zone84.examples.efficientteststartup.environment.TestUtils.step;

@MicronautTest
public class ProductTest {
    @Inject
    ApplicationClient client;

    @Test
    @DisplayName("should save and read products")
    void shouldSaveAndReadProduct() {
        // given
        var someProduct = new Product();
        someProduct.setName("Some name");
        someProduct.setDescription("Some description");

        // step 1: save article
        var id = step("Save product", () -> {
            // when
            var response = client.saveProduct(someProduct);

            // then
            assertThat((CharSequence) response.status()).isEqualTo(HttpStatus.CREATED);
            var result = response.header("location");
            assertThat(result)
                .isNotNull()
                .isNotBlank();
            return result;
        });

        step("Fetch previously created product", () -> {
            // when
            var response = client.fetchProduct(id);

            // then
            assertThat((CharSequence) response.status()).isEqualTo(HttpStatus.OK);
            var actual = response.body();
            assertThat(actual.getName()).isEqualTo(someProduct.getName());
            assertThat(actual.getDescription()).isEqualTo(someProduct.getDescription());
        });
    }
}
