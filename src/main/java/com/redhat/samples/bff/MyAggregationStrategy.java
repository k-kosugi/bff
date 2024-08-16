package com.redhat.samples.bff;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.jboss.logging.Logger;

public class MyAggregationStrategy implements AggregationStrategy{

    private static final Logger LOGGER = Logger.getLogger(MyAggregationStrategy.class);

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {

        LOGGER.info("************************************");
        LOGGER.info("oldExchange: " + oldExchange);
        LOGGER.info("newExchange: " + newExchange);

        if (oldExchange == null) {
            return newExchange;
        }
        
        var book = oldExchange.getIn().getBody(Book.class);
        var ratings = newExchange.getIn().getBody(Ratings.class);
        book.setRatings(ratings.getRatings());

        oldExchange.getIn().setBody(book);
        LOGGER.info("ISBN: " + book.getIsbn());
        LOGGER.info("TITLE: " + book.getTitle());
        LOGGER.info("DESCRIPTION : " + book.getDescription());
        LOGGER.info("PRICE: " + book.getPrice());
        LOGGER.info("RATING: " + book.getRatings());
        LOGGER.info("************************************");
        return oldExchange;
    }
    
}
