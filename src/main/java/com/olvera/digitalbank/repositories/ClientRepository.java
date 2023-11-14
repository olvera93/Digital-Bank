package com.olvera.digitalbank.repositories;

import com.olvera.digitalbank.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
