package tech.zone84.examples.efficientteststartup.product;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.bson.types.ObjectId;
import tech.zone84.examples.efficientteststartup.MongoDbConfiguration;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class MongoDbProductRepository implements ProductRepository {
    private final MongoClient client;
    private final MongoDbConfiguration configuration;

    @Inject
    public MongoDbProductRepository(MongoClient client, MongoDbConfiguration configuration) {
        this.client = client;
        this.configuration = configuration;
    }

    @Override
    public List<Product> listAll() {
        return acquireCollection().find().into(new ArrayList<>());
    }

    @Override
    public Product find(String id) {
        var query = new BasicDBObject();
        query.put("_id", new ObjectId(id));
        return acquireCollection().find(query).first();
    }

    @Override
    public String save(Product product) {
        return acquireCollection().insertOne(product).getInsertedId().asObjectId().getValue().toHexString();
    }

    private MongoCollection<Product> acquireCollection() {
        return client.getDatabase(configuration.getDatabaseName())
            .getCollection(configuration.getProductCollectionName(), Product.class);
    }
}
