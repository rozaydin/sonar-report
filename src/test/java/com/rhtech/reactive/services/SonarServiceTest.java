package com.rhtech.reactive.services;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.http.HttpClient;

public class SonarServiceTest {

    private SonarService sonarService;

    @BeforeTest
    public void init() {
        HttpClient httpClient = HttpClient.newHttpClient();
        this.sonarService = new SonarService(httpClient, "http://localhost:9000", "2b9e4113fab2792bb0606097ae039850d55d6cdb", "");
    }

    @Test
    public void should_retrieve_project_data_from_sonarqube_server() {
        this.sonarService.retrieveProjectAnalysis("com.rhtech:reactive");
    }

}
