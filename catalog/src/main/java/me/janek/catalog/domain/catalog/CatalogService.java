package me.janek.catalog.domain.catalog;

import java.util.List;

public interface CatalogService {

    List<CatalogInfo> getAllCatalogs();

    CatalogInfo getCatalogByToken(String productToken);

}
