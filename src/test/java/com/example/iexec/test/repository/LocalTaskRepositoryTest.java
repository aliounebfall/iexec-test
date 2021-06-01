package com.example.iexec.test.repository;

import com.example.iexec.test.model.LocalTask;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataMongoTest
class LocalTaskRepositoryTest {

    @Autowired
    LocalTaskRepository underTest;

    private LocalTask getNewLocalTask(){
        final String timestamp = String.valueOf(new Timestamp(System.currentTimeMillis()).getTime());
        final String data = "Processed localTask at " + timestamp;
        return new LocalTask(timestamp, data);
    }

    @Test
    void itShouldFetchAllLocalTasks() {
        //Given
        //When
        final List<LocalTask> all = underTest.findAll().collectList().block();
        //Then
        assertThat(all).isNotNull();
        all.forEach(System.out::println);
    }

    @Test
    void itShouldSaveLocalTask() {
        //Given
        final LocalTask newLocalTask = getNewLocalTask();
        //When
        final LocalTask savedLocalTask = underTest.save(newLocalTask).block();
        //Then
        assertThat(savedLocalTask).isNotNull();
        assertThat(savedLocalTask.getId()).isEqualTo(newLocalTask.getId());
        System.out.println(savedLocalTask);
    }

    @Test
    void itShouldFetchLocalTaskById() {
        //Given
        final LocalTask newLocalTask = getNewLocalTask();
        //When
        final LocalTask savedLocalTask = underTest.save(newLocalTask).block();
        final Optional<LocalTask> optionalLocalTask = underTest.findById(savedLocalTask.getId()).blockOptional();
        //Then
        assertThat(optionalLocalTask.isPresent()).isTrue();
        System.out.println(optionalLocalTask.get());
    }

    @Test
    void itShouldFetchAllLocalTasksByTimestamp() {
        //Given
        final LocalTask newLocalTask = getNewLocalTask();
        //When
        final LocalTask savedLocalTask = underTest.save(newLocalTask).block();
        final List<LocalTask> all = underTest.findAllByTimestamp(savedLocalTask.getTimestamp()).collectList().block();
        //Then
        assertThat(all).isNotNull();
        assertThat(all.size()).isEqualTo(1);
        all.forEach(System.out::println);
    }

    @Test
    void itShouldFetchAllLocalTasksByData() {
        //Given
        final LocalTask newLocalTask = getNewLocalTask();
        //When
        final LocalTask savedLocalTask = underTest.save(newLocalTask).block();
        final List<LocalTask> all = underTest.findAllByDataLike(savedLocalTask.getTimestamp()).collectList().block();
        //Then
        assertThat(all).isNotNull();
        assertThat(all.size()).isEqualTo(1);
        all.forEach(System.out::println);
    }

    @Test
    void itShouldDeleteLocalTask() {
        //Given
        final LocalTask newLocalTask = getNewLocalTask();
        //When
        underTest.delete(newLocalTask).block();
        //Then
        final Optional<LocalTask> optionalLocalTask = underTest.findById(newLocalTask.getId()).blockOptional();
        assertThat(optionalLocalTask.isEmpty()).isTrue();
    }

}