package com.example.searchApp.search;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/search/{title}")

public class SearchController {

	private final SearchService searchService;

	@Autowired
	public SearchController(SearchService searchService) {
		this.searchService = searchService;
	}

	@GetMapping
	public List<Listing> getSearchResults(@PathVariable("title") String title) throws InterruptedException {
		return (List<Listing>) searchService.getSearchResults(title);
	}

}
