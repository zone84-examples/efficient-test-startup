package tech.zone84.examples.efficientteststartup.article;

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

@Controller("/articles")
@ExecuteOn(TaskExecutors.IO)
public class ArticleEndpoint {
    private static final Logger log = LoggerFactory.getLogger(ArticleEndpoint.class);
    private final ArticleRepository repository;

    @Inject
    public ArticleEndpoint(ArticleRepository repository) {
        this.repository = repository;
    }

    @Post("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public HttpResponse<Void> publishArticle(@Body Article article) {
        log.info("Received article: " + article);
        var id = this.repository.save(article);
        return HttpResponse.created(URI.create(id));
    }

    @Get("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Article> listArticles() {
        return repository.listAll();
    }

    @Get("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Article fetchArticle(@PathVariable(name = "id") String id) {
        var article = repository.find(id);
        return article;
    }
}
