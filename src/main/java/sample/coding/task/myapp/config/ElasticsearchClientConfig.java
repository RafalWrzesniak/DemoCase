package sample.coding.task.myapp.config;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Slf4j
@Configuration
@ComponentScan(basePackages = "sample.coding.task.myapp.service")
@EnableElasticsearchRepositories(basePackages = { "sample.coding.task.myapp.repository" })
public class ElasticsearchClientConfig extends AbstractElasticsearchConfiguration {

    @Bean
    @Override
    public RestHighLevelClient elasticsearchClient() {
        final ClientConfiguration clientConfiguration = ClientConfiguration
                .builder()
                .connectedTo("elastic:9200")
                .build();
        return RestClients.create(clientConfiguration).rest();
    }


}
