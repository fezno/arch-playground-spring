package com.breuninger.arch.playground.outfit.web;

import com.breuninger.arch.playground.outfit.domain.Outfit;
import com.breuninger.arch.playground.outfit.service.OutfitService;
import com.breuninger.arch.playground.toggle.domain.Features;
import io.micrometer.core.annotation.Timed;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.breuninger.arch.playground.common.domain.BadRequestException.badRequest;
import static java.util.concurrent.TimeUnit.HOURS;
import static org.springframework.http.CacheControl.maxAge;
import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@AllArgsConstructor
@RestController
@RequestMapping("/outfits")
public class OutfitRestController {

  private final Validator validator;
  private final OutfitService OutfitService;

  @Timed("rest.outfits.findAll")
  @GetMapping(produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Outfit>> findAll() {
    if (!Features.TEST_TOGGLE.isActive()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok()
      .contentType(APPLICATION_JSON)
      .cacheControl(maxAge(1, HOURS).cachePublic())
      .varyBy(ACCEPT)
      .body(OutfitService.findAll());
  }

  @Timed("rest.outfits.create")
  @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<Outfit> create(@RequestBody final Outfit Outfit, final Errors errors) {
    final var sanitizedOutfit = Outfit.sanitize();
    validator.validate(sanitizedOutfit, errors);
    if (errors.hasErrors()) {
      throw badRequest(errors);
    }

    final var createdOutfit = OutfitService.create(sanitizedOutfit);
    return ResponseEntity.ok().contentType(APPLICATION_JSON).body(createdOutfit);
  }
}
