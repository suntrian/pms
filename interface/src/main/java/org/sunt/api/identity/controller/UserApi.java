package org.sunt.api.identity.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.sunt.api.identity.entity.UserVo;
import org.sunt.common.mvc.R;

@FeignClient(name = "user", path = "/user")
public interface UserApi {

    @PostMapping("/register")
    R<UserVo> createUser(UserVo user);


}
