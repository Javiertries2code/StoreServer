
package com.elorrieta.storeapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.elorrieta.storeapi.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
