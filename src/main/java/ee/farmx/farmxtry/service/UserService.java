package ee.farmx.farmxtry.service;

import ee.farmx.farmxtry.model.User;
import ee.farmx.farmxtry.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<User> getAllUsers(){

        List<User> users = new ArrayList<>();
        userRepository.findAll()
                .forEach(users::add);
        return users;

    }

    public User getUser(String username){

        return userRepository.findByUsername(username).orElseThrow();
    }

    public void addUser(User user) {

        userRepository.save(user);
    }

    public void updateUser(String username, User user) {
        userRepository.save(user);
    }


    public void deleteUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        userRepository.delete(user);
    }



}
