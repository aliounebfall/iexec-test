package com.example.iexec.test.repository;

import com.example.iexec.test.model.LocalTask;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

/**
 * The LocalTask model reactive repository
 */
public interface LocalTaskRepository extends ReactiveMongoRepository<LocalTask, String> {
    Flux<LocalTask> findAllByTimestamp(String timestamp);
    Flux<LocalTask> findAllByDataLike(String data);
}
