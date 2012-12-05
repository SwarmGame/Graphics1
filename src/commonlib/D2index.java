package commonlib;

/**
 * Created with IntelliJ IDEA.
 * User: laosun
 * Date: 12/1/12
 * Time: 10:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class D2index {
    int m_cor;
    int n_cor;

    public int getM_cor() {
        return m_cor;
    }

    public void setM_cor(int m_cor) {
        this.m_cor = m_cor;
    }

    public int getN_cor() {
        return n_cor;
    }

    public void setN_cor(int n_cor) {
        this.n_cor = n_cor;
    }

    public D2index(int m, int n)
    {
        this.m_cor = m;
        this.n_cor = n;
    }
    public D2vector to_vector()
    {
        double x_cor = (double)n_cor;
        double y_cor = (double)m_cor;
        D2vector result = new D2vector(x_cor,y_cor);
        return result;
    }
    void Print()
    {
        System.out.format("[%d,%d]\n",m_cor,n_cor);
    }
}
