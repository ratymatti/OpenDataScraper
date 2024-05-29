package com.of.scraper.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.of.scraper.entity.Data;
import com.of.scraper.entity.Fish;
import com.of.scraper.repository.DataRepository;
import com.of.scraper.repository.FishRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FishServiceImpl implements FishService {

    private FishRepository fishRepository;
    private DataRepository dataRepository;
    
    @Override
    public Fish save(Fish fish) {
        return fishRepository.save(fish);
    }

    @Override
    public String convertDataToFish() {
        List<Data> fishesAsData = dataRepository.findAll();

        int count  = 0;

        for (Data data : fishesAsData) {
            fishRepository.save(convertDataEntityToFishEntity(data));
            count++;
        }

        return "Converted " + count + " entities succesfully.";
    }

    private Fish convertDataEntityToFishEntity(Data data) {
        Fish fish = new Fish();

        fish.setLocation(data.getLocation());

        fish.setDate(data.getLocalDate());

        fish.setWeight(data.getWeight());

        fish.setSpecies(data.getSpecies());

        fish.setGear(data.getGear());

        fish.setZone(data.getZone());

        fish.setName(data.getName());

        return fish;
    }
}
