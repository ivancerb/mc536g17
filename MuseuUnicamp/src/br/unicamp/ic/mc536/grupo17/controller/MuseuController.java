package br.unicamp.ic.mc536.grupo17.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.unicamp.ic.mc536.grupo17.model.SearchResult;
import br.unicamp.ic.mc536.grupo17.process.ArtistaProcess;
import br.unicamp.ic.mc536.grupo17.process.SearchProcess;

@Controller
@RequestMapping("/museu")
public class MuseuController {
	

	@RequestMapping("/home")
	public String loadHomePage(Model model) throws SQLException{
		return "/museu/home";
	}
	
	@RequestMapping("/search")
	public String loadSearchPage(Model model) throws SQLException{
		//TestsResultsDao.insert();
		//model.addAttribute("testeBd", "Hello");
		return "/museu/search";
	}
	
	@RequestMapping("/process-search")
	public String processSearch(@RequestParam String artist, @RequestParam String artwork, @RequestParam String material, Model model) throws SQLException{
		System.out.println("artist: " + artist + "artwork: " + artwork + "material: " + material);
		SearchProcess process = new SearchProcess();
		List<SearchResult> searchResultsList = process.getResults(artist, artwork, material);
		model.addAttribute("searchResultsList", searchResultsList);
		//TestsResultsDao.insert();
		//model.addAttribute("testeBd", "Hello");
		return "museu/search-results";
	}
	
	@RequestMapping("/artist/{artistId}")
	public String loadArtistPage(@PathVariable String artistId, Model model) throws SQLException{
		//TestsResultsDao.insert();
		//model.addAttribute("testeBd", "Hello");
		model.addAttribute("artistInfo", new ArtistaProcess().getArtistInfo(artistId));
		return "/museu/artist";
	}
	
	@RequestMapping("/artwork")
	public String loadArtworkPage(Model model) throws SQLException{
		//TestsResultsDao.insert();
		//model.addAttribute("testeBd", "Hello");
		return "/museu/artwork";
	}
	
	@RequestMapping("/expo")
	public String loadExpoPage(Model model) throws SQLException{
		//TestsResultsDao.insert();
		//model.addAttribute("testeBd", "Hello");
		return "/museu/expo";
	}
	
	
}
