package tech.zone84.examples.efficientteststartup.article;

import io.micronaut.core.annotation.NonNull;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface ArticleRepository {
    List<Article> listAll();
    Article find(String id);
    String save(@NonNull @NotNull @Valid Article article);
}
