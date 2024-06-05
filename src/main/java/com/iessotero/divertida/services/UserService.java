package com.iessotero.divertida.services;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.iessotero.divertida.model.User;
import com.iessotero.divertida.repository.IUserRepository;

/**
 * Servicio para realizar operaciones relacionadas con usuarios.
 */
@Service
public class UserService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IUserRepository userRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    /**
     * Guarda un nuevo usuario en la base de datos.
     *
     * @param user el usuario a guardar.
     * @return el usuario guardado.
     */
    public User saveUser(User user) {
       // user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /**
     * Busca un usuario por su dirección de correo electrónico.
     *
     * @param email la dirección de correo electrónico del usuario a buscar.
     * @return un objeto Optional que contiene el usuario correspondiente, si existe.
     */
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Busca un usuario por su ID.
     *
     * @param id el ID del usuario a buscar.
     * @return un objeto Optional que contiene el usuario correspondiente, si existe.
     */
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Genera un token JWT para el usuario dado.
     *
     * @param user el usuario para el que se generará el token.
     * @return el token JWT generado.
     */
    public String generateTokenForUser(User user) {
        return jwtService.generateToken(user, generateExtraClaims(user));
    }

    /**
     * Genera reclamaciones adicionales para incluir en el token JWT.
     *
     * @param user el usuario para el que se generarán las reclamaciones adicionales.
     * @return un mapa que contiene las reclamaciones adicionales de el email y el isAdmin.
     */
    private Map<String, Object> generateExtraClaims(User user) {
        return Map.of("name", user.getEmail(), "isAdmin", user.getAdmin());
    }
}
