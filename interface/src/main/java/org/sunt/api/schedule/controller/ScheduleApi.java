package org.sunt.api.schedule.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.sunt.common.mvc.R;

@FeignClient(name = "schedule", path = "/schedule")
public interface ScheduleApi {

    @GetMapping("/ping")
    default R<String> pong() {
        return R.success("pong");
    }

}
