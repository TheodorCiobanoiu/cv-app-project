package com.dbproject.cvapp.service;

import com.dbproject.cvapp.exception.AdminDeleteException;
import com.dbproject.cvapp.exception.NoAuthorizationException;
import com.dbproject.cvapp.exception.NoUserException;
import com.dbproject.cvapp.model.MyUser;
import com.dbproject.cvapp.model.Roles;
import com.dbproject.cvapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MyUserService {

    private final UserRepository userRepository;

    public List<MyUser> getAllUsers(){
        return userRepository.findAll();
    }

    public String deleteUserById(Integer id, Integer requesterId) throws NoUserException, AdminDeleteException, NoAuthorizationException {
        Optional<MyUser> optionalUser = userRepository.findById(id);
        Optional<MyUser> optionalUserPotentialAdmin = userRepository.findById(requesterId);

        if(optionalUser.isEmpty() || optionalUserPotentialAdmin.isEmpty())
            throw new NoUserException(id);

        List<Roles> potentialAdminUserRoles = optionalUserPotentialAdmin.get().getRoles().stream().toList();
        Roles roleAdminPotential = new Roles();

        if(potentialAdminUserRoles.stream().findFirst().isPresent())
            roleAdminPotential = potentialAdminUserRoles.stream().findFirst().get();

        if(!roleAdminPotential.getName().toString().equals("ROLE_ADMIN"))
            throw new NoAuthorizationException();

        List<Roles> userRoles = optionalUser.get().getRoles().stream().toList();
        Roles role = new Roles();

        if(userRoles.stream().findFirst().isPresent())
            role = userRoles.stream().findFirst().get();

        if(role.getName().toString().equals("ROLE_ADMIN"))
            throw new AdminDeleteException();


        userRepository.deleteById(id);
        return "The user with username: " + optionalUser.get().getUsername() + "has been deleted";

    }

    public MyUser modifyUser(MyUser user){
        return userRepository.save(user);
    }

    public MyUser getUserById(Integer userId) throws NoUserException {
        Optional<MyUser> optionalMyUser = userRepository.findById(userId);
        if(optionalMyUser.isEmpty())
            throw new NoUserException(userId);
        return optionalMyUser.get();
    }

    public MyUser getUserByUsername(String username) throws NoUserException {
        Optional<MyUser> optionalMyUser = userRepository.findUserByUsername(username);
        if(optionalMyUser.isEmpty())
            throw new NoUserException(username);
        return optionalMyUser.get();
    }


}
