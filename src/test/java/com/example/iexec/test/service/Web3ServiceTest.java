package com.example.iexec.test.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Web3ServiceTest {

    @Autowired
    Web3Service underTest;

    @Test
    void itShouldGetAllAccounts() throws IOException {
        //Given
        //When
        final List<String> accounts = underTest.getAccounts();
        //Then
        assertThat(accounts).isNotEmpty();
        accounts.forEach(System.out::println);
    }

}