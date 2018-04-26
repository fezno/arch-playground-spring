package com.breuninger.arch.playground.outfit.domain;

import com.breuninger.arch.playground.common.domain.JongoMapper;
import com.breuninger.boot.mongo.AbstractMongoRepository;
import com.breuninger.boot.mongo.configuration.MongoProperties;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.stereotype.Repository;

@Repository
public class OutfitRepository extends AbstractMongoRepository<String, Outfit> {

  private final MongoCollection<Document> collection;

  public OutfitRepository(final MongoDatabase mongoDatabase, final MongoProperties mongoProperties) {
    super(mongoProperties);
    collection = mongoDatabase.getCollection("OutfitsSample");
  }

  @Override
  protected MongoCollection<Document> collection() {
    return collection;
  }

  @Override
  protected String keyOf(final Outfit outfit) {
    return outfit.getId();
  }

  @Override
  protected Document encode(final Outfit outfit) {
    return JongoMapper.encode(outfit);
  }

  @Override
  protected Outfit decode(final Document document) {
    return JongoMapper.decode(document, Outfit.class);
  }

  @Override
  protected void ensureIndexes() {
    //    CompletableFuture.runAsync(
    //      () -> collection().createIndex(Indexes.compoundIndex(Indexes.ascending(BOARD_ID), Indexes.descending(CREATION_DATE)),
    //        new IndexOptions().background(true)));
  }
}
