package com.olvera.digitalbank.repositories;

import com.olvera.digitalbank.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT c FROM Client c WHERE c.name LIKE :kw")
    List<Client> searchClients(@Param(value = "kw")String keyword);
}
