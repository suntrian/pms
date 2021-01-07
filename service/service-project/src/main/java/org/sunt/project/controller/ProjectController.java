package org.sunt.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.sunt.common.R;
import org.sunt.project.service.ProjectService;

@RestController
public class ProjectController implements ProjectApi {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Autowired
    RestTemplate restTemplate;

    @Override
    public R<String> remoteInvoke(String param) {
        return restTemplate.getForObject("http://storage/storage/hello/" + param, R.class);
    }


}
