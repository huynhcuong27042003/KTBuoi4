package com.example.kt.Service;

import com.example.kt.Repository.UserRepository;
import com.example.kt.model.Role;
import com.example.kt.model.User;
import com.example.kt.RequestEntities.UserCreate;
import com.example.kt.RequestEntities.UserUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService; // To fetch role by ID

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        return userRepository.findById(id).orElseThrow(
                () ->{throw  new RuntimeException("User not found with id: " + id);
                }
        );
    }

    public User createUser(UserCreate userCreate) {
        try {
            User user = new User();
            user.setUsername(userCreate.getUsername());
            user.setPassword(userCreate.getPassword());
            user.setFirstName(userCreate.getFirstName());
            user.setLastName(userCreate.getLastName());
            user.setEmail(userCreate.getEmail());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            user.setDateOfBirth(dateFormat.parse(userCreate.getDateOfBirth()));
            user.setIsDelete(false);
            Role role = roleService.getRoleById(userCreate.getRole().getRole_id());
            user.setRole(role);  // Set only the Role object
            userRepository.save(user);
            return user;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public User updateUser(UserUpdate userUpdate) {
        try {
            User user = getUserById(userUpdate.getId());
            user.setUsername(userUpdate.getUsername());
            user.setFirstName(userUpdate.getFirstName());
            user.setLastName(userUpdate.getLastName());
            user.setEmail(userUpdate.getEmail());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            user.setDateOfBirth(dateFormat.parse(userUpdate.getDateOfBirth()));
            user.setRole(userUpdate.getRole());
            userRepository.save(user);
            return user;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public User deleteUser(User userDelete) {
        try {
            User user = getUserById(userDelete.getId());
            user.setIsDelete(true);
            userRepository.save(user);
            return user;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
