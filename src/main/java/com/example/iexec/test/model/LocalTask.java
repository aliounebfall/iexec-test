package com.example.iexec.test.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Random;

/**
 * The LocalTask model
 */
@Document
public class LocalTask {

    @Id
    private String id;
    @NotNull
    private String timestamp;
    @NotNull
    private String data;

    public LocalTask(@JsonProperty("timestamp") String timestamp,
                     @JsonProperty("data") String data) {
        final Random random = new Random();
        this.id = Long.toUnsignedString(random.nextLong());
        this.timestamp = timestamp;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LocalTask{" +
                "id='" + id + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}