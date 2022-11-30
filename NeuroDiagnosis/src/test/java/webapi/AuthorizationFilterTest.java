package webapi;

import com.example.neurodiagnosis.webapi.filters.AuthorizationFilter;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.HttpHeaders;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.verify;

public class AuthorizationFilterTest {

    public AuthorizationFilterTest() {

    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    public void givenAuthorizationFilter__whenRequestWithoutProperJwtInAuthorizationHeaderIsMade__ShouldDenyAccess() throws IOException {
        //Arrange
        ContainerRequestContext crc = Mockito
                .mock(ContainerRequestContext.class);

        Mockito.when(crc.getHeaderString(HttpHeaders.AUTHORIZATION))
                .thenReturn("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJyZWFjdEZlVXJsIiwiaXNzIjoiamF2YUFwcHBVcmwiLCJ1c2VyRW1haWwiOiJ6ZXJvNjMwNTVAZ21haWwuY29tIiwidXNlck5hbWUiOiJuaWN1MTk5OTkiLCJ1c2VySWQiOiI0MTc5NDQzMy04ZWY5LTQ2ODgtYTYwNi00MTMwMWU5MzU0NmMiLCJqdGkiOiJiNTcxMTJkOS0yNGE2LTQzNDEtYmFmZS1kZjVmNmI3MzdkOTIifQ.2Yhp8KgxTq4yIifG0vLKcVn7LhFjGDt2WhgqSlHmGBs");

        AuthorizationFilter filter = new AuthorizationFilter();

        //ACT
//        filter.filter(crc);

        //ASSERT
//        verify(crc).abortWith(Matchers.any());
    }
}
