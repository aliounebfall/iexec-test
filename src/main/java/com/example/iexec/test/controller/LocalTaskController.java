package com.example.iexec.test.controller;

import com.example.iexec.test.model.LocalTask;
import com.example.iexec.test.service.LocalTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.ws.rs.*;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * LocalTask Controller, fetches all created tasks,
 * returns created tasks count,
 * and returns a new created LocalTask from db
 */
@Validated
@Component
@Path("api/iexec/v1/local/tasks")
public class LocalTaskController {

    @Autowired
    private LocalTaskService localTaskService;

    /**
     * @return all created tasks
     */
    @GET
    @Produces(APPLICATION_JSON)
    public List<LocalTask> fetchAllLocalTasks () {
        return localTaskService.getAllLocalTasks();
    }

    /**
     * @return the number of created tasks
     */
    @GET
    @Path("count")
    @Produces(APPLICATION_JSON)
    public int count () {
        return localTaskService.getAllLocalTasks().size();
    }

    /**
     * @param localTask the JSON object of the new task
     * @return the new created task from db
     */
    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public LocalTask createLocalTask (@RequestBody @Valid LocalTask localTask) {
        return localTaskService.updateLocalTask(localTask);
    }

}
