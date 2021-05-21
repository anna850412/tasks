package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {
    @Autowired
    private AdminConfig adminConfig;
    @Autowired
    private CompanyInfo companyInfo;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://anna850412.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("preview", "Trello Card added");
        context.setVariable("goodbye", "Best Regards");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("company_name", companyInfo.getCompanyName());
        context.setVariable("company_address", companyInfo.getCompanyAddress());
        context.setVariable("company_email", companyInfo.getCompanyEmail());
        context.setVariable("company_phone", companyInfo.getCompanyPhone());

        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}
