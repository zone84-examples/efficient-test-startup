package tech.zone84.examples.efficientteststartup;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;
import tech.zone84.examples.efficientteststartup.article.Article;
import tech.zone84.examples.efficientteststartup.product.Product;

@Client("/")
public interface ApplicationClient {
    @Post("/articles")
    HttpResponse<Void> saveArticle(@Body Article article);

    @Get("/articles/{id}")
    @Consumes("application/json")
    HttpResponse<Article> fetchArticle(@PathVariable String id);

    @Post("/products")
    HttpResponse<Void> saveProduct(@Body Product product);

    @Get("/products/{id}")
    @Consumes("application/json")
    HttpResponse<Product> fetchProduct(@PathVariable String id);
}
