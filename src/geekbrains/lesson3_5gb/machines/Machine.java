package geekbrains.lesson3_5gb.machines;

import geekbrains.lesson3_5gb.fuelstation.FuelStation;

public abstract class Machine implements Runnable {
    private static final int consumptionPeriod = 3000;
    private String id;
    private float volume;
    private float consumption;
    private float level;
    private FuelStation fuelStation;

    public void setFuelStation(FuelStation fuelStation) {
        this.fuelStation = fuelStation;
    }

    public float getLevel() {
        return level;
    }

    public void setLevel(float level) {
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public float getVolume() {
        return volume;
    }

    protected void setId(String id) {
        this.id = id;
    }

    protected void setVolume(float volume) {
        this.volume = volume;
    }

    protected void setConsumption(float consumption) {
        this.consumption = consumption;
    }

    public void go() {
        try {
            System.out.printf("\n%s начал(а) движение", id);
            while (level >= consumption) {
                System.out.printf("\n%s едет. Топлива в баке %s.", id, level);
                Thread.sleep(consumptionPeriod);
                level -= consumption;
            }
            System.out.printf("\n%s нужна дозаправка.", id);
            fuelStation.arrivalToTheFuelStation(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        go();
    }
}
