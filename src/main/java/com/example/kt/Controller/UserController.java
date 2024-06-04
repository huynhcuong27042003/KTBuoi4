package com.example.kt.Controller;

import com.example.kt.RequestEntities.UserCreate;
import com.example.kt.RequestEntities.UserUpdate;
import com.example.kt.Service.RoleService;
import com.example.kt.Service.UserService;
import com.example.kt.model.Role;
import com.example.kt.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @GetMapping("")
    public String getUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users/user_list";
    }
    @GetMapping("/new")
    public String addUser(Model model) {
         // Assuming roleService handles role retrieval
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("user", new User());
        return "users/add_user"; // Assuming an add_user.html view exists
    }

    @PostMapping("/new")
    public String saveUser(UserCreate userCreate) {
        userService.createUser(userCreate);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String editUser(Model model, @PathVariable String id) {
        User user = userService.getUserById(id);
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("user", user);
            return "users/update_user";
    }
    @PostMapping("/saveedit")
    public String saveEditUser(UserUpdate userUpdate) {
        userService.updateUser(userUpdate);
        return "redirect:/users";
    }
    @GetMapping("delete/{id}")
    public String deleteUser(Model model, @PathVariable String id) {
        User user = userService.getUserById(id);
        userService.deleteUser(user);
        return "redirect:/users";
    }
}
