package com.ecommerce.usermgmtapi.domain;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity(name = "\"user\"")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Nonnull
    private String firstName;

    @Nonnull
    private String lastName;

    @Nonnull
    private String mobile;

    @Nonnull
    private String email;

    private String alternateMobile;

    private String password;

    private Boolean isActive = true;

    private Date createdOn = new Date();

    private Date lastModifiedOn;

    @PreUpdate
    protected void onUpdate() {
        lastModifiedOn = new Date();
    }
}
