package com.maxi.backstore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.maxi.backstore.entities.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {

    public Region findByName(String name);

    @Query("SELECT r FROM Region r")
    public List<Region> findAllRegions();
}
