package geekbrains.lesson3_5gb.machines;


import geekbrains.lesson3_5gb.fuelstation.FuelStation;

public class Bus extends Machine {
    private final float consumption = 7.5f;
    private final float volume = 40f;

    public Bus(String id, FuelStation fuelStation) {
        super.setConsumption(consumption);
        super.setVolume(volume);
        super.setId(id);
        super.setLevel(volume);
        super.setFuelStation(fuelStation);
    }
}
