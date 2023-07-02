package me.janek.catalog.domain.catalog;

import lombok.RequiredArgsConstructor;
import me.janek.catalog.infrastructure.catalog.CatalogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CatalogServiceImpl implements CatalogService {

    private final CatalogRepository catalogRepository;

    @Override
    public List<CatalogInfo> getAllCatalogs() {
        return catalogRepository.findAll()
            .stream().map(CatalogInfo::new)
            .collect(Collectors.toList());
    }

    @Override
    public CatalogInfo getCatalogByToken(String productToken) {
        var findCatalog = catalogRepository.findByProductToken(productToken)
            .orElseThrow(RuntimeException::new);

        return new CatalogInfo(findCatalog);
    }

}
