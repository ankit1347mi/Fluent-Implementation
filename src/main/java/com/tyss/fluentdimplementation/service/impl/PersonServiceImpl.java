package com.tyss.fluentdimplementation.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.RangeQuery;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.json.JsonData;
import com.tyss.fluentdimplementation.aspect.LogAspect;
import com.tyss.fluentdimplementation.entity.Person;
import com.tyss.fluentdimplementation.service.PersonService;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private ElasticsearchClient elasticsearchClient;

    public PersonServiceImpl(ElasticsearchClient elasticsearchClient) {
        this.elasticsearchClient = elasticsearchClient;
    }

    @LogAspect
    @Override
    public String savePerson(Person person) throws IOException {
        String indexName = Person.class.getAnnotation(Document.class).indexName();
        return elasticsearchClient.index(i -> i.index(indexName).id(person.getId()).document(person)).toString();

    }

    @LogAspect
    @Override
    public String findPersonById(String id) throws IOException {
        String indexName = Person.class.getAnnotation(Document.class).indexName();
        return elasticsearchClient.get(s -> s.index(indexName).id(id), Person.class).toString();
    }

    @LogAspect
    @Override
    public String findPersonByName(String name) throws IOException {
        String indexName = Person.class.getAnnotation(Document.class).indexName();
        return elasticsearchClient.search(s -> s.index(indexName).query(q -> q.match(m -> m.field("name").query(name))), Person.class).toString();

    }

    @LogAspect
    @Override
    public List<Person> findAllPerson() throws IOException {
        String indexName = Person.class.getAnnotation(Document.class).indexName();
        SearchResponse<Person> searchResponse = elasticsearchClient.search(s -> s.index(indexName).size(100), Person.class);
        return searchResponse.hits().hits().stream().map(Hit::source).toList();

    }

    @LogAspect
    @Override
    public List<Person> findPersonAgeGreaterThan(int age) throws IOException {
        String indexName = Person.class.getAnnotation(Document.class).indexName();
        SearchResponse<Person> searchResponse = elasticsearchClient.search(s -> s.index(indexName).query(RangeQuery.of(r -> r.field("age").gte(JsonData.of(age)))._toQuery()), Person.class);
        return searchResponse.hits().hits().stream().map(Hit::source).toList();
    }

    @LogAspect
    @Override
    public String updatePerson(Person person) throws IOException {
        String indexName = Person.class.getAnnotation(Document.class).indexName();
        return elasticsearchClient.update(u -> u.index(indexName).id(person.getId()).doc(person), Person.class).toString();
    }

    @LogAspect
    @Override
    public String deletePerson(String id) throws IOException {
        String indexName = Person.class.getAnnotation(Document.class).indexName();
        return elasticsearchClient.delete(i -> i.index(indexName).id(id)).toString();

    }


}
