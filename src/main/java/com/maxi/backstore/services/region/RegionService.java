package com.maxi.backstore.services.region;

import java.util.List;

import com.maxi.backstore.entities.Region;

public interface RegionService {
    public Region findByName(String name);

    public List<Region> findAllRegions();

    public Region createRegion(Region region);
}
