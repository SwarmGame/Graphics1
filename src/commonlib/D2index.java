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
    D2index(int m, int n)
    {
        this.m_cor = m;
        this.n_cor = n;
    }
    D2vector to_vector()
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
