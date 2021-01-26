package org.sunt.identity.entity;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


public interface Converter {

    MenuConv MENU_CONV = Mappers.getMapper(MenuConv.class);

    UserConv USER_CONV = Mappers.getMapper(UserConv.class);

    @Mapper
    interface MenuConv {

        MenuNode toMenuNode(Menu menu);

        Menu fromMenuNode(MenuNode menuNode);
    }

    @Mapper
    interface UserConv {

        UserVo toUserVo(User user);

        User fromUserVo(UserVo user);

    }

}
