package com.breuninger.arch.playground.outfit.domain;

import com.breuninger.arch.playground.common.util.SanitizingUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Builder(toBuilder = true)
@AllArgsConstructor
@Value
public class Ansichten {

  private final String outfitUrl;

  private final Date creationDate;
  private final Date lastModificationDate;;

  public Ansichten sanitize() {
    return toBuilder().outfitUrl(SanitizingUtil.sanitize(outfitUrl)).build();
  }


}
