package services;

import com.example.neurodiagnosis.application.service.user.IPasswordHashGeneratorService;
import com.example.neurodiagnosis.application.service.user.JwtService;
import com.example.neurodiagnosis.application.service.user.PasswordHashGeneratorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTests {

    public JwtServiceTests() {

    }

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void givenJwtService__whenValidJwtIssuedByJwtServiceSuppliedToDecodeMethod__shouldSuccessfulyDecode() {
        //ARRANGE
        String jwtToDecode = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJyZWFjdEZlVXJsIiwiaXNzIjoiamF2YUFwcHBVcmwiLCJ1c2VyRW1haWwiOiJ6ZXJvNjMwNTVAZ21haWwuY29tIiwidXNlck5hbWUiOiJuaWN1MTk5OTkiLCJ1c2VySWQiOiI0MTc5NDQzMy04ZWY5LTQ2ODgtYTYwNi00MTMwMWU5MzU0NmMiLCJqdGkiOiJiNTcxMTJkOS0yNGE2LTQzNDEtYmFmZS1kZjVmNmI3MzdkOTIifQ.2Yhp8KgxTq4yIifG0vLKcVn7LhFjGDt2WhqiSlHmGBs";

        //ACT
        //ASSERT
        assertFalse(JwtService.decodeJWT(jwtToDecode).isEmpty());
    }

}