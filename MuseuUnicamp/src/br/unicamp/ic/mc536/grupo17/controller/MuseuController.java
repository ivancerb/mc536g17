package br.unicamp.ic.mc536.grupo17.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.unicamp.ic.mc536.grupo17.dao.filter.SearchResult;
import br.unicamp.ic.mc536.grupo17.model.Artista;
import br.unicamp.ic.mc536.grupo17.model.Audiovisual;
import br.unicamp.ic.mc536.grupo17.model.Escultura;
import br.unicamp.ic.mc536.grupo17.model.Estilo;
import br.unicamp.ic.mc536.grupo17.model.Exposicao;
import br.unicamp.ic.mc536.grupo17.model.Pintura;
import br.unicamp.ic.mc536.grupo17.model.results.ArtistSearchResult;
import br.unicamp.ic.mc536.grupo17.model.results.AudiovisualSearchResult;
import br.unicamp.ic.mc536.grupo17.model.results.EsculturaSearchResult;
import br.unicamp.ic.mc536.grupo17.model.results.PinturaSearchResult;
import br.unicamp.ic.mc536.grupo17.process.InfoPagesProcess;
import br.unicamp.ic.mc536.grupo17.process.SearchProcess;

@Controller
@RequestMapping("/museu")
public class MuseuController {
	

	@RequestMapping("/home")
	public String loadHomePage(Model model) throws SQLException{
		InfoPagesProcess infoPagesProcess  = new InfoPagesProcess();
		List<Exposicao> activeExposList = infoPagesProcess.getActiveExpos();
		model.addAttribute("activeExposList", activeExposList);
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
		System.out.println(artistId);
		InfoPagesProcess infoPagesProcess = new InfoPagesProcess();
		List<Artista> artista = infoPagesProcess.getArtistInfo(artistId);
		model.addAttribute("artistInfo", artista.get(0));
		return "/museu/artist";
	}
	
	@RequestMapping("/pinturas/{pinturaId}")
	public String loadPinturaInfo(@PathVariable String pinturaId, @RequestParam int idAutor, @RequestParam String nomeAutor, 
			@RequestParam int idEstilo, @RequestParam String nomeEstilo, Model model) throws SQLException{
		System.out.println(pinturaId);
		InfoPagesProcess infoPagesProcess = new InfoPagesProcess();
		List<Pintura> result = infoPagesProcess.getPinturaInfo(pinturaId);
		
		model.addAttribute("result", result.get(0));
		
		model.addAttribute("idAutor", idAutor);
		model.addAttribute("nomeAutor", nomeAutor);
		model.addAttribute("idEstilo", idEstilo);
		model.addAttribute("nomeEstilo", nomeEstilo);
		
		model.addAttribute("isPintura", true);
		model.addAttribute("isEscultura", false);
		model.addAttribute("isAudiovisual", false);
		
		return "/museu/artwork";
	}
	
	@RequestMapping("/esculturas/{esculturaId}")
	public String loadEsculturaInfo(@PathVariable String esculturaId, @RequestParam int idAutor, @RequestParam String nomeAutor, 
			@RequestParam int idEstilo, @RequestParam String nomeEstilo, Model model) throws SQLException{
		System.out.println(esculturaId);
		InfoPagesProcess infoPagesProcess = new InfoPagesProcess();
		List<Escultura> result = infoPagesProcess.getEsculturaInfo(esculturaId);
		
		model.addAttribute("result", result.get(0));
		
		model.addAttribute("idAutor", idAutor);
		model.addAttribute("nomeAutor", nomeAutor);
		model.addAttribute("idEstilo", idEstilo);
		model.addAttribute("nomeEstilo", nomeEstilo);
		
		model.addAttribute("isPintura", false);
		model.addAttribute("isEscultura", true);
		model.addAttribute("isAudiovisual", false);
		
		return "/museu/artwork";
	}
	
	@RequestMapping("/audiovisual/{audiovisualId}")
	public String loadAudiovisualInfo(@PathVariable String audiovisualId, @RequestParam int idAutor, @RequestParam String nomeAutor, 
			@RequestParam int idEstilo, @RequestParam String nomeEstilo, Model model) throws SQLException{
		System.out.println(audiovisualId);
		InfoPagesProcess infoPagesProcess = new InfoPagesProcess();
		List<Audiovisual> result = infoPagesProcess.getAudiovisualInfo(audiovisualId);
		
		model.addAttribute("result", result.get(0));
		
		model.addAttribute("idAutor", idAutor);
		model.addAttribute("nomeAutor", nomeAutor);
		model.addAttribute("idEstilo", idEstilo);
		model.addAttribute("nomeEstilo", nomeEstilo);
		
		model.addAttribute("isPintura", false);
		model.addAttribute("isEscultura", false);
		model.addAttribute("isAudiovisual", true);
		
		return "/museu/artwork";
	}
	
	@RequestMapping("/estilo/{estiloId}")
	public String loadEstiloPage(@PathVariable String estiloId, Model model) throws SQLException{
		System.out.println(estiloId);
		InfoPagesProcess infoPagesProcess = new InfoPagesProcess();
		List<Estilo> estilo = infoPagesProcess.getEstiloInfo(estiloId);
		model.addAttribute("estiloInfo", estilo.get(0));
		return "/museu/style";
	}
	
	
	@RequestMapping("/exposicoes")
	public String loadAllExposPage(Model model) throws SQLException{
		InfoPagesProcess infoPagesProcess  = new InfoPagesProcess();
		List<Exposicao> activeExposList = infoPagesProcess.getActiveExpos();
		List<Exposicao> expoList = infoPagesProcess.getAllExpos();
		model.addAttribute("activeExposList", activeExposList);
 		model.addAttribute("expoList", expoList);
		return "/museu/expo-list";
	}
	
	@RequestMapping("/exposicoes/{expoId}")
	public String loadExpoPage(@PathVariable int expoId, Model model) throws SQLException{
		System.out.println(expoId);
		InfoPagesProcess process = new InfoPagesProcess();
		List<Exposicao> expoInfo = process.getExpoInfo(expoId);
		
		String nomeCurador = process.getCuradorFromExpo(expoInfo.get(0).getIdCurador());
		
		List<PinturaSearchResult> expoPinturaResultsList = process.getPinturasFromExpo(expoId);
		List<EsculturaSearchResult> expoEsculturaResultsList = process.getEsculturasFromExpo(expoId);
		List<AudiovisualSearchResult> expoAudiovisualResultsList = process.getAudiovisualFromExpo(expoId);
		
		model.addAttribute("expoPinturaResultsList", expoPinturaResultsList);
		model.addAttribute("expoEsculturaResultsList", expoEsculturaResultsList);
		model.addAttribute("expoAudiovisualResultsList", expoAudiovisualResultsList);
		
		model.addAttribute("nomeCurador", nomeCurador);
 		model.addAttribute("expo", expoInfo.get(0));
 		
		return "/museu/expo";
	}
	
	@RequestMapping("/sobre")
	public String loadAboutPage(){
		return "/museu/sobre";
	}
	
	@RequestMapping("/contato")
	public String loadContatoPage(){
		return "/museu/contato";
	}
}
