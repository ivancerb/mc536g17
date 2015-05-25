package br.unicamp.ic.mc536.grupo17.process;

import java.util.List;
import java.util.ArrayList;

import br.unicamp.ic.mc536.grupo17.model.SearchResult;

public class SearchProcess {

	public List<SearchResult> getResults(String artist, String artwork, String material){
		return mockupSearchResults(artist, artwork, material);
	}
	
	private List<SearchResult> mockupSearchResults(String artist, String artwork, String material){
		List<SearchResult> searchResultList = new ArrayList<SearchResult>();
		for(int i=1; i<=10; i++){
			SearchResult result = new SearchResult();
			result.setArtistId(i);
			result.setArtworkId(i);
			result.setArtist("Artist " + i);
			result.setArtwork("Artwork " + artwork);
			searchResultList.add(result);
		}
		return searchResultList;
	}
	
}
