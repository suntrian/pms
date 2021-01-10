package org.sunt.project.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.sunt.common.R;

@FeignClient(name = "project", url = "/project")
public interface ProjectApi {

    @GetMapping("/hello/{world}")
    default R<String> pong(@PathVariable("world") String world) {
        return R.success("hello " + world);
    }

    @GetMapping("/storage/{world}")
    R<String> remoteInvoke(@PathVariable("world") String param);
}
