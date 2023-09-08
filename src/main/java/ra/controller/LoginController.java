package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ra.model.entity.User;

import javax.validation.Valid;

@Controller
public class LoginController {
    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("user", new User());
        return "/index";
    }

    @PostMapping("/")
    public String post(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {
        new User().validate(user,bindingResult);
        if (bindingResult.hasFieldErrors()){
            return "/index";
        }
        model.addAttribute("successful", "Register successfully!");
        model.addAttribute("user", user);
        return "/result";
    }
}
