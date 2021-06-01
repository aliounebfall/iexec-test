package com.example.iexec.test.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.util.List;


/**
 * Provides the web3 instance to interact with the provided ethereum node
 */
@Service
public class Web3Service {

    @Value("${eth.node.url}")
    private String ETH_NODE_URL;

    @Value("${eth.node.chainid}")
    private int ETH_NODE_CHAINID;

    /**
     * @return web3 instance
     */
    public Web3j getWeb3(){
        return Web3j.build(new HttpService(ETH_NODE_URL));
    }


    /**
     * @return accounts unlocked in the provided ethereum node
     * @throws IOException
     */
    public List<String> getAccounts() throws IOException {
        return getWeb3().ethAccounts().send().getAccounts();
    }

}
