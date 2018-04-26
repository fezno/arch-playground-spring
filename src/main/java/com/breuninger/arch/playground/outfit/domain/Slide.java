package com.breuninger.arch.playground.outfit.domain;

import com.breuninger.arch.playground.common.util.SanitizingUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Builder(toBuilder = true)
@AllArgsConstructor
@Value
public class Slide {

  private final String variant;

  private final Date creationDate;
  private final Date lastModificationDate;;

  public Slide sanitize() {
    return toBuilder().variant(SanitizingUtil.sanitize(variant)).build();
  }


}
