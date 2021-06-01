package com.example.iexec.test.service;

import com.example.iexec.test.model.BlockchainTask;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.web3j.protocol.exceptions.TransactionException;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BlockchainTaskServiceTest {

    @Autowired
    BlockchainTaskService underTest;

    @Test
    void itShouldGetBlockchainTaskCount() throws IOException {
        //Given
        //When
        final int blockchainTaskCount = underTest.getBlockchainTaskCount();
        //Then
        assertThat(blockchainTaskCount).isNotEqualTo(-1);
        System.out.println(blockchainTaskCount);
    }

    @Test
    void itShouldCreateANewBlockchainTask() throws IOException, TransactionException {
        //Given
        //When
        final BlockchainTask blockchainTask = underTest.createNewTask();
        //Then
        assertThat(blockchainTask).isNotNull();
        System.out.println(blockchainTask.toString());
    }
}