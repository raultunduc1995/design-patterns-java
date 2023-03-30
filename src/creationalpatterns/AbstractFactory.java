package creationalpatterns;

/**
 * Abstract Factory is a creational design pattern that lets you produce families of related objects
 * without specifying their concrete classes.
 */
public class AbstractFactory {
    public static void main(String[] args) {
        var isWindows = true;
        GUIFactory factory;
        if (isWindows) {
            factory = new WinFactory();
        } else {
            factory = new LinuxFactory();
        }
        Button winButton = factory.createButton();
        Checkbox linuxCheckbox = factory.createCheckbox();
        System.out.println(winButton.getClass().getName());
        System.out.println(linuxCheckbox.getClass().getName());
    }

    interface Button {
    }

    interface Checkbox {
    }

    interface GUIFactory {
        Button createButton();

        Checkbox createCheckbox();
    }

    static class WinButton implements Button {
    }

    static class LinuxButton implements Button {
    }

    static class WinCheckbox implements Checkbox {
    }

    static class LinuxCheckbox implements Checkbox {
    }

    static class WinFactory implements GUIFactory {
        @Override
        public Button createButton() {
            return new WinButton();
        }

        @Override
        public Checkbox createCheckbox() {
            return new WinCheckbox();
        }
    }

    static class LinuxFactory implements GUIFactory {
        @Override
        public Button createButton() {
            return new LinuxButton();
        }

        @Override
        public Checkbox createCheckbox() {
            return new LinuxCheckbox();
        }
    }
}
