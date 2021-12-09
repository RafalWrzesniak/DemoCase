package sample.coding.task.myapp.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import sample.coding.task.myapp.model.elastic.Case;

public interface CaseElastic extends ElasticsearchRepository<Case, String> {

}
