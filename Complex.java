public class Complex {
    public double re;
    public double im;

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public Complex add(Complex other) {
        return new Complex(this.re + other.re, this.im + other.im);
    }

    public Complex sub(Complex other) {
        return new Complex(this.re - other.re, this.im - other.im);
    }

    public Complex mul(Complex other) {
        double newRe = this.re * other.re - this.im * other.im;
        double newIm = this.re * other.im + this.im * other.re;
        return new Complex(newRe, newIm);
    }

    public Complex div(Complex other) {
        double denom = other.re * other.re + other.im * other.im;
        if (denom == 0) {
            throw new ArithmeticException("Деление на ноль в комплексных числах!");
        }
        double newRe = (this.re * other.re + this.im * other.im) / denom;
        double newIm = (this.im * other.re - this.re * other.im) / denom;
        return new Complex(newRe, newIm);
    }
    @Override
    public String toString() {
        return re + " + " + im + "i";
    }
}