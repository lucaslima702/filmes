package br.com.filmes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "filmes")
public class Filme {
	public Filme() {
		
	}
	
	public Filme(String nome, String genero, int anoDeLancamento, String tempo, int nota) {
		this.nome = nome;
		this.genero = genero;
		this.anoDeLancamento = anoDeLancamento;
		this.tempo = tempo;
		this.nota = nota;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "genero")
	private String genero;
	
	@Column(name = "ano_de_lancamento")
	private int anoDeLancamento;
	
	@Column(name = "tempo")
	private String tempo;
	
	@Column(name = "nota")
	private int nota;

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getAnoDeLancamento() {
		return anoDeLancamento;
	}

	public void setAnoDeLancamento(int anoDeLancamento) {
		this.anoDeLancamento = anoDeLancamento;
	}

	public String getTempo() {
		return tempo;
	}

	public void setTempo(String tempo) {
		this.tempo = tempo;
	}
	
	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}
	
	public Long getId() {
		return id;
	}
}
