package com.estore.api.estoreapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class User{

    @JsonProperty("username") private String username;

    public User(@JsonProperty("username") String username){
        this.username = username;
    }

    
}