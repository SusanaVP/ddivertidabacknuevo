package com.iessotero.divertida.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.iessotero.divertida.model.User;

/**
 * Repositorio para acceder a la base de datos de usuarios.
 */
@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

	/**
	 * Busca un usuario por su dirección de correo electrónico.
	 *
	 * @param email la dirección de correo electrónico del usuario.
	 * @return un objeto Optional que contiene el usuario correspondiente, si
	 *         existe.
	 */
	@Query("SELECT u FROM User u WHERE u.email = :email")
	Optional<User> findByEmail(String email);

	/**
	 * Verifica si existe un usuario con la combinación de correo electrónico y
	 * contraseña dada.
	 *
	 * @param email    la dirección de correo electrónico del usuario.
	 * @param password la contraseña del usuario.
	 * @return el ID del usuario si la combinación es válida, o null si no es
	 *         válida.
	 */
	@Query("SELECT u.id FROM User u WHERE u.email = :email AND u.password = :password")
	Long existsEmailPaswword(String email, String password);
}
