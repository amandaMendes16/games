package application.repository;

import org.springframework.data.repository.CrudRepository;

import application.model.Games;

public interface GamesRepository extends CrudRepository<Games, long> {

    public Iterable<Games> findByTitulo(String titulo);
    
}