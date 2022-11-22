package com.example.neurodiagnosis.verification.mop.monitor1;

import java.rmi.AccessException;

public class AuthenticateBeforeAccesingResources {

    public static void main(String[] args) throws AccessException {
        var resource = new TestResultsResource();

        resource.authorize("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJyZWFjdEZlVXJsIiwiaXNzIjoiamF2YUFwcHBVcmwiLCJ1c2VyRW1haWwiOiJ6ZXJvNjMwNTVAZ21haWwuY29tIiwidXNlck5hbWUiOiJuaWN1MTk5OTkiLCJ1c2VySWQiOiI0MTc5NDQzMy04ZWY5LTQ2ODgtYTYwNi00MTMwMWU5MzU0NmMiLCJqdGkiOiJiNTcxMTJkOS0yNGE2LTQzNDEtYmFmZS1kZjVmNmI3MzdkOTIifQ.2Yhp8KgxTq4yIifG0vLKcVn7LhFjGDt2WhqiSlHmGBs");

        var results = resource.getTestResults();

    }
}
