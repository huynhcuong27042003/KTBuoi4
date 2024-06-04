package com.example.kt.Controller;

import com.example.kt.RequestEntities.RoleCreate;
import com.example.kt.RequestEntities.RoleUpdate;
import org.springframework.ui.Model;
import com.example.kt.Service.RoleService;
import com.example.kt.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @GetMapping("")
    public String getRoles(Model model) {
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("roles", roles);
        return "roles/role_list";
    }
    @GetMapping("/new")
    public String AddRole(Model model) {
        model.addAttribute("role", new Role());
        return "roles/add_role";
    }
    @PostMapping("/new")
    public String saveRole(RoleCreate roleCreate) {
        roleService.createRole(roleCreate);
        return "redirect:/roles";
    }
    @GetMapping("/edit/{id}")
    public String editRole(Model model, @PathVariable String id) {
        Role role = roleService.getRoleById(id);
        model.addAttribute("role", role);
        return "roles/update_role";
    }
    @PostMapping("/saveedit")
    public String saveRoleEdit(RoleUpdate roleUpdate) {
        roleService.updateRole(roleUpdate);
        return "redirect:/roles";
    }
    @GetMapping("/delete/{id}")
    public String deleteRole(@PathVariable String id){
        roleService.deleteRole(id);
        return "redirect:/roles";
    }
}
