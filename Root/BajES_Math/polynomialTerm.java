package BajES_Math;

public class polynomialTerm extends primordialFunction {
    private polynomial poly;            // optional: full polynomial
    private complexNumber coefficient;  // optional: single-term coefficient
    private complexNumber exponent;     // optional: single-term exponent

    // -----------------------------
    // Constructor for single-term (coefficient * x^exponent)
    // -----------------------------
    public polynomialTerm(complexNumber coefficient, complexNumber exponent) {
        super("x");
        this.coefficient = coefficient;
        this.exponent = exponent;
        this.poly = null; // not using full polynomial
    }

    // -----------------------------
    // Constructor for full polynomial
    // -----------------------------
    public polynomialTerm(polynomial poly) {
        super("x");
        this.poly = poly;
        this.coefficient = null;
        this.exponent = null;
    }

    // -----------------------------
    // Evaluate the term at input
    // -----------------------------
    @Override
    public complexNumber evaluate(complexNumber input) {
        if (poly != null) {
            return poly.evaluateAt(input);
        } else {
            // single-term: coefficient * input^exponent
            return coefficient.multiplyWithComplexNumber(input.pow(exponent));
        }
    }

    // -----------------------------
    // Symbolic string
    // -----------------------------
    @Override
    public String toString() {
        if (poly != null) {
            return poly.toString();
        } else {
            return coefficient + "*" + variable + "^(" + exponent + ")";
        }
    }
}
