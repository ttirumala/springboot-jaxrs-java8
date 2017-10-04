package com.account.jaxrs.patch;

import javax.ws.rs.core.MediaType;

public class MediaTypes {

    public final static String APPLICATION_JSON_PATCH = "application/json+patch";
    public final static String APPLICATION_PROTOBUF = "application/x-protobuf";

    public final static MediaType APPLICATION_JSON_PATCH_TYPE = new MediaType("application", "json+patch");
    public final static MediaType APPLICATION_PROTOBUF_TYPE = new MediaType("application", "x-protobuf");
}

