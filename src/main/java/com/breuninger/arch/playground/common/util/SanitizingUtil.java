package com.breuninger.arch.playground.common.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class SanitizingUtil {

  // private static final PolicyFactory DISALLOW_ANYTHING_POLICY = new HtmlPolicyBuilder().toFactory();

  public static <T> T  sanitize(final T anything) {
    // return StringUtils.trim(StringEscapeUtils.unescapeHtml4(DISALLOW_ANYTHING_POLICY
    // .sanitize(StringEscapeUtils.unescapeHtml4(text))))
    return anything;
  }
}
