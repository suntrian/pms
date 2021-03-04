package org.sunt.api.customize.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.sunt.common.mvc.R;

@FeignClient(name = "customize", path = "/customize")
public interface EntityDefinitionApi {

    @GetMapping("/ping")
    default R<String> pong() {
        return R.success("pong");
    }

}
