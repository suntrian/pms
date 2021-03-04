package org.sunt.identity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sunt.api.identity.controller.UserApi;
import org.sunt.api.identity.entity.UserVo;
import org.sunt.common.mvc.R;
import org.sunt.identity.entity.Converter;
import org.sunt.identity.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController implements UserApi {

    @Autowired
    private UserService userService;

    @Override
    public R<UserVo> createUser(UserVo user) {
        return R.success(Converter.USER_CONV.toUserVo(userService.create(Converter.USER_CONV.fromUserVo(user))));
    }

}
