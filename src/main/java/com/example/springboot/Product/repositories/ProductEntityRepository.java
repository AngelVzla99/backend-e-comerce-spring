package com.example.springboot.Product.repositories;

import com.example.springboot.Product.entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductEntityRepository extends JpaRepository<ProductEntity, Long> {

    Page<ProductEntity> findAll(Pageable pageable);
    List<ProductEntity> findByIdIn(List<Long> ids);
    Page<ProductEntity> findByNameContainingOrDescriptionContaining( String name, String description, Pageable pageable);

    /**
     * TODO: Improve this query storing the vector in a column with an index
     * @param pageable
     * @return
     */
    @Query(value = "select * from (select *, ts_rank(vector, plainto_tsquery(?1)) as similarity\n" +
            "from (\n" +
            "select *, to_tsvector(p.\"name\" || '  '|| p.brand || ' ' || p.description) as vector\n" +
            "from products p\n" +
            ") as p2\n" +
            "where p2.vector @@ plainto_tsquery(?1))",

            countQuery = "SELECT count(*) FROM (select *\n" +
                    "from products p\n" +
                    "where to_tsvector(p.\"name\" || '  '|| p.brand || ' ' || p.description) @@ plainto_tsquery(?1))",
            nativeQuery = true)
    Page<ProductEntity> paginatedSearch(String queryText, Pageable pageable);
}