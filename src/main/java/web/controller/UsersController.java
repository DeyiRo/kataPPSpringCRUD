package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserServiceI;

@Controller
@RequestMapping(value = "/users")
public class UsersController {

    @Autowired
    private UserServiceI userServiceI;

    @GetMapping()
    public String getUsersList(Model model) {
        model.addAttribute("usersList", userServiceI.getAllUsers());
        return "users";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("user") User user) {
        userServiceI.saveUser(user);
        return "redirect:users";
    }

    @GetMapping("/edit")
    public String editUser(Model model, @RequestParam("id") long id) {
        User user = userServiceI.findUserById(id);
        model.addAttribute("editUser", user);
        return "edit";
    }

    @PostMapping("/edit-user")
    public String updateUser(@ModelAttribute("editUser") User editUser) {
        userServiceI.updateUserById(editUser.getId(), editUser);
        return "redirect:/users";
    }

    @GetMapping("/delete")
    public String deleteUser(Model model, @RequestParam("id") long id) {
        User user = userServiceI.findUserById(id);
        model.addAttribute("deleteUser", user);
        return "delete";
    }

    @PostMapping("/delete-user")
    public String removeUser(@ModelAttribute("deleteUser") User deleteUser) {
        userServiceI.deleteUserById(deleteUser.getId());
        return "redirect:/users";
    }
}