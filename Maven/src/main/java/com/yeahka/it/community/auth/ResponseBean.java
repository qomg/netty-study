package com.yeahka.it.community.auth;

import java.io.Serializable;

public class ResponseBean<T extends Serializable> {

    private String reqSerialNo;
    private int respCode;
    private String respMsg;
    private String version;
    private T data;

}
