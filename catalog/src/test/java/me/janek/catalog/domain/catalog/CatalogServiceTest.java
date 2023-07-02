package me.janek.catalog.domain.catalog;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@SpringBootTest
class CatalogServiceTest {

    @Autowired
    CatalogService catalogService;

    @Test
    @DisplayName("")
    void test() {
        // when
        var allCatalogs = catalogService.getAllCatalogs();

        // expect
        assertEquals(allCatalogs.size(), 3);
    }

}