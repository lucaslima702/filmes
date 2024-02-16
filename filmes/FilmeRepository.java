package br.com.filmes;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long>{
	
}
