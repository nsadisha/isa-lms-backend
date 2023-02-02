package com.nsadisha.lms.api.repository;

import com.nsadisha.lms.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

/**
 * @author Sadisha Nimsara
 * @created 31 of Jan 2023
 **/
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByEmail(String email);
}
