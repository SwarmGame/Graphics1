package commonlib;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: laosun
 * Date: 12/1/12
 * Time: 10:09 PM
 * To change this template use File | Settings | File Templates.
 */
public final class Random_2Dcoord_generator {
    // this class generates a random coordinate on a two dim map with higher probability near center
    // x from x_min to x_max, y from y_min to y_max, boundary value included
    // because of 3-sigma rule, the random number we obtain from java gaussian will be range from -3 to 3, with 99.7% confidence.
    Random fRandom;
    Random_2Dcoord_generator()
    {
        fRandom = new Random();
    }
    public D2index get_random_2Dcoord(int min_x, int max_x, int min_y, int max_y)
    {
        double rand1 = fRandom.nextGaussian();
        double rand2 = fRandom.nextGaussian();
        int x_range =  Math.abs(max_x - min_x);
        int y_range =  Math.abs(max_y - min_y);
        double x_mid = (max_x+min_x)/2.0;
        double y_mid = (max_y+min_y)/2.0;
        int x_coord = get_closet_int(rand1*x_range/7.0+x_mid);   // on large enough maps, the round off probability difference is negligible.
        int y_coord = get_closet_int(rand2*y_range/7.0+y_mid);   // use get_closet_int function for symmetric distribution result
        if(x_coord>max_x)
        {
            x_coord = max_x;
        }
        if(x_coord<min_x)
        {
            x_coord = min_x;
        }
        if(y_coord>max_y)
        {
            y_coord = max_y;
        }
        if(y_coord<min_y)
        {
            y_coord = min_y;
        }
        D2index result = new D2index(x_coord,y_coord);
        //System.out.format("new random coordinate generated: "); result.Print();
        return result;

    }
    private int get_closet_int(double a)
    {
        int tmp1 = (int)a;
        int tmp2 = tmp1+1;
        if(Math.abs(tmp1-a)>Math.abs(tmp2-a))
        {
            return tmp2;
        }
        else
        {
            return tmp1;
        }
    }
    public static void main(String[] args)
    {
        Random_2Dcoord_generator my_2d_ran_gen= new Random_2Dcoord_generator();
        int i;
        int count_5 =0;
        int count_0 =0;
        D2index tmp;
        for(i=0;i<1000000;i++)
        {
            tmp = my_2d_ran_gen.get_random_2Dcoord(0,1000,0,1000);
            if(tmp.m_cor ==300)
            {
                count_5++;
            }
            if(tmp.n_cor ==0)
            {
                count_0++;
            }

        }
        System.out.format("number of 5s is %d, number of 0s is %d",count_5,count_0);
    }

}
