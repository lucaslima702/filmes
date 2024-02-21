package br.com.filmes;

import java.util.Scanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories
@SpringBootApplication
@ComponentScan(basePackages = "br.com.filmes")
public class Main {
	public static void main(String[] args) throws Exception{
		SpringApplication.run(Main.class, args);
		System.out.println("[1] - Adicionar Filme\n[2] - Remover Filme\n[3] - Atualizar Filme\n[4] - Ler Filme");
		
		Scanner teclado = new Scanner(System.in);
		
		int numeroRecebido = teclado.nextInt();
		
		Runnable[] metodos = {() -> {
			try {
				FilmeService.addFilme();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, () -> {
			try {
				
				FilmeService.rmFilme();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		, () -> {
			try {
				FilmeService.udFilme();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, () -> {
			try {
				FilmeService.rdFilme();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}};
		
		metodos[numeroRecebido - 1].run();
		teclado.close();
	}			
}