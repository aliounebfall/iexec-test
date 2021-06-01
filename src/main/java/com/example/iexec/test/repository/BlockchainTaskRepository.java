package com.example.iexec.test.repository;

import com.example.iexec.test.model.BlockchainTask;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

/**
 * The BlockchainTask model reactive repository
 */
public interface BlockchainTaskRepository extends ReactiveMongoRepository<BlockchainTask, String> {
    Flux<BlockchainTask> findAllByTransactionHash(String transactionHash);
    Flux<BlockchainTask> findAllByDataLike(String data);
}
