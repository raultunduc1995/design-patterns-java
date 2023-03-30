package behavioralpatterns;

/**
 * Strategy is a behavioral design pattern that lets you define a family of algorithms,
 * put each of them into a separate class, and make their objects interchangeable.
 */
public final class Strategy {
    public static void main(String[] args) {
        Navigator navigator = new Navigator();
        navigator.setStrategy(new RoadStrategy());
        navigator.navigateTo(1, 1);
        navigator.setStrategy(new WalkingStrategy());
        navigator.navigateTo(100, 15);
    }

    private interface RouteStrategy {
        void buildRoute(int pointX, int pointY);
    }

    private static final class Navigator {
        private RouteStrategy strategy;

        public Navigator() {
        }

        public void setStrategy(RouteStrategy strategy) {
            this.strategy = strategy;
        }

        public void navigateTo(int pointX, int pointY) {
            strategy.buildRoute(pointX, pointY);
        }
    }

    private static final class RoadStrategy implements RouteStrategy {
        @Override
        public void buildRoute(int pointX, int pointY) {
        }
    }

    private static final class WalkingStrategy implements RouteStrategy {
        @Override
        public void buildRoute(int pointX, int pointY) {
        }
    }
}
