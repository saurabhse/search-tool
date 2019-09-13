package com.app.elasticsearch.service;

import com.app.elasticsearch.domain.CustomerDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.protocol.HTTP;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;

import java.io.IOException;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomerSearchService {

    private final RestHighLevelClient client;
    private final ObjectMapper objectMapper;

    public Mono<CustomerDetails> findById(String id) {

        try {
            RestTemplate  restTemplate = new RestTemplate();
            System.out.println(restTemplate.getForObject("http://172.17.88.81:9200", String.class));
        } catch (RestClientException e) {
            e.printStackTrace();
        }

        try {
            RestTemplate  restTemplate = new RestTemplate();
            System.out.println(restTemplate.getForObject("http://10.0.2.2:9200", String.class));
        } catch (RestClientException e) {
            e.printStackTrace();
        }



        try {
            GetRequest req = new GetRequest("customer_new", "_doc",id);
            GetResponse res = client.get(req, RequestOptions.DEFAULT);
            Map<String, Object> source = res.getSource();
            System.out.println(source);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Mono
                .<GetResponse>create(sink ->
                        client.getAsync(new GetRequest("customer_new", "_doc",id), RequestOptions.DEFAULT, actionListener(sink))
                )
                .filter(GetResponse::isExists)
                .map(GetResponse::getSource)
                .map(map -> objectMapper.convertValue(map, CustomerDetails.class));
    }

    private <T> ActionListener<T> actionListener(MonoSink<T> sink) {
        return new ActionListener<T>() {
            @Override
            public void onResponse(T response) {
                sink.success(response);
            }

            @Override
            public void onFailure(Exception e) {
                sink.error(e);
            }
        };
    }

    /*public static void main(String[] args) {
        RestTemplate  restTemplate = new RestTemplate();
        System.out.println(restTemplate.getForObject("http://172.17.88.81:9200", String.class));
    }*/
}
