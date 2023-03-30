package creationalpatterns;

/**
 * Factory Method is a creational design pattern that provides an interface for creating objects in
 * a superclass, but allows subclasses to alter the type of objects that will be created.
 */
public class Factory {
    public static void main(String[] args) {
        TransportLogicstics roadLogistics = new RoadLogistics();
        TransportLogicstics seaLogisitcs = new SeaLogistics();
        Transport truckTransport = roadLogistics.createTransport();
        Transport shipTransport = seaLogisitcs.createTransport();
        System.out.println("Road logistics are using: " + truckTransport.getClass().getName());
        System.out.println("Sea logistics are using: " + shipTransport.getClass().getName());
        truckTransport.delievr();
        shipTransport.delievr();
    }

    interface Transport {
        void delievr();
    }

    static class Truck implements Transport {

        @Override
        public void delievr() {
            System.out.println("deliver cargo by land/road");
        }
    }

    static class Ship implements Transport {

        @Override
        public void delievr() {
            System.out.println("deliver cargo by sea");
        }
    }

    static abstract class TransportLogicstics {
        public void planDeliver() {
        }

        abstract Transport createTransport();
    }

    static class RoadLogistics extends TransportLogicstics {
        @Override
        Transport createTransport() {
            return new Truck();
        }
    }

    static class SeaLogistics extends TransportLogicstics {
        @Override
        Transport createTransport() {
            return new Ship();
        }
    }
}
