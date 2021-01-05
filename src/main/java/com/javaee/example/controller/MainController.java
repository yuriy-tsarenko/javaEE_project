package com.javaee.example.controller;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class MainController extends Application {

    private Set<Object> singletons = new HashSet<Object>();

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }

}
