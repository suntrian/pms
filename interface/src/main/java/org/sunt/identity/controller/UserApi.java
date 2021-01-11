package org.sunt.identity.controller;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "user", url = "/user")
public interface UserApi {


}
