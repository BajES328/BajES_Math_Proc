package BajES_Math;

public class trigonometricTerm extends primordialFunction {
    public enum Type { sin, cos, tan } // lowercase to match style
    private complexNumber coefficient;
    private Type type;

    public trigonometricTerm(complexNumber coefficient, Type type) {
        super("x");
        this.coefficient = coefficient;
        this.type = type;
    }

    @Override
    public complexNumber evaluate(complexNumber input) {
        switch (type) {
            case sin:
                return coefficient.multiplyWithComplexNumber(input.sin());
            case cos:
                return coefficient.multiplyWithComplexNumber(input.cos());
            case tan:
                return coefficient.multiplyWithComplexNumber(input.tan());
            default:
                throw new IllegalArgumentException("Unknown trig type");
        }
    }

    @Override
    public String toString() {
        return coefficient + "*" + type.name() + "(" + variable + ")";
    }
}


