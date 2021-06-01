package com.example.iexec.test.service;

import com.example.iexec.test.model.BlockchainTask;
import com.example.iexec.test.repository.BlockchainTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Uint;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.response.PollingTransactionReceiptProcessor;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Collections;

/**
 * Blockchain Task Service, interacts with the BlockchainTask collection and the TaskCount smart contract
 */
@Service
public class BlockchainTaskService {

    @Autowired
    BlockchainTaskRepository blockchainTaskRepository;

    @Autowired
    Web3Service web3Service;

    @Value("${eth.contact.methods.taskCount}")
    private String taskCountMethod;

    @Value("${eth.contact.methods.newTask}")
    private String newTaskMethod;

    @Value("${eth.contract.address}")
    private String contractAddress;

    final int GAS_LIMIT = 29000;

    /**
     * Calls The Task Count Method of the BlockchainTask contract
     * and returns the current localtask count
     * @return localTaskCount
     */
    public int getBlockchainTaskCount() throws IOException {
        final Web3j web3 = web3Service.getWeb3();
        final Function taskCount = new Function(taskCountMethod,
                Collections.emptyList(),
                Collections.singletonList(new TypeReference<Uint>() {
                }));
        String encodedTaskCount = FunctionEncoder.encode(taskCount);
        final String returnValue = web3.ethCall(
                Transaction.createEthCallTransaction(
                        web3Service.getAccounts().get(0),
                        contractAddress,
                        encodedTaskCount),
                DefaultBlockParameterName.LATEST)
                .send().getValue();

        final String intValue = returnValue.substring(2);

        if(!intValue.equals("")){
            return Integer.parseInt(intValue);
        } else return -1;
    }


    /**
     * Calls the New Task Method of the TaskCount contract
     *
     * @return receipt : The Transaction receipt
     */
    public BlockchainTask createNewTask() throws IOException, TransactionException {
        final Web3j web3 = web3Service.getWeb3();
        final String from = web3Service.getAccounts().get(0);
        final Function newTask = new Function(newTaskMethod,
                Collections.emptyList(),
                Collections.emptyList());
        String encodedNewTask = FunctionEncoder.encode(newTask);
        final BigInteger nonce = web3.ethGetTransactionCount(from, DefaultBlockParameterName.LATEST).send().getTransactionCount();
        final String txHash = web3.ethSendTransaction(
                Transaction.createFunctionCallTransaction(
                        from,
                        nonce,
                        DefaultGasProvider.GAS_PRICE,
                        BigInteger.valueOf(GAS_LIMIT),
                        contractAddress,
                        BigInteger.ZERO,
                        encodedNewTask
                )
        ).send().getTransactionHash();
        final TransactionReceipt receipt = new PollingTransactionReceiptProcessor(web3,
                TransactionManager.DEFAULT_POLLING_FREQUENCY,
                TransactionManager.DEFAULT_POLLING_ATTEMPTS_PER_TX_HASH)
                .waitForTransactionReceipt(txHash);

        if(receipt.isStatusOK()){
            final String data = "Processed BlockchainTask at block " + receipt.getBlockNumber();
            final BlockchainTask blockchainTask = new BlockchainTask(receipt.getTransactionHash(), data);
            return blockchainTaskRepository.save(blockchainTask).block();
        } else return null;
    }
}
