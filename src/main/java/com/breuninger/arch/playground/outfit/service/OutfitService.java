package com.breuninger.arch.playground.outfit.service;

import com.breuninger.arch.playground.outfit.domain.Outfit;
import com.breuninger.arch.playground.outfit.domain.OutfitRepository;
import io.micrometer.core.instrument.MeterRegistry;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;

@Service
public class OutfitService {

  private final OutfitRepository OutfitRepository;
  private final LongAdder OutfitCount;

  public OutfitService(final OutfitRepository OutfitRepository, final MeterRegistry meterRegistry) {
    this.OutfitRepository = OutfitRepository;
    OutfitCount = new LongAdder();
    OutfitCount.add(count());
    meterRegistry.gauge("outfits.count", OutfitCount);
  }

  public List<Outfit> findAll() {
    return OutfitRepository.findAll();
  }

  public Outfit create(final Outfit Outfit) {
    final var creationDate = new Date();
    final var createdOutfit = OutfitRepository.create(Outfit.toBuilder()
      .id(ObjectId.get().toString())
      .creationDate(creationDate)
      .lastModificationDate(creationDate)
      .build());
    OutfitCount.increment();
    return createdOutfit;
  }

  private long count() {
    return OutfitRepository.size();
  }
}
