package com.cinema.Repositories;

import com.cinema.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
