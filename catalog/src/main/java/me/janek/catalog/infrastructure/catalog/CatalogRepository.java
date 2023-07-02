package me.janek.catalog.infrastructure.catalog;

import me.janek.catalog.domain.catalog.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CatalogRepository extends JpaRepository<Catalog, Long> {

    Optional<Catalog> findByProductToken(String productToken);

}
