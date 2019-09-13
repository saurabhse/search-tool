package com.app.elasticsearch.bulk;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class BulkInsert {

    private static final String INDEX="book_index";
    private static final String TYPE="book_type";

    private RestHighLevelClient restHighLevelClient;
    private ObjectMapper objectMapper;

    public void bulkInsert(List<Book> bookList){
        restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"),
                        new HttpHost("localhost", 9201, "http")
                )
        );
        BulkRequest bulkRequest = new BulkRequest();
        bookList.forEach(book -> {
            IndexRequest indexRequest = new IndexRequest(INDEX,TYPE,book.getId())
                    .source(objectMapper.convertValue(book, Map.class));
            bulkRequest.add(indexRequest);
        });
        try {
            restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void bulkInsert(int count){
        bulkInsert(getBookList(count));
    }
    private List getBookList(int count) {
        List bookList = new ArrayList<>();
        for(int i=0;i < count;i++) {
            Book book = new Book();
            book.setId(UUID.randomUUID().toString());
            book.setAuthor("author"+i);
            book.setGenre("genre"+i);
            book.setPublisher("publisher"+i);
            book.setTitle("title"+i);
            bookList.add(book);
        }
        return bookList;
    }
}

class Book {
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    private String id;
    private String author;
    private String genre;
    private String publisher;
    private String title;
}