package com.alkewallet.wallet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
public class User {

    @Id
    private UUID id;
    @Setter
    @Getter
    private String email;
    @Setter
    @Getter
    private String password;

}
