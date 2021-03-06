package com.gmail.alisarrian.lamamultimedia.models.data;

import com.gmail.alisarrian.lamamultimedia.models.Movie;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface MovieDao extends CrudRepository<Movie, Integer> {
}
