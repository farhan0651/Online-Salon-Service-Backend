package com.capg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.entity.Card;

public interface ICardRepository extends JpaRepository<Card, Long > {

}