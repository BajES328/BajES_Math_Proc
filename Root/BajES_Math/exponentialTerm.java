package BajES_Math;

public class exponentialTerm extends primordialFunction {
    private complexNumber coefficient;
    private complexNumber base;

    public exponentialTerm(complexNumber coefficient, complexNumber base) {
        super("x");
        this.coefficient = coefficient;
        this.base = base;
    }

    @Override
    public complexNumber evaluate(complexNumber input) {
        return coefficient.multiplyWithComplexNumber(base.pow(input));
    }

    @Override
    public String toString() {
        return coefficient + "*" + base + "^" + variable;
    }
}


