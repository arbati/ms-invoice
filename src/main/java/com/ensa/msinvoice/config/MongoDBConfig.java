package com.ensa.msinvoice.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoDBConfig extends AbstractMongoClientConfiguration {

    static final String uri="mongodb+srv://abdoadmin:VbGvpRluCgvrOMnr@clusterms0.t10ilja.mongodb.net/?retryWrites=true&w=majority";
    static final String db="ms_db_invoice";

    @Override
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString(uri);
        MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(connectionString).build();
        return MongoClients.create(settings);
    }

    @Override
    protected String getDatabaseName() {
        return db;
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoClient(), getDatabaseName());
    }

}
