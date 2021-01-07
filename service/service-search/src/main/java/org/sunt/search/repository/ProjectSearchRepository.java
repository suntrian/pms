package org.sunt.search.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import org.sunt.search.entity.ProjectSearchPO;

@Repository
public interface ProjectSearchRepository extends ElasticsearchRepository<ProjectSearchPO, Integer> {


}
