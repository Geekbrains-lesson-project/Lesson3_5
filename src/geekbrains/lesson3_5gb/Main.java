package geekbrains.lesson3_5gb;

import geekbrains.lesson3_5gb.fuelstation.FuelStation;
import geekbrains.lesson3_5gb.machines.Bus;
import geekbrains.lesson3_5gb.machines.Car;
import geekbrains.lesson3_5gb.machines.Truck;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        FuelStation fuelStation = new FuelStation();
        Car car1 = new Car("Audi", fuelStation);
        Car car2 = new Car("Bugatti", fuelStation);
        Car car3 = new Car("BMW", fuelStation);
        Car car4 = new Car("Renault", fuelStation);
        Car car5 = new Car("Land Rover", fuelStation);

        Bus bus1 = new Bus("Школьный автобус", fuelStation);
        Bus bus2 = new Bus("Маршрут №207",fuelStation);

        Truck truck2 = new Truck("Камаз", fuelStation);
        Truck truck1 = new Truck("MAN", fuelStation);

        ExecutorService executorService = Executors.newFixedThreadPool(9);
        executorService.submit(car1);
        executorService.submit(car2);
        executorService.submit(car3);
        executorService.submit(car4);
        executorService.submit(car5);

        executorService.submit(bus1);
        executorService.submit(bus2);

        executorService.submit(truck1);
        executorService.submit(truck2);

        executorService.shutdown();

    }
}
