package org.sunt.api.search.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.sunt.common.mvc.R;

@FeignClient(name = "search", path = "/search")
public interface SearchApi {

    @GetMapping("/ping")
    default R<String> ping(@RequestParam("world") String world) {
        return R.success("hello " + world);
    }

}
