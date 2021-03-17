package com.crud.tasks.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MailTest {
    @Test
    void testMailNew(){
        //Given
        Mail mail = new Mail.MailBuilder()
                .mailTo("to someone")
                .message("message")
                .subject("subject")
                .toCc("first")
                .toCc("second")
                .toCc("third")
                .build();
        //When

        //Then

    }

}