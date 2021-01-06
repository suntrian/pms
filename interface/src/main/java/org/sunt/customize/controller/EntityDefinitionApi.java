package org.sunt.customize.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.sunt.common.R;

@RequestMapping("/customize")
@FeignClient(name = "customize", url = "")
public interface EntityDefinitionApi {

    @GetMapping("/ping")
    default R<String> pong() {
        return R.success("pong");
    }

}
