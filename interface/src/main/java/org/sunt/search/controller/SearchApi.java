package org.sunt.search.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.sunt.common.R;

@FeignClient(name = "", url = "")
@RequestMapping("/search")
public interface SearchApi {

    @GetMapping("/ping")
    default R<String> ping(@RequestParam("world") String world) {
        return R.success("hello " + world);
    }

}
