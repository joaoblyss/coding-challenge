package com.individee.codingchallenge.sched;

import com.mongodb.client.MongoClient;
import com.mongodb.connection.ClusterDescription;
import com.mongodb.connection.ServerDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class DatabaseConnectionMonitor {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConnectionMonitor.class);

    private ApplicationContext applicationContext;
    private MongoClient mongoClient;

    @Inject
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Inject
    public void setMongoClient(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Scheduled(fixedRate = 5000)
    public void databaseStatusPoll() {
        ClusterDescription clusterDescription = this.mongoClient.getClusterDescription();
        List<ServerDescription> serverDescriptions = clusterDescription.getServerDescriptions();
        ServerDescription currentServerDescription = serverDescriptions.get(0);
        if (currentServerDescription.getException() != null) {
            LOGGER.info("Shutting down!");
            SpringApplication.exit(applicationContext, () -> 1);
        }
    }

}
