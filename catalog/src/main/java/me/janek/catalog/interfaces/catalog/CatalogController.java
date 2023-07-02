package me.janek.catalog.interfaces.catalog;

import lombok.RequiredArgsConstructor;
import me.janek.catalog.domain.catalog.CatalogInfo;
import me.janek.catalog.domain.catalog.CatalogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static me.janek.catalog.interfaces.catalog.CatalogDto.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/catalog-service/catalog")
public class CatalogController {

    private final CatalogService catalogService;

    @GetMapping()
    public ResponseEntity<List<CatalogResponse>> getAllCatalogs() {
        var allCatalog = catalogService.getAllCatalogs()
            .stream()
            .map(CatalogInfo::toDto)
            .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(allCatalog);
    }

    @GetMapping("/{product-token}")
    public ResponseEntity<CatalogResponse> getCatalogByToken(
        @PathVariable("product-token") String productToken
    ) {
        var findCatalog = catalogService.getCatalogByToken(productToken).toDto();

        return ResponseEntity.status(HttpStatus.OK).body(findCatalog);
    }

}
