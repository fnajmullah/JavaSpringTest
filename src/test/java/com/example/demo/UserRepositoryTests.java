package com.example.demo;

import com.example.demo.User.User;
import com.example.demo.User.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {
    @Autowired private UserRepository repo;

    @Test
    public void testAddNew(){
        User u =new User();
        u.setFirstname("Fridoon");
        u.setLastname("Najexcvbeb");
        u.setEmail("fnajmucvbllah@gmail.com");
        u.setPassword("Bbc@12345");
        User savedUser=repo.save(u);
        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll(){
        Iterable<User> users = repo.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);
        for (User user: users)
        {
            System.out.println(user);
        }
    }

    @Test
    public void testUpdate(){
        Integer userId=1;
        Optional<User> optionalUser = repo.findById(userId);
        User user=optionalUser.get();
        user.setPassword("asdflkj");
        repo.save(user);

        User updateUser= repo.findById(userId).get();
    }
}
