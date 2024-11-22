//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Main {

    public static void main(String[] args) {
        // Create Car objects
        Car car1 = new Car(2020, "Toyota", "Corolla");
        Car car2 = new Car(2022, "Honda", "Civic");
        Car car3 = new Car(2023, "Toyota", "Venza");

        // Display the car information
        System.out.println("Car 1: " + car1.getYear() + " " + car1.getMake() + " " + car1.getModel());
        System.out.println("Car 2: " + car2.getYear() + " " + car2.getMake() + " " + car2.getModel());
        System.out.println("Car 3: " + car3.getYear() + " " + car3.getMake() + " " + car3.getModel());

        // Display the number of cars built
        System.out.println("Number of cars built: " + Car.getNumberBuilt());

    }
}

