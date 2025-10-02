import BajES_Math.complexNumber;
import BajES_Math.mathFunction;
import BajES_Math.polynomial;
import BajES_Math.polynomialTerm;
import BajES_Math.primordialFunction;
import BajES_Math.trigonometricTerm;

public class MainMathProcessor {
    public static void main(String[] args) {
        // --------------------
        // 1. Create a polynomial: 2x + 3
        // --------------------
        complexNumber[] coeffs = {
            new complexNumber(2, 0),  // coefficient of x^1
            new complexNumber(3, 0)   // coefficient of x^0
        };
        primordialFunction polyTerm = new polynomialTerm(new polynomial(coeffs));

        // --------------------
        // 2. Create a trigonometric term: sin(x)
        // --------------------
        primordialFunction trigTerm = new trigonometricTerm(new complexNumber(1, 0), trigonometricTerm.Type.sin);

        // --------------------
        // 3. Wrap them in mathFunction
        // --------------------
        mathFunction fPoly = new mathFunction(polyTerm);
        mathFunction fTrig = new mathFunction(trigTerm);

        // --------------------
        // 4. Combine: (poly + trig)^2
        // --------------------
        mathFunction combined = fPoly.add(fTrig).pow(new complexNumber(2, 0));

        // --------------------
        // 5. Evaluate at x = 1 + i
        // --------------------
        complexNumber x = new complexNumber(1, 1);
        complexNumber result = combined.evaluate(x);

        // --------------------
        // 6. Print symbolic expression and result
        // --------------------
        System.out.println("Function: " + combined);
        System.out.println("f(" + x + ") = " + result);
    }
}