public class Car {
        // Immutable fields
        private final int year;
        private final String make;
        private final String model;

        // Static field to track the number of cars built
        private static int numberBuilt = 0;

        // Constructor to initialize fields
        public Car(int year, String make, String model) {
            this.year = year;
            this.make = make;
            this.model = model;
            numberBuilt++; // Increment the counter each time a new car is built
        }

        // Getter methods (No setters for immutability)
        public int getYear() {
            return year;
        }

        public String getMake() {
            return make;
        }

        public String getModel() {
            return model;
        }

        public static int getNumberBuilt() {
            return numberBuilt;
        }
    }

