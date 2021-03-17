package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class Mail {
    private final String mailTo;
    private final String subject;
    private final String message;
    private List<String> toCcs = new ArrayList<>();


    static class MailBuilder {
        private String mailTo;
        private String subject;
        private String message;
        private List<String> toCcs = new ArrayList<>();

        public MailBuilder mailTo(String mailTo) {
            this.mailTo = mailTo;
            return this;
        }

        public MailBuilder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public MailBuilder message(String message) {
            this.message = message;
            return this;
        }

        public MailBuilder toCc(String toCc) {
            toCcs.add(toCc);
            return this;
        }

        public Mail build() {
            return new Mail(mailTo, subject, message, toCcs);
        }
//        public MailBuilder(final String mailTo, final String subject, final String message, List<String> toCcs){
//            this.mailTo = mailTo;
//            this.subject = subject;
//            this.message = message;
//            this.toCcs = new ArrayList<>(toCcs);
//        }
    }
}
