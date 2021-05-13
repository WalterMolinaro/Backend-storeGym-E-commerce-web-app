package com.ecommerce.storegym.repositories;

import com.ecommerce.storegym.entities.SessioneSpedizione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessioneSpedizioneRepository extends JpaRepository<SessioneSpedizione,Long> {
}
