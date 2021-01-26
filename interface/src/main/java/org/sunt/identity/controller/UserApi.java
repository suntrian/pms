package org.sunt.identity.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.sunt.common.R;
import org.sunt.identity.entity.UserVo;

@FeignClient(name = "user", path = "/user")
public interface UserApi {

    @PostMapping("/register")
    R<UserVo> createUser(UserVo user);


}
