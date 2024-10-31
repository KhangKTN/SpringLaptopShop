package vn.khangktn.laptopshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import vn.khangktn.laptopshop.domain.User;
import vn.khangktn.laptopshop.domain.dto.RegisterDTO;
import vn.khangktn.laptopshop.repository.UserRepository;

@Service
public class UserService {
    @Autowired UserRepository userRepository;
    @Autowired PasswordEncoder passwordEncoder;
    @Autowired UploadService uploadService;
    @Autowired RoleService roleService;

    public User saveUser(User user, Optional<MultipartFile> file){
        if(file.isPresent()){
            user.setAvatar(uploadService.handleSaveUploadFile(file.get(), "avatar"));
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(roleService.getRolebyName("USER"));
        return userRepository.save(user);
    }

    public User updateUser(User userUpdate){
        Optional<User> userCurrent = userRepository.findById(userUpdate.getId());
        if(userCurrent.isPresent()) userUpdate.setEmail(userCurrent.get().getEmail());
        return userRepository.save(userUpdate);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email).orElse(null);
    }

    public User findUserById(Long id){
        return userRepository.findById(id).get();
    }

    public void deleteUser(Long id){
        User userDelete = userRepository.findById(id).get();
        userRepository.delete(userDelete);
    }

    public User registerDTOToUser(RegisterDTO registerDTO){
        User user = new User();
        user.setFullName(registerDTO.getFirstName() + " " + registerDTO.getLastName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());

        return user;
    }
}
