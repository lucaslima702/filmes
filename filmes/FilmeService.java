package br.com.filmes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
	public static void addFilme() throws Exception {
		
		
		System.out.println("Digite o nome do filme: ");
		String nomeFormatado = teclado.nextLine().replace(" ", "+");
		
		URL url = new URL("https://www.omdbapi.com/?t=" + nomeFormatado +"&apikey=d6ffc961");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.connect();
		if(connection.getResponseCode() != 200) {
			throw new RuntimeException("HTTP error code : " + connection.getResponseCode());
		}
		
		BufferedReader resposta = new BufferedReader(new InputStreamReader((connection.getInputStream())));
		String jsonRetornado = resposta.readLine();
		JSONObject objeto = new JSONObject(jsonRetornado.toString());
		System.out.println("Qual nota você da para esse filme ?");
		int nota = teclado.nextInt();
		
		
		Filme filmeRecebido = new Filme(objeto.getString("Title"),objeto.getString("Genre"), objeto.getInt("Year"), objeto.getString("Runtime"), nota);
		filmeRepository.save(filmeRecebido);
		System.out.println("Filme = " + objeto.getString("Title") + " adicionado com sucesso");
	}
	
	public static void rmFilme() throws Exception{
		System.out.println("rmFilme");
	}
	
	public static void udFilme() throws Exception{
		System.out.println("udFilme");
	}
	
	public static void rdFilme() throws Exception{
		System.out.println("rdFilme");
	}
	
}
