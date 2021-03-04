package org.sunt.schedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sunt.api.schedule.controller.ScheduleApi;
import org.sunt.schedule.service.ScheduleService;

@RestController
@RequestMapping("/schedule")
public class ScheduleController implements ScheduleApi {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }


}
