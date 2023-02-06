package be.technifutur.java.timairport.service.impl;

import be.technifutur.java.timairport.exceptions.FormValidationException;
import be.technifutur.java.timairport.model.entity.User;
import be.technifutur.java.timairport.model.form.RegistrationForm;
import be.technifutur.java.timairport.repository.UserRepository;
import be.technifutur.java.timairport.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    public UserDetailsServiceImpl(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow( () -> new UsernameNotFoundException("Bad Credentials"));

    }


    @Override
    public void register(RegistrationForm form) {

        if ( form.getPassword().equals( form.getConfirmPswd() ) )
            throw new FormValidationException("password and confirmPswd should be equal");

        if ( userRepository.existsByUserName((form.getUsername())))
            throw new FormValidationException("this username already exists");

        User user = form.toEntity();
        user.setPassword( encoder.encode(user.getPassword()) );

        userRepository.save( user );

    }
}
