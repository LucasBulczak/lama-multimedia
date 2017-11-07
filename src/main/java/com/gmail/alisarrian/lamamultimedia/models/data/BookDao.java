package com.gmail.alisarrian.lamamultimedia.models.data;

import com.gmail.alisarrian.lamamultimedia.models.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookDao extends CrudRepository<Book, Integer> {
}
