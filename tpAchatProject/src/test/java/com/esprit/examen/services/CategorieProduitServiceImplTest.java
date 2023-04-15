package com.esprit.examen.services;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.repositories.CategorieProduitRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CategorieProduitServiceImplTest {
    @Autowired
    public CategorieProduitServiceImpl service;
    @MockBean
    public CategorieProduitRepository repo;
    @Test
    public void retrieveAllCategorieProduitsTest() {
        CategorieProduit cp = new CategorieProduit(1L,"test", "test",null);
        CategorieProduit cp2 = new CategorieProduit(2L,"test2", "test",null);
        service.addCategorieProduit(cp2);
        service.addCategorieProduit(cp);
        service.retrieveAllCategorieProduits();
        assertEquals(2, service.retrieveAllCategorieProduits().size());
    }

}
