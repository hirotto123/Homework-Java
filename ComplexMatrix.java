public class ComplexMatrix {
    public Complex[][] data;
    public int rows;
    public int cols;

    public ComplexMatrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        data = new Complex[rows][cols];
        // инициализируем нулями
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = new Complex(0, 0);
            }
        }
    }

    public ComplexMatrix add(ComplexMatrix other) {
        if (this.rows != other.rows || this.cols != other.cols) {
            throw new RuntimeException("Размеры матриц не совпадают для сложения!");
        }
        ComplexMatrix res = new ComplexMatrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                res.data[i][j] = this.data[i][j].add(other.data[i][j]);
            }
        }
        return res;
    }

    public ComplexMatrix multiply(ComplexMatrix other) {
        if (this.cols != other.rows) {
            throw new RuntimeException("Число столбцов первой != числу строк второй!");
        }
        ComplexMatrix res = new ComplexMatrix(this.rows, other.cols);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                Complex sum = new Complex(0, 0);
                for (int k = 0; k < this.cols; k++) {
                    sum = sum.add(this.data[i][k].mul(other.data[k][j]));
                }
                res.data[i][j] = sum;
            }
        }
        return res;
    }

    public ComplexMatrix transpose() {
        ComplexMatrix res = new ComplexMatrix(cols, rows);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                res.data[j][i] = this.data[i][j];
            }
        }
        return res;
    }

   public Complex determinant() {
        if (rows != cols) {
            throw new RuntimeException("Определитель можно считать только для квадратных матриц!");
        }
        if (rows == 1) {
            return data[0][0];
        }
        if (rows == 2) {
            Complex ad = data[0][0].mul(data[1][1]);
            Complex bc = data[0][1].mul(data[1][0]);
            return ad.sub(bc);
        }

        Complex det = new Complex(0, 0);
        for (int j = 0; j < cols; j++) {
            ComplexMatrix minor = getMinor(0, j);
            Complex cofactor = minor.determinant();
            if (j % 2 != 0) {
                cofactor = cofactor.mul(new Complex(-1, 0));
            }
            det = det.add(data[0][j].mul(cofactor));
        }
        return det;
    }

    private ComplexMatrix getMinor(int skipRow, int skipCol) {
        ComplexMatrix res = new ComplexMatrix(rows - 1, cols - 1);
        int r = 0;
        for (int i = 0; i < rows; i++) {
            if (i == skipRow) continue;
            int c = 0;
            for (int j = 0; j < cols; j++) {
                if (j == skipCol) continue;
                res.data[r][c] = data[i][j];
                c++;
            }
            r++;
        }
        return res;
    }

    public ComplexMatrix divide(ComplexMatrix other) {
        ComplexMatrix inv = other.getInverse();
        return this.multiply(inv);
    }

    public ComplexMatrix getInverse() {
        if (rows != cols) throw new RuntimeException("Матрица не квадратная!");
        Complex det = this.determinant();
        if (det.re == 0 && det.im == 0) {
            throw new RuntimeException("Определитель равен нулю, обратная матрица не существует!");
        }

        ComplexMatrix adj = new ComplexMatrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                ComplexMatrix minor = getMinor(i, j);
                Complex cofactor = minor.determinant();
                if ((i + j) % 2 != 0) {
                    cofactor = cofactor.mul(new Complex(-1, 0));
                }
                adj.data[j][i] = cofactor; 
            }
        }

        // умножаем на 1/det
        Complex invDet = new Complex(1, 0).div(det);
        ComplexMatrix res = new ComplexMatrix(rows, cols);
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                res.data[i][j] = adj.data[i][j].mul(invDet);
            }
        }
        return res;
    }

    public void print() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(data[i][j] + "\t");
            }
            System.out.println();
        }
    }
}