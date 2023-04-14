package com.esprit.examen.services;

import static org.junit.Assert.*;

import java.util.*;


import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.entities.DetailFacture;
import com.esprit.examen.entities.Stock;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.Produit;

import lombok.extern.slf4j.Slf4j;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@ExtendWith(MockitoExtension.class)
public class ProduitServiceImplTest {
    @Autowired
    IProduitService ProduitService;


    @Test
    public void testAddProduit() {

        Date dateFormat = new Date("dd/MM/yyyy");
        Stock stock = new Stock();
        CategorieProduit categorieProduit = new CategorieProduit();
        Set<DetailFacture> detailFactures = new Set<DetailFacture>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<DetailFacture> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(DetailFacture detailFacture) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends DetailFacture> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }
        };
        Produit p = new Produit(null, "code", "libelleProduit", 3, dateFormat, dateFormat, stock, detailFactures, categorieProduit );
        Produit Produit = ProduitService.addProduit(p);
        System.out.print("Produit "+Produit);
        assertNotNull(Produit.getIdProduit());
        assertTrue(Produit.getCodeProduit().length() > 0);
        ProduitService.deleteProduit(Produit.getIdProduit());

    }
    @Test
    public void testDeleteProduit() {
        Date dateFormat = new Date("dd/MM/yyyy");
        Stock stock = new Stock();
        CategorieProduit categorieProduit = new CategorieProduit();
        Set<DetailFacture> detailFactures = new Set<DetailFacture>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<DetailFacture> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(DetailFacture detailFacture) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends DetailFacture> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }
        };
        Produit p = new Produit(null, "code", "libelleProduit", 3, dateFormat, dateFormat, stock, detailFactures, categorieProduit );
        Produit Produit = ProduitService.addProduit(p);
        ProduitService.deleteProduit(Produit.getIdProduit());
        assertNull(ProduitService.retrieveProduit(Produit.getIdProduit()));
    }



}
