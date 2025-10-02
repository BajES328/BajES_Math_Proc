package BajES_Math;

public class rationalTerm extends primordialFunction {
    private complexNumber numerator;
    private complexNumber denominatorExponent;

    public rationalTerm(complexNumber numerator, complexNumber denominatorExponent) {
        super("x");
        this.numerator = numerator;
        this.denominatorExponent = denominatorExponent;
    }

    @Override
    public complexNumber evaluate(complexNumber input) {
        return numerator.divideByComplexNumber(input.pow(denominatorExponent));
    }

    @Override
    public String toString() {
        return numerator + "/" + variable + "^(" + denominatorExponent + ")";
    }
}


