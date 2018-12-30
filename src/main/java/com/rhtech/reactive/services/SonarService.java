package com.rhtech.reactive.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

public class SonarService {

    // logger
    private static final Logger log = LoggerFactory.getLogger(SonarService.class);

    private final HttpClient httpClient;
    private final String baseUrl;
    private final String authorizationHeaderValue;

    public SonarService(HttpClient httpClient, String baseUrl, String user, String password) {
        this.httpClient = httpClient;
        this.baseUrl = baseUrl;
        this.authorizationHeaderValue = createAuthorizationBasic(user, password);
    }

    public void retrieveProjectAnalysis(final String projectKey) {

        try {
            HttpResponse<String> httpResponse = httpClient.send(createProjectAnalysisRequest(projectKey), HttpResponse.BodyHandlers.ofString());
            log.info("We succeeded in retrieving the response from endpoint body: {}", httpResponse.body());

        } catch (Exception exc) {
            log.error("Failed to retrieve ProjectAnalysis!", exc);
        }
    }

    private HttpRequest createProjectAnalysisRequest(final String projectKey) {

        try {

            URI uri = new URI(baseUrl + "/api/project_analyses/search?project=" + projectKey);


            return HttpRequest
                    .newBuilder()
                    .GET()
                    .uri(uri)
                    .setHeader("Authorization", authorizationHeaderValue)
                    .version(HttpClient.Version.HTTP_1_1)
                    .build();

        } catch (Exception exc) {
            log.error("Failed to create uri", exc);
            throw new RuntimeException(exc);
        }

    }

    private String createAuthorizationBasic(String user, String password) {

        String basicAuthorizationHeaderValue = user + ":" + password;
        return new String(Base64.getEncoder().encode(basicAuthorizationHeaderValue.getBytes()));
    }


}
