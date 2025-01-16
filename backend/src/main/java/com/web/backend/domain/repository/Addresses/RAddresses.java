package com.web.backend.domain.repository.Addresses;

import com.web.backend.domain.model.Addresses.Addresses;
import com.web.backend.domain.model.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RAddresses extends JpaRepository<Addresses, Long>, JpaSpecificationExecutor<Addresses> {
}