package webapi;

import com.example.neurodiagnosis.application.service.database.DatabaseContextTests;
import com.example.neurodiagnosis.application.service.user.JwtService;
import com.example.neurodiagnosis.infrastructure.repositories.UserRepository;
import com.example.neurodiagnosis.infrastructure.seed.UsersFactory;
import com.example.neurodiagnosis.webapi.filters.AuthorizationFilter;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.HttpHeaders;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

public class AuthorizationFilterTest {

    public static final String JWT_TOKEN_VALID_WITH_BEARER_PREFIX = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJyZWFjdEZlVXJsIiwiaXNzIjoiamF2YUFwcHBVcmwiLCJ1c2VyRW1haWwiOiJ6ZXJvNjMwNTVAZ21haWwuY29tIiwidXNlck5hbWUiOiJuaWN1MTk5OTkiLCJ1c2VySWQiOiI0MTc5NDQzMy04ZWY5LTQ2ODgtYTYwNi00MTMwMWU5MzU0NmMiLCJqdGkiOiJiNTcxMTJkOS0yNGE2LTQzNDEtYmFmZS1kZjVmNmI3MzdkOTIifQ.2Yhp8KgxTq4yIifG0vLKcVn7LhFjGDt2WhgqSlHmGBs";
    public static final String JWT_TOKEN_VALID_WITHOUT_PREFIX = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJyZWFjdEZlVXJsIiwiaXNzIjoiamF2YUFwcHBVcmwiLCJ1c2VyRW1haWwiOiJ6ZXJvNjMwNTVAZ21haWwuY29tIiwidXNlck5hbWUiOiJuaWN1MTk5OTkiLCJ1c2VySWQiOiI0MTc5NDQzMy04ZWY5LTQ2ODgtYTYwNi00MTMwMWU5MzU0NmMiLCJqdGkiOiJiNTcxMTJkOS0yNGE2LTQzNDEtYmFmZS1kZjVmNmI3MzdkOTIifQ.2Yhp8KgxTq4yIifG0vLKcVn7LhFjGDt2WhgqSlHmGBs";
    public static final String JWT_TOKEN_INVALID_WITHOUT_PREFIX = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJyZWFjdEZlVXJsIiwiaXNzIjoiamF2YUFwcHBVcmwiLCJ1c2VyRW1haWwiOiJ6ZXJvNjMwNTVAZ21haWwuY29tIiwidXNlck5hbWUiOiJuaWN1MTk5OTkiLCJ1c2VySWQiOiI0MTc5NDQzMy04ZWY5LTQ2ODgtYTYwNi00MTMwMWU5MzU0NmMiLCJqdGkiOiJiNTcxMTJkOS0yNGE2LTQzNDEtYmFmZS1kZjVmNmI3MzdkOTIifQ.2Yhp8KgxTq4yIifG0vLKcVn7LhFjGDt2CCCCSlHmGBs";

    public AuthorizationFilterTest() {

    }

    @BeforeEach
    void setUp() {
        new UsersFactory(new UserRepository(new DatabaseContextTests())).seedTestData();
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    public void givenAuthorizationFilter__whenRequestWithInvalidJwtInAuthorizationHeaderIsMade__ShouldDenyAccess() throws IOException {
        //Arrange
        ContainerRequestContext crc = Mockito
                .mock(ContainerRequestContext.class);

        AuthorizationFilter filter = new AuthorizationFilter();

        //ACT
        var authorizedUser = filter
                .validateAuthorization(JWT_TOKEN_INVALID_WITHOUT_PREFIX);

        //ASSERT
        assertTrue(authorizedUser.isEmpty());
    }


    @Test
    public void givenAuthorizationFilter__whenRequestWithValidJwtInAuthorizationHeaderIsMade__ShouldAuthorizeAccess() throws IOException {
        //Arrange
        ContainerRequestContext crc = Mockito
                .mock(ContainerRequestContext.class);

        var userInSeed = new UserRepository(new DatabaseContextTests()).findByEmail("emailexistent1@gmail.com");

        var jwt = JwtService.createJWT(userInSeed.get());

        AuthorizationFilter filter = new AuthorizationFilter();

        //ACT
        var authorizedUser = filter
                .validateAuthorization(jwt);

        //ASSERT
        assertTrue(authorizedUser.isPresent());
    }



    @Test
    public void givenAuthorizationFilter__whenFilterMethodIsCalledWithValidJwtHeader__ShouldProceedToNextFilter() throws IOException {
        //Arrange
        ContainerRequestContext crc = Mockito
                .mock(ContainerRequestContext.class);

        var userInSeed = new UserRepository(new DatabaseContextTests()).findByEmail("emailexistent1@gmail.com");
        var jwt = JwtService.createJWT(userInSeed.get());


        Mockito.when(crc.getHeaderString(HttpHeaders.AUTHORIZATION))
                .thenReturn(String.format("Bearer %s", jwt));
        Mockito.doNothing()
                .when(crc).setSecurityContext(Matchers.any());


        AuthorizationFilter filter = new AuthorizationFilter();

        //ACT & ASSERT
        assertDoesNotThrow(() -> filter.filter(crc));
    }


    @Test
    public void givenAuthorizationFilter__whenIsTokenBasedAuthIsCalledWithABearerToken__thenShouldReturnTrue() {

        //ARRANGE

        var authorizationFilter = new AuthorizationFilter();


        //ACT & ASSERT
        assertTrue(authorizationFilter.isTokenBasedAuthentication(JWT_TOKEN_VALID_WITH_BEARER_PREFIX));
    }


    @Test
    public void givenAuthorizationFilter__whenIsTokenBasedAuthIsCalledWithNull__thenShouldReturnFalse() {

        //ARRANGE

        var authorizationFilter = new AuthorizationFilter();


        //ACT & ASSERT
        assertFalse(authorizationFilter.isTokenBasedAuthentication(null));
    }
}
