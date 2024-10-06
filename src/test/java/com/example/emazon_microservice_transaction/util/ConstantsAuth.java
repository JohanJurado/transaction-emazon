package com.example.emazon_microservice_transaction.util;

import java.util.Date;

public class ConstantsAuth {

    public ConstantsAuth(){
    }

    public String STRING_KEY = "eENRlFL9i0kFx+JhJGQYk8KGtIwx5Vm/m+aGJXmMNh0=";
    public Date TIME = new Date(System.currentTimeMillis() + 1000 * 60 * 60);
    public Date TIME_EXPIRED = new Date(System.currentTimeMillis() - 1000 * 60);
    public Date TIME_NOT_EXPIRED = new Date(System.currentTimeMillis() + 1000 * 60);
    public String USER = "admin@gmail.com";
    public String ROLE = "ADMIN";
    public String CLAIM_ROLE_PARAM = "role";
    public String BEARER_TOKEN = "Bearer valid-token";
    public String JWT_TOKEN = "valid-token";
    public String AUTHORIZATION = "Authorization";

}
