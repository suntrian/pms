package org.sunt.api.git.controller;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "git", path = "/git")
public interface GitApi {


}
