package com.app.solr.service;


import com.app.solr.domain.CustomerDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomerSearchService {


    private final ObjectMapper objectMapper;

    public Mono<CustomerDetails> findById(String id) {
        try {
            String serverURL = "http://localhost:8983/solr/customer_core";
            SolrClient solr = new HttpSolrClient.Builder(serverURL).build();
            SolrQuery query = new SolrQuery();
            query.setQuery("*:*");
            QueryResponse response = solr.query(query);
            List<CustomerDetails> list = response.getBeans(CustomerDetails.class);
            for(CustomerDetails customerDetails : list){
                System.out.println(customerDetails.toString());
            }
            SolrDocumentList results = response.getResults();
            for (int i = 0; i < results.size(); ++i) {
                System.out.println(results.get(i));
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
