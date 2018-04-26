package com.breuninger.arch.playground.outfit.domain;

import com.breuninger.arch.playground.common.util.SanitizingUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.jongo.marshall.jackson.oid.MongoId;

import java.util.Date;

@Builder(toBuilder = true)
@AllArgsConstructor
@Value
public class Outfit {

  @MongoId
  private final String id;

  private final Ansichten ansichten;
  private final java.util.List<Slide> slides;

  private final Date creationDate;
  private final Date lastModificationDate;

  public Outfit sanitize() {
    return toBuilder().ansichten(SanitizingUtil.sanitize(ansichten)).build();
  }
}
