package com.example.iexec.test.service;

import com.example.iexec.test.model.LocalTask;
import com.example.iexec.test.repository.LocalTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Local Task Service, interacts with the LocalTask collection and the LocalTask smart contract
 */
@Service
public class LocalTaskService {

    @Autowired
    LocalTaskRepository localTaskRepository;


    public List<LocalTask> getAllLocalTasks(){
        return localTaskRepository.findAll().collectList().block();
    }

    /**
     * @param localTaskId The LocalTask id
     * @return an optional LocalTask
     */
    public Optional<LocalTask> getLocalTaskById(String localTaskId){
        return localTaskRepository.findById(localTaskId).blockOptional();
    }

    /**
     * @param timestamp The LocalTask timestamp
     * @return a list of LocalTasks
     */
    public List<LocalTask> getAllLocalTasksByTimestamp(String timestamp){
        return localTaskRepository.findAllByTimestamp(timestamp).collectList().block();
    }

    /**
     * @param data The LocalTask data
     * @return a list of LocalTasks
     */
    public List<LocalTask> getAllLocalTasksByData(String data){
        return localTaskRepository.findAllByDataLike(data).collectList().block();
    }

    /**
     * @param localTask The LocalTask to update
     * @return the updated LocalTask
     */
    public LocalTask updateLocalTask(LocalTask localTask){
        return localTaskRepository.save(localTask).block();
    }
}
