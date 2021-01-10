package org.sunt.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sunt.common.R;
import org.sunt.project.service.ProjectService;
import org.sunt.spring.I18n;
import org.sunt.storage.controller.StorageApi;

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


}
