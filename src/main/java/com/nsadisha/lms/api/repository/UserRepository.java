package com.nsadisha.lms.api.repository;

import com.nsadisha.lms.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sadisha Nimsara
 * @created 31 of Jan 2023
 **/
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
