package creationalpatterns;

import org.jetbrains.annotations.NotNull;

public class Builder {
    private final int number;
    private final String name;

    private Builder(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public static void main(String[] args) {
        InnerBuilder innerBuilder = new InnerBuilder();
        Director director = new Director();
        director.make(innerBuilder);
        Builder builder = innerBuilder.build();
        System.out.println(builder);
    }

    @Override
    public String toString() {
        return "Builder{" +
                "number=" + number +
                ", name='" + name + '\'' +
                '}';
    }

    interface IInnerBuilder {
        IInnerBuilder reset();

        IInnerBuilder setNumber(int number);

        InnerBuilder setName(String name);
    }

    static class InnerBuilder implements IInnerBuilder {
        private int number = 0;
        private String name = null;

        @Override
        public IInnerBuilder reset() {
            number = 0;
            name = null;
            return this;
        }

        @Override
        public InnerBuilder setNumber(int number) {
            this.number = number;
            return this;
        }

        @Override
        public InnerBuilder setName(String name) {
            this.name = name;
            return this;
        }

        Builder build() {
            if (number == 0)
                throw new IllegalStateException("No set number");
            if (name == null)
                throw new IllegalStateException("No set name");
            return new Builder(number, name);
        }
    }

    static class Director {
        public void make(@NotNull IInnerBuilder iInnerBuilder) {
            iInnerBuilder.reset()
                    .setName("Name")
                    .setNumber(23);
        }
    }
}
