package br.com.filmes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class FilmeService {
	
	private static FilmeRepository filmeRepository;
	
	public FilmeService(FilmeRepository filmeRepository) {
        FilmeService.filmeRepository = filmeRepository;
    }
	
	static Scanner teclado = new Scanner (System.in);
	
	public static JSONObject retornaFilme(String nomeDoFilme) throws Exception {
		String nomeFormatado = nomeDoFilme.replace(" ", "+");
		
		URL url = new URL("https://www.omdbapi.com/?t=" + nomeFormatado +"&apikey=d6ffc961");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.connect();
		if(connection.getResponseCode() != 200) {
			throw new RuntimeException("HTTP error code : " + connection.getResponseCode());
		}
		
		BufferedReader resposta = new BufferedReader(new InputStreamReader((connection.getInputStream())));
		String jsonRetornado = resposta.readLine();
		return new JSONObject(jsonRetornado.toString());
	}
	
	public static Filme getFilme(List<Filme> lista, String nome) {
		Filme filmeSelecionado = null;
		for (Filme filme : lista) {
		    if (filme.getNome().equals(nome)) {
		    	filmeSelecionado = filme;
		        ;
		    }
		}
		return filmeSelecionado;
	}
	
	public static void addFilme() throws Exception {
		System.out.println("Digite o nome do filme: ");
		String nomeDoFilme = teclado.nextLine();
		JSONObject objeto = retornaFilme(nomeDoFilme);
		System.out.println("Qual nota você da para esse filme ?");
		int nota = teclado.nextInt();
		
		
		Filme filmeRecebido = new Filme(objeto.getString("Title"),objeto.getString("Genre"), objeto.getInt("Year"), objeto.getString("Runtime"), nota);
		System.out.println("- Favor verificar se o filme selecionado é o correto.\nPoster do filme: " + objeto.getString("Poster"));
		filmeRepository.save(filmeRecebido);
		System.out.println("Filme = " + objeto.getString("Title") + " adicionado com sucesso");
	}
	
	public static void rmFilme() throws Exception{
		System.out.println("Digite o nome do filme para remove-lo do DB: ");
		String nomeDoFilme = teclado.nextLine();
		Filme filmeSelecionado = getFilme(filmeRepository.findByNome(nomeDoFilme), nomeDoFilme);
		filmeRepository.delete(filmeSelecionado);
		System.out.println("Filme = " + filmeSelecionado.getNome() + " deletado com sucesso");
	}
	
	public static void udFilme() throws Exception{
		System.out.println("udFilme");
	}
	
	public static void rdFilme() throws Exception{
		System.out.println("rdFilme");
	}
	
}
