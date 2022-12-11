package services;

import com.example.neurodiagnosis.application.service.database.DatabaseContextTests;
import com.example.neurodiagnosis.application.service.question.QuestionService;
import com.example.neurodiagnosis.application.service.test.TestGeneratorService;
import com.example.neurodiagnosis.infrastructure.repositories.QuestionRepository;
import com.example.neurodiagnosis.infrastructure.repositories.UserRepository;
import com.example.neurodiagnosis.infrastructure.seed.QuestionsFactory;
import com.example.neurodiagnosis.infrastructure.seed.UsersFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestGeneratorServiceTests {


    public TestGeneratorServiceTests() {

    }


    @BeforeEach
    public void seedAllQuestions() {
        var questionsFactory = new QuestionsFactory(new QuestionService(new QuestionRepository(new DatabaseContextTests())));

        questionsFactory.seedQuestions();


        //ensure users exists (are sown [as in seeded])
        new UsersFactory(new UserRepository(new DatabaseContextTests()))
                .seedTestData();
    }

    @Test
    public void givenTestGeneratorService__whenGenerateTestCalled__thenShouldReturnAValidTest() {
        var testsService = new TestGeneratorService(new UserRepository(new DatabaseContextTests()),
                new QuestionRepository(new DatabaseContextTests()));
        var userFromSeed = new UserRepository(new DatabaseContextTests()).findByUsername("User").get();

        var test = testsService.generateTest(userFromSeed.getId());

        assertNotNull(test);
        assertTrue(test.questions.size() > 0);
    }
}
