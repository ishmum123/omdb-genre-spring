package com.newscred.omdb.genre.config;

import com.newscred.omdb.genre.helpers.dataclass.UserShortSummary;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Configuration
@Getter
public class ServiceConfiguration {

    @Value("${service.properties.short-code:STR}")
    private String shortCode;

    @Value("${service.user.one.name}")
    private String userOneName;

    @Value("${service.user.one.email}")
    private String userOneEmail;

    @Value("${service.user.one.password}")
    private String userOnePassword;

    @Value("${service.user.two.name}")
    private String userTwoName;

    @Value("${service.user.two.email}")
    private String userTwoEmail;

    @Value("${service.user.two.password}")
    private String userTwoPassword;

    @Value("${service.user.three.name}")
    private String userThreeName;

    @Value("${service.user.three.email}")
    private String userThreeEmail;

    @Value("${service.user.three.password}")
    private String userThreePassword;

    private UserShortSummary userOneShortSummary;
    private UserShortSummary userTwoShortSummary;
    private UserShortSummary userThreeShortSummary;

    private List<UserShortSummary> users;

    @PostConstruct
    public void init() {
        userOneShortSummary = new UserShortSummary(userOneName, userOneEmail, userOnePassword);
        userTwoShortSummary = new UserShortSummary(userTwoName, userTwoEmail, userTwoPassword);
        userThreeShortSummary = new UserShortSummary(userThreeName, userThreeEmail, userThreePassword);
        users = Arrays.asList(userOneShortSummary, userThreeShortSummary, userThreeShortSummary);
    }
}
