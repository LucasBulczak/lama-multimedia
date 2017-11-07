package com.gmail.alisarrian.lamamultimedia.models.data;

import com.gmail.alisarrian.lamamultimedia.models.Season;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface SeasonDao extends CrudRepository<Season, Integer> {
}
