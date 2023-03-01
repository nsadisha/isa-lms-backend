package com.nsadisha.lms.api.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sadisha Nimsara
 * @created 01 of Feb 2023
 **/
@Entity
@Data
@NoArgsConstructor
public class ManagementStaff extends User{
    public ManagementStaff(User user) {
        super(user);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
