package BajES_Math;

public abstract class primordialFunction {
    protected String variable; // usually "x"

    public primordialFunction(String variable) {
        this.variable = variable;
    }

    public abstract complexNumber evaluate(complexNumber input);

    public String getVariable() {
        return variable;
    }

    public abstract String toString();
}


