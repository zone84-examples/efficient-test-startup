package tech.zone84.examples.efficientteststartup;

import io.micronaut.http.HttpStatus;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tech.zone84.examples.efficientteststartup.article.Article;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.zone84.examples.efficientteststartup.environment.TestUtils.step;

@MicronautTest
class ArticleTest {
    @Inject
    ApplicationClient client;

    @Test
    @DisplayName("should save and read articles")
    void shouldSaveAndReadArticles() {
        // given
        var someArticle = new Article();
        someArticle.setName("Some name");
        someArticle.setContent("Some content");

        // step 1: save article
        var id = step("Save article", () -> {
            // when
            var response = client.saveArticle(someArticle);

            // then
            assertThat((CharSequence) response.status()).isEqualTo(HttpStatus.CREATED);
            var result = response.header("location");
            assertThat(result)
                .isNotNull()
                .isNotBlank();
            return result;
        });

        step("Fetch previously created article", () -> {
            // when
            var response = client.fetchArticle(id);

            // then
            assertThat((CharSequence) response.status()).isEqualTo(HttpStatus.OK);
            var actual = response.body();
            assertThat(actual.getName()).isEqualTo(someArticle.getName());
            assertThat(actual.getContent()).isEqualTo(someArticle.getContent());
        });
    }
}
