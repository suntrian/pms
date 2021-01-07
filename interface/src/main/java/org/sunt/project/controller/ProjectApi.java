package org.sunt.project.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.sunt.common.R;

@FeignClient(name = "${spring.application.name}", url = "/project")
@RequestMapping("/project")
public interface ProjectApi {

    @GetMapping("/hello/{world}")
    default R<String> pong(@PathVariable("world") String world) {
        return R.success("hello " + world);
    }

    @GetMapping("/storage/{world}")
    R<String> remoteInvoke(@PathVariable("world") String param);
}
