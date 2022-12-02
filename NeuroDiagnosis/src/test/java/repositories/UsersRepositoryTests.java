package repositories;

import com.example.neurodiagnosis.application.interfaces.repositories.IUserRepository;
import com.example.neurodiagnosis.application.service.database.DatabaseContextTests;
import com.example.neurodiagnosis.infrastructure.repositories.UserRepository;
import com.example.neurodiagnosis.infrastructure.seed.UsersFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UsersRepositoryTests {
    private final IUserRepository userRepo;

    public UsersRepositoryTests() {
        this.userRepo = new UserRepository(new DatabaseContextTests());
    }


    @BeforeEach
    void setUp() {

        new UsersFactory(this.userRepo).seedTestData();
    }

    @AfterEach
    void clear() {
        new UsersFactory(this.userRepo).clearData();
    }


    @Test
    public void givenUsersRepository__whenFindUserByIdWithExistentUserId__shouldReturnTheUserWithThatUserId() {
        //ARRANGE

        var userToSearch = userRepo.findByEmail("emailexistent1@gmail.com").orElseThrow();

        //ACT
        var userFound = userRepo.findById(userToSearch.getId()).orElseThrow();

        //ASSERT
        assertEquals(userFound, userFound);
    }


    @Test
    public void givenUsersRepository__whenDeleteUserWithExistentUserId__shouldDeleteUserFromDb() {
        //ARRANGE
        var userToSearch = userRepo.findByEmail("emailexistent1@gmail.com").orElseThrow();

        //ACT
        userRepo.deleteUserAccount(userToSearch.getId());

        //ASSERT
        assertTrue(userRepo.findById(userToSearch.getId()).isEmpty());
    }
}
