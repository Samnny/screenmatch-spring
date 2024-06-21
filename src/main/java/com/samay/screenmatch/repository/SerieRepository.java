package com.samay.screenmatch.repository;

import com.samay.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
                                                    //Entidade - TipoId
public interface SerieRepository extends JpaRepository<Serie, Long> {
}
