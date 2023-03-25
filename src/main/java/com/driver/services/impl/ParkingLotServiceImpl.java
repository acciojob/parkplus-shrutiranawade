package com.driver.services.impl;

import com.driver.model.ParkingLot;
import com.driver.model.Spot;
import com.driver.model.SpotType;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.SpotRepository;
import com.driver.services.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {
    @Autowired
    ParkingLotRepository parkingLotRepository1;
    @Autowired
    SpotRepository spotRepository1;
    @Override
    public ParkingLot addParkingLot(String name, String address) {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName(name);
        parkingLot.setAddress(address);
      parkingLotRepository1.save(parkingLot);
      return parkingLot;
    }

    @Override
    public Spot addSpot(int parkingLotId, Integer numberOfWheels, Integer pricePerHour) {
        ParkingLot parkingLot = parkingLotRepository1.findById(parkingLotId).get();
Spot spot= new Spot();
spot.setPricePerHour(pricePerHour);
if(numberOfWheels <=2){
    spot.setSpotType(SpotType.TWO_WHEELER);
}
else if(numberOfWheels<=4){
    spot.setSpotType(SpotType.FOUR_WHEELER);
}
else {
    spot.setSpotType(SpotType.OTHERS);
}
spot.setParkingLot(parkingLot);
spot.setOccupied(false);
parkingLot.getSpotList().add(spot);
parkingLotRepository1.save(parkingLot);
return spot;
    }

    @Override
    public void deleteSpot(int spotId) {
spotRepository1.deleteById(spotId);
    }

    @Override
    public Spot updateSpot(int parkingLotId, int spotId, int pricePerHour) {
ParkingLot parkingLot = parkingLotRepository1.findById(parkingLotId).get();
Spot spot=null;
for(Spot s : parkingLot.getSpotList()){
    if (spotId==s.getId()){
    s.setPricePerHour(pricePerHour);
    spotRepository1.save(s);
    spot = s;
    break;
    }
}
    return spot;}

    @Override
    public void deleteParkingLot(int parkingLotId) {
parkingLotRepository1.deleteById(parkingLotId);
    }
}
