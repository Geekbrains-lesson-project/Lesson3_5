package geekbrains.lesson3_5gb.machines;


import geekbrains.lesson3_5gb.fuelstation.FuelStation;

public class Truck extends Machine {
    private final float consumption = 15f;
    private final float volume = 60f;

    public Truck(String id, FuelStation fuelStation) {
        super.setConsumption(consumption);
        super.setVolume(volume);
        super.setId(id);
        super.setLevel(volume);
        super.setFuelStation(fuelStation);
    }
}