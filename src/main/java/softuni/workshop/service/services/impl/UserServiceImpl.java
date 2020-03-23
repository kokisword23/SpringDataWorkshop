package softuni.workshop.service.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import softuni.workshop.data.entities.Role;
import softuni.workshop.data.entities.User;
import softuni.workshop.data.repositories.UserRepository;
import softuni.workshop.service.models.UserServiceModel;
import softuni.workshop.service.services.RoleService;
import softuni.workshop.service.services.UserService;
import softuni.workshop.web.models.UserRegisterModel;

import java.util.LinkedHashSet;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserServiceModel registerUser(UserRegisterModel userRegisterModel) {
        User user = this.modelMapper.map(userRegisterModel, User.class);

        if (this.userRepository.count() == 0) {
            this.roleService.seedRolesInDb();
            user.setAuthorities(this.roleService.findAllRoles().stream()
                    .map(r -> this.modelMapper.map(r, Role.class)).collect(Collectors.toSet()));
        } else {
            user.setAuthorities(new LinkedHashSet<>());
            user.getAuthorities().add(this.modelMapper.map(this.roleService.findByAuthority("USER"),Role.class));
        }

        user.setPassword(this.bCryptPasswordEncoder.encode(userRegisterModel.getPassword()));
        return this.modelMapper.map(this.userRepository.save(user), UserServiceModel.class);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(s);
    }
}
