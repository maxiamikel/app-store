package com.maxi.backstore.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.maxi.backstore.entities.Region;
import com.maxi.backstore.services.region.RegionServiceImplementation;

@RestController
@RequestMapping("/api/v1/regions")
public class RegionController {

    @Autowired
    private RegionServiceImplementation regionService;

    @PostMapping("/create")
    public ResponseEntity<Region> createRegion(@RequestBody Region region) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(region.getId()).toUri();
        return ResponseEntity.created(uri).body(regionService.createRegion(region));
    }

    @GetMapping("/")
    public ResponseEntity<List<Region>> findAllRegions() {
        return ResponseEntity.ok().body(regionService.findAllRegions());
    }

    @GetMapping("/find/{name}")
    public ResponseEntity<Region> findByName(@PathVariable String name) {
        return ResponseEntity.ok().body(regionService.findByName(name));
    }
}
