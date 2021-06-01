package com.example.iexec.test.repository;

import com.example.iexec.test.model.BlockchainTask;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class BlockchainTaskRepositoryTest {

    @Autowired
    BlockchainTaskRepository underTest;

    private BlockchainTask getNewBlockchainTask(){
        final String transactionHash = String.valueOf(new Timestamp(System.currentTimeMillis()).getTime());
        final String data = "Processed BlockchainTask at " + transactionHash;
        return new BlockchainTask(transactionHash, data);
    }

    @Test
    void itShouldFetchAllBlockchainTasks() {
        //Given
        //When
        final List<BlockchainTask> all = underTest.findAll().collectList().block();
        //Then
        assertThat(all).isNotNull();
        all.forEach(System.out::println);
    }

    @Test
    void itShouldSaveBlockchainTask() {
        //Given
        final BlockchainTask newBlockchainTask = getNewBlockchainTask();
        //When
        final BlockchainTask savedBlockchainTask = underTest.save(newBlockchainTask).block();
        //Then
        assertThat(savedBlockchainTask).isNotNull();
        assertThat(savedBlockchainTask.getTransactionHash()).isEqualTo(newBlockchainTask.getTransactionHash());
        System.out.println(savedBlockchainTask);
    }

    @Test
    void itShouldFetchAllBlockchainTasksByTransactionHash() {
        //Given
        final BlockchainTask newBlockchainTask = getNewBlockchainTask();
        //When
        final BlockchainTask savedBlockchainTask = underTest.save(newBlockchainTask).block();
        final List<BlockchainTask> all = underTest.findAllByTransactionHash(savedBlockchainTask.getTransactionHash()).collectList().block();
        //Then
        assertThat(all).isNotNull();
        assertThat(all.size()).isEqualTo(1);
        all.forEach(System.out::println);
    }

    @Test
    void itShouldFetchAllBlockchainTasksByData() {
        //Given
        final BlockchainTask newBlockchainTask = getNewBlockchainTask();
        //When
        final BlockchainTask savedBlockchainTask = underTest.save(newBlockchainTask).block();
        final List<BlockchainTask> all = underTest.findAllByDataLike(savedBlockchainTask.getTransactionHash()).collectList().block();
        //Then
        assertThat(all).isNotNull();
        assertThat(all.size()).isEqualTo(1);
        all.forEach(System.out::println);
    }

    @Test
    void itShouldDeleteBlockchainTask() {
        //Given
        final BlockchainTask newBlockchainTask = getNewBlockchainTask();
        //When
        underTest.delete(newBlockchainTask).block();
        //Then
        final Optional<BlockchainTask> optionalBlockchainTask = underTest.findById(newBlockchainTask.getTransactionHash()).blockOptional();
        assertThat(optionalBlockchainTask.isEmpty()).isTrue();
    }
}