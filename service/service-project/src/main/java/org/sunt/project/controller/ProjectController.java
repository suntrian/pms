package org.sunt.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sunt.api.project.controller.ProjectApi;
import org.sunt.api.storage.controller.StorageApi;
import org.sunt.common.mvc.R;
import org.sunt.project.service.ProjectService;
import org.sunt.spring.I18n;

@RestController
@RequestMapping("/project")
public class ProjectController implements ProjectApi {

    private final ProjectService projectService;

    @Autowired
    private StorageApi storageApi;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Autowired
    private I18n i18n;

    @Override
    public R<String> remoteInvoke(String param) {
        String hello = i18n.message("hello");
        return storageApi.pong(hello + param);
    }

    @GetMapping("/port")
    public R<Integer> port() {
        return storageApi.getPort();
    }

}
