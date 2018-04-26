package com.breuninger.arch.playground.outfit.web;

import com.breuninger.arch.playground.outfit.service.OutfitService;
import io.micrometer.core.annotation.Timed;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.http.MediaType.TEXT_HTML_VALUE;

@AllArgsConstructor
@Controller
@RequestMapping("/outfits")
public class OutfitHtmlController {

  private final OutfitService outfitService;

  @Timed("html.outfits.findAll")
  @GetMapping(produces = TEXT_HTML_VALUE)
  public ModelAndView findAll() {
    final var model = new ModelMap();
    model.put("outfits", outfitService.findAll());
    return new ModelAndView("outfitOverviewPage.html", model);
  }
}
