package com.example.cursovoy.repo;

import com.example.cursovoy.models.Vakansy;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface VakansyRepository extends CrudRepository<Vakansy, Long> {
    List<Vakansy> findVakansiesByNamevakansyIsContaining(String name);
}
