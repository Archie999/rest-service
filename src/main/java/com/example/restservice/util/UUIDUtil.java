package com.example.restservice.util;

import java.util.UUID;

/**
 * Created by Archie on 2021-03-01.
 */
public class UUIDUtil {

    public static String uuid(){
        return UUID.randomUUID().toString().replace("-", "");
    }

}
