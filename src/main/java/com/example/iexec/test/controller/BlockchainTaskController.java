package com.example.iexec.test.controller;

import com.example.iexec.test.model.BlockchainTask;
import com.example.iexec.test.service.BlockchainTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.web3j.protocol.exceptions.TransactionException;

import javax.ws.rs.*;

import java.io.IOException;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * BlockchainTask Controller,
 * returns created tasks count on-chain,
 * increments on-chain tasks
 * and returns a new created BlockchainTask from db
 */
@Validated
@Component
@Path("api/iexec/v1/blockchain/tasks")
public class BlockchainTaskController {

    @Autowired
    private BlockchainTaskService blockchainTaskService;

    /**
     * @return the number of created tasks on-chain
     */
    @GET
    @Path("count")
    @Produces(APPLICATION_JSON)
    public int count () throws IOException {
        return blockchainTaskService.getBlockchainTaskCount();
    }


    /**
     * @return the new created BlockchainTask from db
     * @throws IOException
     * @throws TransactionException
     */
    @POST
    @Produces(APPLICATION_JSON)
    public BlockchainTask createBlockchainTask () throws IOException, TransactionException {
        return blockchainTaskService.createNewTask();
    }
}
