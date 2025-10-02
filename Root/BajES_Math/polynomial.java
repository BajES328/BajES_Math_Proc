package BajES_Math;

public class polynomial {
    complexNumber[] coefficients; // Coefficients are stored from highest degree to lowest degree

    public polynomial(complexNumber[] coefficients) {
        this.coefficients = coefficients;
    }

    public int degree() {
        // Check for the zero polynomial (all coefficients zero)
        for (int i = 0; i < coefficients.length; i++) {
            complexNumber coeff = coefficients[i];
            if (coeff.getRealPart() != 0 || coeff.getImaginaryPart() != 0) {
                return coefficients.length - 1 - i;
            }
        }
        // All coefficients are zero: degree is undefined, return -1
        return -1;
    }

    public complexNumber evaluateAt(complexNumber x) {
        complexNumber result = new complexNumber(0, 0);
        for (int i = 0; i < coefficients.length; i++) {
            complexNumber term = coefficients[i];
            for (int j = 0; j < coefficients.length - 1 - i; j++) {
                term = term.multiplyWithComplexNumber(x);
            }
            result = result.addToComplexNumber(term);
        }
        return result;
    }

    public polynomial add(polynomial other) {
        int maxDegree = Math.max(this.degree(), other.degree());
        complexNumber[] newCoefficients = new complexNumber[maxDegree + 1];
        for (int i = 0; i <= maxDegree; i++) {
            complexNumber coeff1 = (i <= this.degree()) ? this.coefficients[this.degree() - i] : new complexNumber(0, 0);
            complexNumber coeff2 = (i <= other.degree()) ? other.coefficients[other.degree() - i] : new complexNumber(0, 0);
            newCoefficients[maxDegree - i] = coeff1.addToComplexNumber(coeff2);
        }
        return new polynomial(newCoefficients);
    }

    public polynomial multiply(polynomial other) {
        int newDegree = this.degree() + other.degree();
        complexNumber[] newCoefficients = new complexNumber[newDegree + 1];
        for (int i = 0; i <= newDegree; i++) {
            newCoefficients[i] = new complexNumber(0, 0);
        }
        for (int i = 0; i <= this.degree(); i++) {
            for (int j = 0; j <= other.degree(); j++) {
                int degreeIndex = (this.degree() - i) + (other.degree() - j);
                newCoefficients[newDegree - degreeIndex] = newCoefficients[newDegree - degreeIndex]
                        .addToComplexNumber(this.coefficients[i].multiplyWithComplexNumber(other.coefficients[j]));
            }
        }
        return new polynomial(newCoefficients);
    }
    public polynomial scalarMultiply(complexNumber scalar) {
        complexNumber[] newCoefficients = new complexNumber[this.coefficients.length];
        for (int i = 0; i < this.coefficients.length; i++) {
            newCoefficients[i] = this.coefficients[i].multiplyWithComplexNumber(scalar);
        }
        return new polynomial(newCoefficients);
    }
    public polynomial scalarAdd(complexNumber scalar) {
        complexNumber[] newCoefficients = new complexNumber[this.coefficients.length];
        for (int i = 0; i < this.coefficients.length - 1; i++) {
            newCoefficients[i] = this.coefficients[i];
        }
        newCoefficients[this.coefficients.length - 1] = this.coefficients[this.coefficients.length - 1].addToComplexNumber(scalar);
        return new polynomial(newCoefficients);
    }
    public polynomial scalarSubtract(complexNumber scalar) {
        complexNumber[] newCoefficients = new complexNumber[this.coefficients.length];
        for (int i = 0; i < this.coefficients.length - 1; i++) {
            newCoefficients[i] = this.coefficients[i];
        }
        newCoefficients[this.coefficients.length - 1] = this.coefficients[this.coefficients.length - 1].subtractFromComplexNumber(scalar);
        return new polynomial(newCoefficients);
    }
    public polynomial scalarDivide(complexNumber scalar) {
        complexNumber[] newCoefficients = new complexNumber[this.coefficients.length];
        for (int i = 0; i < this.coefficients.length; i++) {
            newCoefficients[i] = this.coefficients[i].divideByComplexNumber(scalar);
        }
        return new polynomial(newCoefficients);
    }
    public polynomial negate() {
        complexNumber[] newCoefficients = new complexNumber[this.coefficients.length];
        for (int i = 0; i < this.coefficients.length; i++) {
            newCoefficients[i] = this.coefficients[i].multiplyWithComplexNumber(new complexNumber(-1, 0));
        }
        return new polynomial(newCoefficients);
    }
    public polynomial addToPolynomial(polynomial other) {
        return this.add(other);
    }
    public polynomial multiplyWithPolynomial(polynomial other) {
        return this.multiply(other);
    }
    public polynomial subtractFromPolynomial(polynomial other) {
        return this.add(other.negate());
    }
    public polynomial derivative() {
        if (this.degree() == 0) {
            return new polynomial(new complexNumber[]{new complexNumber(0, 0)});
        }
        complexNumber[] newCoefficients = new complexNumber[this.degree()];
        for (int i = 0; i < this.degree(); i++) {
            int power = this.degree() - i;
            newCoefficients[i] = this.coefficients[i].multiplyWithComplexNumber(new complexNumber(power, 0));
        }
        return new polynomial(newCoefficients);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int deg = degree();
        if (deg == -1) return "0";

        for (int i = 0; i < coefficients.length; i++) {
            complexNumber c = coefficients[i];
            if (c.getRealPart() == 0 && c.getImaginaryPart() == 0) continue;

            if (sb.length() > 0) sb.append(" + ");

            if (i < deg) sb.append("(").append(c).append(")*x^").append(deg - i);
            else sb.append(c);
        }
        return sb.toString();
    }
}