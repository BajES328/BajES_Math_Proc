package BajES_Math;

public class logarithmicTerm extends primordialFunction {
    private complexNumber coefficient;
    private complexNumber base;

    public logarithmicTerm(complexNumber coefficient, complexNumber base) {
        super("x");
        this.coefficient = coefficient;
        this.base = base;
    }

    @Override
    public complexNumber evaluate(complexNumber input) {
        return coefficient.multiplyWithComplexNumber(input.ln().divideByComplexNumber(base.ln()));
    }

    @Override
    public String toString() {
        return coefficient + "*log_(" + base + ")(" + variable + ")";
    }
}

