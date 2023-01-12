package com.example.listingbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.listingbackend.model.Item;


public interface PostingRepo extends JpaRepository<Item,String> {
}
