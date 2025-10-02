package BajES_Math;

public class complexNumber {
    double realPart;
    double imaginaryPart;
    public complexNumber(double realPart, double imaginaryPart) {
        this.realPart = realPart;
        this.imaginaryPart = imaginaryPart;
    }
    public double getRealPart() {
        return realPart;
    }
    public double getImaginaryPart() {
        return imaginaryPart;
    }
    public double magnitude() {
        return Math.sqrt(realPart * realPart + imaginaryPart * imaginaryPart);
    }
    public double argument(){
        return Math.atan2(imaginaryPart, realPart);
    }
    
    public complexNumber conjugateNumber() {
        return new complexNumber(realPart, -imaginaryPart);
    }
    public boolean equals(complexNumber other) {
        return this.realPart == other.realPart && this.imaginaryPart == other.imaginaryPart;
    }
    public String toString() {
        if (imaginaryPart >= 0) {
            return realPart + " + " + imaginaryPart + "i";
        } else {
            return realPart + " - " + (-imaginaryPart) + "i";
        }
    }
    public complexNumber parseComplexNumber(String str) {
        str = str.replace(" ", "").toLowerCase();
        if (str.endsWith("i")) {
            str = str.substring(0, str.length() - 1);
            String[] parts = str.split("\\+|(?=-)");
            double real = 0;
            double imaginary = 0;
            for (String part : parts) {
                if (part.contains("i")) {
                    imaginary += Double.parseDouble(part.replace("i", ""));
                } else {
                    real += Double.parseDouble(part);
                }
            }
            return new complexNumber(real, imaginary);
        } else {
            double real = Double.parseDouble(str);
            return new complexNumber(real, 0);
        }
    }
    public complexNumber addToComplexNumber(complexNumber other) {
        return new complexNumber(this.realPart + other.realPart, this.imaginaryPart + other.imaginaryPart);
    }
    public complexNumber subtractFromComplexNumber(complexNumber other) {
        return new complexNumber(this.realPart - other.realPart, this.imaginaryPart - other.imaginaryPart);
    }
    public complexNumber multiplyWithComplexNumber(complexNumber other) {
        double real = this.realPart * other.realPart - this.imaginaryPart * other.imaginaryPart;
        double imaginary = this.realPart * other.imaginaryPart + this.imaginaryPart * other.realPart;
        return new complexNumber(real, imaginary);
    }
    public complexNumber divideByComplexNumber(complexNumber other) {
        double denominator = other.realPart * other.realPart + other.imaginaryPart * other.imaginaryPart;
        if (denominator == 0) {
            throw new ArithmeticException("Division by zero complex number");
        }
        double real = (this.realPart * other.realPart + this.imaginaryPart * other.imaginaryPart) / denominator;
        double imaginary = (this.imaginaryPart * other.realPart - this.realPart * other.imaginaryPart) / denominator;
        return new complexNumber(real, imaginary);
    }
    public complexNumber reciprocal() {
        double denominator = realPart * realPart + imaginaryPart * imaginaryPart;
        if (denominator == 0) {
            throw new ArithmeticException("Reciprocal of zero complex number");
        }
        return new complexNumber(realPart / denominator, -imaginaryPart / denominator);
    }
    public complexNumber negate() {
        return new complexNumber(-realPart, -imaginaryPart);
    }
    public complexNumber complexNumberAddition(complexNumber a, complexNumber b) {
        return new complexNumber(a.realPart + b.realPart, a.imaginaryPart + b.imaginaryPart);
    }
    public complexNumber complexNumberSubtraction(complexNumber a, complexNumber b) {
        return new complexNumber(a.realPart - b.realPart, a.imaginaryPart - b.imaginaryPart);
    }
    public complexNumber complexNumberMultiplication(complexNumber a, complexNumber b) {
        double real = a.realPart * b.realPart - a.imaginaryPart * b.imaginaryPart;
        double imaginary = a.realPart * b.imaginaryPart + a.imaginaryPart * b.realPart;
        return new complexNumber(real, imaginary);
    }
    public complexNumber complexNumberDivision(complexNumber a, complexNumber b) {
        double denominator = b.realPart * b.realPart + b.imaginaryPart * b.imaginaryPart;
        if (denominator == 0) {
            throw new ArithmeticException("Division by zero complex number");
        }
        double real = (a.realPart * b.realPart + a.imaginaryPart * b.imaginaryPart) / denominator;
        double imaginary = (a.imaginaryPart * b.realPart - a.realPart * b.imaginaryPart) / denominator;
        return new complexNumber(real, imaginary);
    }
    public complexNumber pow(complexNumber exponent) {
        return this.ln().multiplyWithComplexNumber(exponent).exp();
    }
    public complexNumber ln() {
        double r = this.magnitude();
        double theta = this.argument();
        return new complexNumber(Math.log(r), theta);
    }

    public complexNumber exp() {
        double eToA = Math.exp(this.realPart);
        double real = eToA * Math.cos(this.imaginaryPart);
        double imag = eToA * Math.sin(this.imaginaryPart);
        return new complexNumber(real, imag);
    }
    public complexNumber sin() {
        double real = Math.sin(this.realPart) * Math.cosh(this.imaginaryPart);
        double imag = Math.cos(this.realPart) * Math.sinh(this.imaginaryPart);
        return new complexNumber(real, imag);
    }

    public complexNumber cos() {
        double real = Math.cos(this.realPart) * Math.cosh(this.imaginaryPart);
        double imag = -Math.sin(this.realPart) * Math.sinh(this.imaginaryPart);
        return new complexNumber(real, imag);
    }

    public complexNumber tan() {
        return this.sin().divideByComplexNumber(this.cos());
    }
}
