package com.driver.services.impl;

import com.driver.model.*;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.ReservationRepository;
import com.driver.repository.SpotRepository;
import com.driver.repository.UserRepository;
import com.driver.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    UserRepository userRepository3;
    @Autowired
    SpotRepository spotRepository3;
    @Autowired
    ReservationRepository reservationRepository3;
    @Autowired
    ParkingLotRepository parkingLotRepository3;
    @Override
    public Reservation reserveSpot(Integer userId, Integer parkingLotId, Integer timeInHours, Integer numberOfWheels) throws Exception {
User user;
ParkingLot parkingLot;
try {
    user = userRepository3.findById(userId).get();
    parkingLot = parkingLotRepository3.findById(parkingLotId).get();
}catch (Exception e){
    throw new Exception("Cannot make reservation");
}
List<Spot>spotList = parkingLot.getSpotList();
Spot bookedSpot = null;
int minPrice = Integer.MAX_VALUE;

for(Spot s :spotList){
    if(!s.getOccupied()){
        if(SpotTypeUtil(s.getSpotType())>= numberOfWheels){
            if(s.getPricePerHour()*timeInHours < minPrice){
                minPrice = s.getPricePerHour()*timeInHours;
                bookedSpot = s;

            }
        }
    }
}
if (bookedSpot ==null)throw new Exception("Cannot make reservation");

Reservation reservation = new Reservation();
reservation.setSpot(bookedSpot);
reservation.setUser(user);
reservation.setNoOfHours(timeInHours);

bookedSpot.getReservationList().add(reservation);
bookedSpot.setOccupied(true);
user.getReservationList().add(reservation);
parkingLot.getSpotList().add(bookedSpot);

userRepository3.save(user);
spotRepository3.save(bookedSpot);
return reservation;
    }
    private int SpotTypeUtil(SpotType spotType){
        if (spotType == SpotType.TWO_WHEELER)return 2;
        if (spotType ==SpotType.FOUR_WHEELER)return 4;
        return Integer.MAX_VALUE;
    }
}
