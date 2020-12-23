package com.shixun.api.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtils {

    public static UserDetails getUserDetals(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails){
            UserDetails userDetails = (UserDetails) principal;
            return userDetails;
        }
        return null;
    }

    public static String getUsername(){
        return getUserDetals().getUsername();
    }


}
