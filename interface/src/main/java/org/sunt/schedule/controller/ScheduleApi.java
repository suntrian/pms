package org.sunt.schedule.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.sunt.common.R;

@FeignClient(name = "", url = "")
@RequestMapping("/schedule")
public interface ScheduleApi {

    @GetMapping("/ping")
    default R<String> pong() {
        return R.success("pong");
    }

}
