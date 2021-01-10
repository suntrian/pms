package org.sunt.customize.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.sunt.common.R;

@FeignClient(name = "customize", url = "/customize")
public interface EntityDefinitionApi {

    @GetMapping("/ping")
    default R<String> pong() {
        return R.success("pong");
    }

}
