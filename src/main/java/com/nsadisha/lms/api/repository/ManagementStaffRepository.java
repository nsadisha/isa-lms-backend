package com.nsadisha.lms.api.repository;

import com.nsadisha.lms.api.model.ManagementStaff;
import com.nsadisha.lms.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Sadisha Nimsara
 * @created 08 of Feb 2023
 **/
@Repository
public interface ManagementStaffRepository extends JpaRepository<ManagementStaff, Integer> {
}
