package com.gmail.alisarrian.lamamultimedia.models.data;

import com.gmail.alisarrian.lamamultimedia.models.Episode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface EpisodeDao extends CrudRepository<Episode, Integer> {
}
