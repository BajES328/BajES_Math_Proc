package BajES_Math;

public class mathFunction {
    private functionNode root;

    // =====================
    // Public constructor from a single term
    // =====================
    public mathFunction(primordialFunction term) {
        this.root = new functionNode(term);
    }

    // =====================
    // Private constructor from a functionNode (for internal operations)
    // =====================
    private mathFunction(functionNode node) {
        this.root = node;
    }

    // =====================
    // Arithmetic operations
    // =====================
    public mathFunction add(mathFunction other) {
        return new mathFunction(new functionNode(this.root, other.root, Operator.ADD));
    }

    public mathFunction subtract(mathFunction other) {
        return new mathFunction(new functionNode(this.root, other.root, Operator.SUBTRACT));
    }

    public mathFunction multiply(mathFunction other) {
        return new mathFunction(new functionNode(this.root, other.root, Operator.MULTIPLY));
    }

    public mathFunction divide(mathFunction other) {
        return new mathFunction(new functionNode(this.root, other.root, Operator.DIVIDE));
    }

    // =====================
    // Unary operations
    // =====================
    public mathFunction sqrt() {
        return new mathFunction(functionNode.sqrt(root));
    }

    public mathFunction pow(complexNumber exponent) {
        return new mathFunction(functionNode.pow(root, exponent));
    }

    // =====================
    // Evaluation & display
    // =====================
    public complexNumber evaluate(complexNumber input) {
        return root.evaluate(input);
    }

    @Override
    public String toString() {
        return root.toString();
    }

    // =====================
    // Internal classes
    // =====================
    private enum Operator { ADD, SUBTRACT, MULTIPLY, DIVIDE, SQRT, POW }

    private static class functionNode {
        primordialFunction term;        // non-null for leaf nodes
        functionNode left, right;       // children for operation nodes
        Operator op;
        complexNumber exponent;         // used for POW only

        // ---------------------
        // Leaf node constructor
        // ---------------------
        functionNode(primordialFunction term) {
            this.term = term;
            this.op = null;
        }

        // ---------------------
        // Binary operation node
        // ---------------------
        functionNode(functionNode left, functionNode right, Operator op) {
            this.left = left;
            this.right = right;
            this.op = op;
            this.term = null;
        }

        // ---------------------
        // Private unary constructor (for sqrt and pow)
        // ---------------------
        private functionNode(functionNode child, Operator op) {
            this.left = child;
            this.op = op;
            this.term = null;
        }

        // ---------------------
        // Factory methods for unary operations
        // ---------------------
        static functionNode sqrt(functionNode child) {
            return new functionNode(child, Operator.SQRT);
        }

        static functionNode pow(functionNode child, complexNumber exponent) {
            functionNode node = new functionNode(child, Operator.POW);
            node.exponent = exponent;
            return node;
        }

        // ---------------------
        // Evaluate recursively
        // ---------------------
        complexNumber evaluate(complexNumber input) {
            if (term != null) return term.evaluate(input);

            switch (op) {
                case ADD: return left.evaluate(input).addToComplexNumber(right.evaluate(input));
                case SUBTRACT: return left.evaluate(input).subtractFromComplexNumber(right.evaluate(input));
                case MULTIPLY: return left.evaluate(input).multiplyWithComplexNumber(right.evaluate(input));
                case DIVIDE: return left.evaluate(input).divideByComplexNumber(right.evaluate(input));
                case SQRT: return left.evaluate(input).pow(new complexNumber(0.5, 0));
                case POW: return left.evaluate(input).pow(exponent);
                default: throw new IllegalStateException("Unknown operator");
            }
        }

        // ---------------------
        // Build symbolic string
        // ---------------------
        @Override
        public String toString() {
            if (term != null) return term.toString();
            switch (op) {
                case ADD: return "(" + left + " + " + right + ")";
                case SUBTRACT: return "(" + left + " - " + right + ")";
                case MULTIPLY: return "(" + left + " * " + right + ")";
                case DIVIDE: return "(" + left + " / " + right + ")";
                case SQRT: return "sqrt(" + left + ")";
                case POW: return "(" + left + ")^(" + exponent + ")";
                default: return "?";
            }
        }
    }
}

