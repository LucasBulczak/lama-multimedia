package com.gmail.alisarrian.lamamultimedia.models.data;

import com.gmail.alisarrian.lamamultimedia.models.Category;
import com.gmail.alisarrian.lamamultimedia.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CategoryDao extends CrudRepository<Category, Integer> {
}
