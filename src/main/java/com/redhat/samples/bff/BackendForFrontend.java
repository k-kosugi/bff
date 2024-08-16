package com.redhat.samples.bff;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BackendForFrontend extends RouteBuilder {

    private final static Logger LOGGER = Logger.getLogger(BackendForFrontend.class);

    @Override
    public void configure() throws Exception {
        //@formatter:off
        restConfiguration()
            .bindingMode(RestBindingMode.json)
                .dataFormatProperty("prettyPrint", "true")
                .enableCORS(true)
                .port("8081")
                .contextPath("/api")
                .apiContextPath("/api-doc")
                .apiProperty("api.title", "Backend for frontend api")
                .apiProperty("api.version", "1.0.0");

            rest("/books/isbn")
                .get("/{isbn}")
                .outType(Book.class)
                .to("direct:fetchBookDetails");
    
            from("direct:fetchBookDetails")
                .setHeader("isbn", simple("${header.isbn}"))
                .multicast(new MyAggregationStrategy()).parallelProcessing(false)
                    .to("direct:callBookApi", "direct:callRatingApi")
                .end();


            from("direct:callBookApi")
                .toD("http://localhost:8081/books?isbn=${header.isbn}&bridgeEndpoint=true")
                .unmarshal().json(Book.class)
                .setBody(body());
            
            from("direct:callRatingApi")
                .toD("http://localhost:8081/books/ratings?isbn=${header.isbn}&bridgeEndpoint=true")
                .unmarshal().json(Ratings.class)
                .setBody(body());
        //@formatter:onß
    }

}
