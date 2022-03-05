package tech.zone84.examples.efficientteststartup.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import javax.validation.constraints.NotBlank;

@Introspected
public class Product {
    @BsonId
    @JsonIgnore
    private ObjectId id;

    @NotBlank
    @JsonProperty
    private String name;

    @NotBlank
    @JsonProperty
    private String description;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    @JsonProperty("id")
    public String getProductId() {
        if (null == id) {
            return "";
        }
        return id.toHexString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
            "name='" + name + '\'' +
            ", description='" + description + '\'' +
            '}';
    }
}
