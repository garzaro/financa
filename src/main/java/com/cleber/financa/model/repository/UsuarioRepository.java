package com.cleber.financa.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.cleber.financa.model.entity.Usuario;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
    /*existe um usuario com um email*/
    boolean existsByEmail(String email);
    
    /*busca um usuario por email*/
    Optional<Usuario> findByEmail(String email);
    
       
}