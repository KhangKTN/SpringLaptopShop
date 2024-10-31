package vn.khangktn.laptopshop.controller.admin;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import vn.khangktn.laptopshop.domain.User;
import vn.khangktn.laptopshop.service.RoleService;
import vn.khangktn.laptopshop.service.UploadService;
import vn.khangktn.laptopshop.service.UserService;


@Controller
@RequestMapping("/admin/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    UploadService uploadService;
    @Autowired
    RoleService roleService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("")
    public String getUserPage(Model model){
        model.addAttribute("list", userService.getAllUsers());
        return "admin/user/list";
    }

    @GetMapping("{id}")
    public String getUserDetail(Model model, @PathVariable Long id){
        model.addAttribute("user", userService.findUserById(id));
        return "admin/user/detail";
    }

    @PostMapping("create")
    public String createUser(@Valid @ModelAttribute("user") User entity,
        @RequestParam("avatarFile") MultipartFile file,
        BindingResult bindingResult
    ) {
        if(bindingResult.hasErrors()){
            return "admin/user/save";
        }
        userService.saveUser(entity, Optional.of(file));
        return "redirect:/admin/user";
    }

    @GetMapping("create")
    public String getAllUser(Model model) {
        model.addAttribute("user", new User());
        return "admin/user/save";
    }

    @GetMapping("update/{id}")
    public String getUserUpdate(Model model, @PathVariable Long id, @ModelAttribute("message") final String message) {
        model.addAttribute("user", userService.findUserById(id));
        model.addAttribute("message", message);
        return "admin/user/update";
    }

    @PostMapping("update")
    public String updateUser(@ModelAttribute("user") User entity, RedirectAttributes ra) {
        User userUpdate = userService.updateUser(entity);
        if(userUpdate != null) ra.addFlashAttribute("message", "Update information successfully!");
        return "redirect:/admin/user/update/" + entity.getId();
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/user";
    }
}
