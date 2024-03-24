package com.poc.feeder;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import org.bson.Document;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

public abstract class GenericEntityFeeder<T> {

    private final GenericMongoRepository<T> mongoRepository;
    private final GenericJpaRepository<T> jpaRepository;
    private final ListeningExecutorService executorService;
    private final int chunkSize; // Adjust chunk size based on memory constraints

    public GenericEntityFeeder(GenericMongoRepository<T> mongoRepository, GenericJpaRepository<T> jpaRepository, ExecutorService executorService) {
        this.mongoRepository = mongoRepository;
        this.jpaRepository = jpaRepository;
        this.executorService = (ListeningExecutorService) executorService; // Cast to ListeningExecutorService
        this.chunkSize = 1000; // Adjust as needed
    }

    public void feedFileData(String filePath) throws Exception {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            List<String> records = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                records.add(line);
                if (records.size() == chunkSize) {
                    saveRecords(records);
                    records.clear();
                }
            }
            if (!records.isEmpty()) {
                saveRecords(records);
            }
        }
    }

    public void feedMongoDataInPages() throws Exception {
        int pageNumber = 0;
        while (true) {
            Slice<Document> dataChunk = mongoRepository.findAll(PageRequest.of(pageNumber, chunkSize));
            List<Document> entityData = dataChunk.getContent();

            if (entityData.isEmpty()) {
                break; // No more data to process
            }

            List<ListenableFuture<Void>> futures = new ArrayList<>();
            futures.add(executorService.submit(new SaveEntitiesTask(entityData)));
            Futures.allAsList(futures).get(); // Wait for chunk to be processed
            pageNumber++;
        }
    }

    public void feedMongoData() throws Exception {
        List<Document> entityData = mongoRepository.findAll();
        List<ListenableFuture<Void>> futures = new ArrayList<>();
        for (int i = 0; i < entityData.size(); i += chunkSize) {
            int endIndex = Math.min(i + chunkSize, entityData.size());
            List<Document> subChunk = entityData.subList(i, endIndex);
            futures.add(executorService.submit(new SaveEntitiesTask(subChunk)));
        }
        Futures.allAsList(futures).get(); // Wait for all sub-chunks to be processed
    }

    private void saveRecords(List<String> records) throws Exception {
        List<ListenableFuture<Void>> futures = new ArrayList<>();
        for (int i = 0; i < records.size(); i += 100) { // Further sub-chunk for parallel processing within a chunk
            int endIndex = Math.min(i + 100, records.size());
            List<String> subChunk = records.subList(i, endIndex);
            futures.add(executorService.submit(new SaveRecordsTask(subChunk)));
        }
        Futures.allAsList(futures).get(); // Wait for all sub-chunks to be processed
    }

    private class SaveRecordsTask implements Callable<Void> {

        private final List<String> records;

        public SaveRecordsTask(List<String> records) {
            this.records = records;
        }

        @Override
        public Void call() throws Exception {
            List<T> entities = new ArrayList<>();
            for (String record : records) {
                // Parse data and create Employee objects
                T entity = parseEntity(record);
                entities.add(entity);
            }
            jpaRepository.saveAll(entities); // Leverage JpaRepository's saveAll for bulk insert
            return null;
        }
    }

    private class SaveEntitiesTask implements Callable<Void> {

        private final List<Document> entityData;

        public SaveEntitiesTask(List<Document> entityData) {
            this.entityData = entityData;
        }

        @Override
        public Void call() throws Exception {
            List<T> entities = new ArrayList<>();
            for (Document doc : entityData) {
                // Parse Document and create Entity objects (implement in subclasses)
                T entity = parseEntity(doc);
                entities.add(entity);
            }
            jpaRepository.saveAll(entities); // Leverage JPA's saveAll for bulk insert
            return null;
        }
    }

    // Implement parseEntity method to convert data to Entity object (abstract)
    protected abstract T parseEntity(String record);

    protected abstract T parseEntity(Document doc);


}