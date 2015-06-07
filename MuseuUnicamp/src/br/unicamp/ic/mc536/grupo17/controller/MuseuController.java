package br.unicamp.ic.mc536.grupo17.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.unicamp.ic.mc536.grupo17.dao.filter.SearchResult;
import br.unicamp.ic.mc536.grupo17.model.results.ArtistSearchResult;
import br.unicamp.ic.mc536.grupo17.model.results.AudiovisualSearchResult;
import br.unicamp.ic.mc536.grupo17.model.results.EsculturaSearchResult;
import br.unicamp.ic.mc536.grupo17.model.results.PinturaSearchResult;
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
		SearchProcess process = new SearchProcess();
		List<String> nomeEstilosList = process.selectNomeEstiloList();
		model.addAttribute("nomeEstilosList", nomeEstilosList);
		return "/museu/search-complex";
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
	
	@RequestMapping("/process-artist-search")
	public String processArtistSearch(@RequestParam String nome, @RequestParam String pais, @RequestParam String estilo, 
			@RequestParam boolean dataInicioChecked, @RequestParam int dataInicio, @RequestParam boolean dataFimChecked, 
			@RequestParam int dataFim, Model model) throws SQLException{
		
		System.out.println("nome" + nome + "pais" + pais + "estilo" + estilo +  "dataInicioChecked" + dataInicioChecked +
				"dataInicio" + dataInicio + "dataFimChecked" + dataFimChecked +  "dataFim" + dataFim);

		SearchProcess process = new SearchProcess();
		List<ArtistSearchResult> searchResultsList = process.getArtistaResults(nome, pais, estilo, 
				dataInicioChecked, dataInicio, dataFimChecked, dataFim);
		model.addAttribute("searchResultsList", searchResultsList);
		return "museu/search-artist-results";
	}
	
	@RequestMapping("/process-obra-search")
	public String processObraSearch(@RequestParam String titulo, @RequestParam String autor, @RequestParam boolean isPintura, 
			@RequestParam boolean isEscultura, @RequestParam boolean isAudiovisual, @RequestParam boolean materialSearchChecked, 
			@RequestParam String material, @RequestParam boolean tipoMidiaChecked, @RequestParam String tipoMidia, @RequestParam String pais, 
			@RequestParam String estilo, @RequestParam boolean dataInicioChecked, @RequestParam int dataInicio, 
			@RequestParam boolean dataFimChecked, @RequestParam int dataFim, Model model) throws SQLException{
		
		System.out.println("controller1");
		
		SearchProcess process = new SearchProcess();
		//inicializando filtros de busca
		process.setObraSearchFilters(titulo, autor, isPintura, isEscultura, isAudiovisual, 
				materialSearchChecked, material, tipoMidiaChecked, tipoMidia, pais, estilo, 
				dataInicioChecked, dataInicio, dataFimChecked, dataFim);
		System.out.println("controller2");
		//consultas no banco para os filtros
		List<PinturaSearchResult> searchPinturaResultsList = process.searchPinturas();
		List<EsculturaSearchResult> searchEsculturaResultsList = process.searchEsculturas();
		List<AudiovisualSearchResult> searchAudiovisualResultsList = process.searchAudiovisual();
		
		System.out.println("controller3");
		model.addAttribute("searchPinturaResultsList", searchPinturaResultsList);
		model.addAttribute("searchEsculturaResultsList", searchEsculturaResultsList);
		model.addAttribute("searchAudiovisualResultsList", searchAudiovisualResultsList);
		
		return "museu/search-obra-results";
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
