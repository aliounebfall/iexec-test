package com.example.iexec.test.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The Blockchain model
 */
@Document
public class BlockchainTask {

    @Id
    private String transactionHash;
    private String data;

    public BlockchainTask(String transactionHash, String data) {
        this.transactionHash = transactionHash;
        this.data = data;
    }

    public String getTransactionHash() {
        return transactionHash;
    }

    public void setTransactionHash(String transactionHash) {
        this.transactionHash = transactionHash;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BlockchainTask{" +
                "transactionHash='" + transactionHash + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
