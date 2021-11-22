package com.legiz.terasoftproject.userProfile.domain.model.entity;

import com.legiz.terasoftproject.security.domain.model.entity.Role;
import com.legiz.terasoftproject.security.domain.model.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("user_customer")
public class Customer extends User {

    @NotNull
    @NotBlank
    private String customerName;

    @NotNull
    @NotBlank
    private String customerLastName;

    public Customer(Long id, String username, String password, String email, Set<Role> role, String customerName, String customerLastName) {
        super(id, username, password, email, role);
        this.customerName = customerName;
        this.customerLastName = customerLastName;
    }

    public Customer(String username, String password,  String email, Set<Role> role, String customerName, String customerLastName) {
        super(username, password, email, role);
        this.customerName = customerName;
        this.customerLastName = customerLastName;
    }
}
