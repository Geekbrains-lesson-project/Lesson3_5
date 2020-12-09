package geekbrains.lesson3_5gb.machines;


import geekbrains.lesson3_5gb.fuelstation.FuelStation;

public class Car extends Machine {
    private final float consumption = 2.5f;
    private final float volume = 20f;

    public Car(String id, FuelStation fuelStation) {
        super.setConsumption(consumption);
        super.setVolume(volume);
        super.setId(id);
        super.setLevel(volume);
        super.setFuelStation(fuelStation);
    }
}