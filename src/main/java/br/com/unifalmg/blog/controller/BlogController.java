package br.com.unifalmg.blog.controller;

import br.com.unifalmg.blog.controller.request.UserRequest;
import br.com.unifalmg.blog.entity.User;
import br.com.unifalmg.blog.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Log4j2
@AllArgsConstructor
public class BlogController {

    private final UserService service;

    @GetMapping("/")
    public String getHome() {
        return "home";
    }

    @GetMapping("/users")
    public String user(Model model) {
        List<User> users = service.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/user")
    public String user(User user) {

        log.info("Entrou no cadastro de usuário");
        return "newuser";
    }

    @PostMapping("/user")
    public String newUser(@ModelAttribute("user") User user) {
        // TODO: Add the new user
        // service.add || service.save
        log.info("Entrou no cadastro de usuário");
        User addUser = service.add(user);
        return "redirect:/user/" + addUser.getId();
    }

    @GetMapping("/user/{id}")
    public String showUser(@PathVariable("id") Integer id,
        Model model) {
        User user = service.findById(id);
        model.addAttribute("user", user);
        return "showuser";
    }

    @RequestMapping(value = "/user/{id}/edit", method = {RequestMethod.POST, RequestMethod.GET})
    public String editUser(@PathVariable("id") Integer id, @ModelAttribute("user") User updatedUser) {
        // Recupere o usuário existente do serviço
        User existingUser = service.findById(id);

        // Crie um objeto UserRequest com os dados do formulário
        UserRequest userRequest = new UserRequest();
        userRequest.setName(updatedUser.getName());
        userRequest.setUsername(updatedUser.getUsername());
        userRequest.setEmail(updatedUser.getEmail());
        userRequest.setPhone(updatedUser.getPhone());
        userRequest.setWebsite(updatedUser.getWebsite());

        // Chame o método de edição do serviço com ambos os argumentos
        User editedUser = service.edit(existingUser, userRequest);

        // Redirecione para a página do usuário editado
        return "redirect:/user/" + editedUser.getId();
    }

}
