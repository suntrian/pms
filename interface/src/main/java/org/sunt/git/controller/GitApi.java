package org.sunt.git.controller;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "git", url = "/git")
public interface GitApi {


}
