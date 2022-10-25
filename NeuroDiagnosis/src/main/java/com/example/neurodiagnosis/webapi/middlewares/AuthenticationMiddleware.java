package com.example.neurodiagnosis.webapi.middlewares;


import com.sun.net.httpserver.Authenticator;
import com.sun.net.httpserver.HttpExchange;

public class AuthenticationMiddleware extends Authenticator {

    @Override
    public Result authenticate(HttpExchange exch) {
        return null;
    }
}
