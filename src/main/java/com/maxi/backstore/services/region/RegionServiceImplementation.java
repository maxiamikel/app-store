package com.maxi.backstore.services.region;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxi.backstore.entities.Region;
import com.maxi.backstore.repositories.RegionRepository;

@Service
public class RegionServiceImplementation implements RegionService {

    @Autowired
    private RegionRepository regionRepository;

    @Override
    public Region findByName(String name) {
        Region region = regionRepository.findByName(name);
        return region;
    }

    @Override
    public List<Region> findAllRegions() {
        List<Region> regionsList = new ArrayList<>();
        regionsList = regionRepository.findAllRegions();
        return regionsList;
    }

    @Override
    public Region createRegion(Region region) {
        Region regionDB = this.findByName(null);
        if (regionDB != null) {
            return regionDB;
        }
        regionDB = regionRepository.save(region);
        return regionDB;
    }

}
