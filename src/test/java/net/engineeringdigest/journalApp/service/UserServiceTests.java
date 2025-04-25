package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

//    @Disabled // - to skip any test
    @Test
    public void testFindByUsername(){
        assertNotNull(userRepository.findByUsername("ram"));
    }

    @ParameterizedTest
    @CsvSource({
            "ram",
            "shyam",
            "peter"
    })
    public void testFindByUsername(String username){
        assertNotNull(userRepository.findByUsername(username));
    }
}
