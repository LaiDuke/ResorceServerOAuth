package org.duke.sa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.duke.sa.entity.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
}
