package com.web.backend.domain.repository.Addresses;

import com.web.backend.domain.model.Addresses.Addresses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RAddresses extends JpaRepository<Addresses, Long> {
}