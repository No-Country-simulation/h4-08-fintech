package com.web.backend.domain.repository.addresses;

import com.web.backend.domain.model.addresses.Addresses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RAddresses extends JpaRepository<Addresses, Long> {
}