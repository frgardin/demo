package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class HelloControllerTest {

    @InjectMocks
    private HelloController helloController;

    @Test
    void getHello() {
        String result = helloController.getHello();
        assertEquals("Hello World!", result);
    }
}