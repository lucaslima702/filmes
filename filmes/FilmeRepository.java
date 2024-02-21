package br.com.filmes;
import java.util.List;

import org.springframework.data.repository.Repository;

public interface FilmeRepository extends Repository<Filme, Long>{
	Filme save(Filme filme);
	List<Filme> findByNome(String nome);
	void delete(Filme filme);
}
