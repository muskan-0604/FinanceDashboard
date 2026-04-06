package com.Finance.Dashboard.Service;


import com.Finance.Dashboard.Exception.AccessDeniedException;
import com.Finance.Dashboard.Exception.ResourceNotFoundException;
import com.Finance.Dashboard.Model.Enum.Role;
import com.Finance.Dashboard.Model.Enum.Status;
import com.Finance.Dashboard.Model.User;
import com.Finance.Dashboard.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    public List<User> getAllUsers(Role role){
        if(role != Role.ADMIN){
           throw new AccessDeniedException("You don't have permission to access this");
        }
        return userRepository.findAll();
    }

    public User getById(Long id,Role role) {
        if(role != Role.ADMIN && role != Role.ANALYST && role != Role.VIEWER){
            throw new AccessDeniedException("You don't have permission to access this");
        }

        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    public User createUser(User user,Role role){
        if(role != Role.ADMIN){
            throw new AccessDeniedException("Only ADMIN can create users");
        }
        User data = userRepository.save(user);
        return data;
    }

    public User userStatus(Long id ,Status status,Role role){
        if(role != Role.ADMIN){
            throw new AccessDeniedException("Only ADMIN can change status");
        }

        User data = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        data.setStatus(status);
        return userRepository.save(data);
    }

    public User findByEmail(String email,Role role){
        if(role != Role.ADMIN){
            throw new AccessDeniedException("You don't have permission to access this");
        }
        User user = userRepository.findByEmail(email);

        if(user == null){
            throw new ResourceNotFoundException("User not found with email: " + email);
        }

        return user;
    }

    public void deleteUser(Long id,Role role){
        if(role != Role.ADMIN){
            throw new AccessDeniedException("Only ADMIN can delete users");
        }
        if(!userRepository.existsById(id)){
            throw new ResourceNotFoundException("User not found with id: " + id);
        }

        userRepository.deleteById(id);
    }

    public User updateUser(Long id,User userData ,Role role ){
        if(role != Role.ADMIN){
            throw new AccessDeniedException("Only ADMIN can update users ");
        }

        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isEmpty()){
            throw new  ResourceNotFoundException("Record not found with id: " + id);
        }
        User user = userOptional.get();
       if(userData.getName()!=null){
           user.setName(userData.getName());
       }
        if(userData.getEmail()!=null){
            user.setEmail(userData.getEmail());
        }
        if(userData.getPassword()!=null){
            user.setPassword(userData.getPassword());
        }

        return userRepository.save(user);

    }


}
