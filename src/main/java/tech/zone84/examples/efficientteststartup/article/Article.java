package tech.zone84.examples.efficientteststartup.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import javax.validation.constraints.NotBlank;

@Introspected
public class Article {
    @BsonId
    @JsonIgnore
    private ObjectId id;

    @NotBlank
    @JsonProperty
    private String name;

    @NotBlank
    @JsonProperty
    private String content;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article{" +
            "name='" + name + '\'' +
            ", content='" + content + '\'' +
            '}';
    }
}
