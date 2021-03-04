package org.sunt.search.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sunt.api.search.controller.SearchApi;

@RestController
@RequestMapping("/search")
public class SearchController implements SearchApi {


}
