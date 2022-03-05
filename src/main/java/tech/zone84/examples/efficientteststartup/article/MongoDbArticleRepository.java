package tech.zone84.examples.efficientteststartup.article;

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
public class MongoDbArticleRepository implements ArticleRepository {
    private final MongoClient client;
    private final MongoDbConfiguration configuration;

    @Inject
    public MongoDbArticleRepository(MongoClient client, MongoDbConfiguration configuration) {
        this.client = client;
        this.configuration = configuration;
    }

    @Override
    public List<Article> listAll() {
        return acquireCollection().find().into(new ArrayList<>());
    }

    @Override
    public Article find(String id) {
        var query = new BasicDBObject();
        query.put("_id", new ObjectId(id));
        return acquireCollection().find(query).first();
    }

    @Override
    public String save(Article article) {
        return acquireCollection().insertOne(article).getInsertedId().asObjectId().getValue().toHexString();
    }

    private MongoCollection<Article> acquireCollection() {
        return client.getDatabase(configuration.getDatabaseName())
            .getCollection(configuration.getArticleCollectionName(), Article.class);
    }
}
