package server;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
//import java.util.ArrayList;
//import java.util.List;


public class game_map {

//enum typeofobject{Empty, Queen, Nutrition, Particle,Taken, Bornloc,Dummy};
//boolean has_bornloc_flag;
//typeofobject[][]  gamemap;

gridpt[][]              map_grid;
ArrayList<D2vector>     spawn_locs;
ArrayList<game_objects> game_objs;
D2distr                 prob_distr;
int M, N;
int i,j;
int center_m;
int center_n;
double dt;
//double dh;
empty emptyobj;
public class gridpt
{
    game_objects gobj;
    D2vector     center;
    gridpt()
    {
        gobj = emptyobj;
    }
    gridpt(game_objects obj1, int m_cor, int n_cor)
    {
        gobj = obj1;
        center = new D2vector(m_cor+0.5,n_cor+0.5);
    }
    void set_obj(game_objects obj2)
    {
        gobj = obj2;
    }
    void resume_empty()
    {
        gobj = emptyobj;
    }
}
public class D2distr
{
    private int DM, DN; // scope of the distribution function
    private double prob_dividend;  //total
    double[][] CDF_2D; // cumulative distribution function, 2D. Similar to a CDF but not exact.
    double[][] PDF_2D; // probability density function,     2D. Similar to a PDF but not exact.
    int i,j;
    D2distr(int MM, int NN)
    {
        DM = MM; DN = NN;
        PDF_2D = new double[DM][DN];
        CDF_2D = new double[DM][DN];
        for(i=0;i<DM;i++)
        {
            for(j=0;j<DN;j++)
            {
                PDF_2D[i][j] = 0.0;
                CDF_2D[i][j] = 0.0;
            }
        }
    }
    boolean Initialize_distri(ArrayList<zone_func> prob_funcs)
    {
        for(i=0;i<prob_funcs.size(); i++)
        {
            prob_funcs.get(i).apply_func_to_PDF(PDF_2D);
        }
        return true;
    }
    D2vector getrandcor()
    {
        Random randGen = new Random();
        double randnum = randGen.nextDouble();
        double target = (prob_dividend*randnum);
        D2vector result = Binary_Search_in_Matrix(CDF_2D, target);
        return result;
    }
    int get_m(int D1coord)
    {
        return D1coord/DN;
    }
    int get_n(int D1coord)
    {
        return D1coord%DN;
    }


    D2vector get_loc(int k)
    {
        D2vector result = new D2vector(get_m(k),get_n(k));
        return result;
    }
    D2vector Binary_Search_in_Matrix(double CDF_ma[][], double target)
    {
        int i= 0;
        int j = DM*DN-1;
        int k = 0;;
        while(j-i>1)
        {
            k = (i+j)/2;
            if(CDF_ma[get_m(k)][get_n(k)]>target)
            {
                j = k;
            }
            else if(CDF_ma[get_m(k)][get_n(k)]==target)
            {
                //return get_loc(k);
                break;
            }
            else
            {
                i = k;
            }
        }
        //return new D2vector(0,0);
        return get_loc(k);
    }
    double get_previous(double ma[][], int i, int j)
    {
        if(j==0)
        {
            if(i==0)
            {
                return 0;
            }
            else
            {
                return ma[i-1][N-1];
            }
        }
        else
        {
            return ma[i][j-1];
        }
    }
}
boolean is_inmap(int coord_m, int coord_n)
{
    if(coord_m>=0&&coord_m<M&&coord_n>=0&&coord_n<N)
    {
        return true;
    }
    else
    {
        return false;
    }
}
public class zone_func
{
    public double radius;
    public D2vector center;
    public double max_p;
    public double min_p;
    //ArrayList<Integer> iter_range;   // four
    public int range_m_min;
    public int range_m_max;
    public int range_n_min;
    public int range_n_max;

    zone_func(double r, D2vector cen, double max, double min)
    {
        radius = r;
        center = cen;
        max_p = max;
        min_p = min;
        init_iter_range(M,N);
    }
    zone_func(double r, double x,double y, double max, double min)
    {
        radius = r;
        center = new D2vector(x,y);
        max_p = max;
        min_p = min;
        init_iter_range(M,N);
    }
    double get_PDF_value(int ii, int jj)
    {
        D2vector pos = new D2vector(ii,jj);
         double dis_to_center = pos.distance(center);
         if(dis_to_center>radius)
         {
             return 0;
         }
         else
         {
             double result = dis_to_center/radius*(max_p-min_p)+min_p;
             return result;
         }
    }
    void init_iter_range(int max_i, int max_j)
    {
        int tmp_range_m_min = (int)(center.getx()-radius);
        int tmp_range_m_max = (int)(center.getx()+radius);
        int tmp_range_n_min = (int)(center.gety()-radius);
        int tmp_range_n_max = (int)(center.gety()+radius);
        if(tmp_range_m_min<0)
        {
            range_m_min = 0;
        }
        else
        {
            range_m_min = tmp_range_m_min;
        }
        if(tmp_range_m_max>M)
        {
            range_m_max = M;
        }
        else
        {
            range_m_max = tmp_range_m_max;
        }
        if(tmp_range_n_min<0)
        {
            range_n_min = 0;
        }
        else
        {
            range_n_min = tmp_range_n_min;
        }
        if(tmp_range_n_max>N)
        {
            range_n_max = N;
        }
        else
        {
            range_n_max = tmp_range_n_max;
        }
    }
    boolean apply_func_to_PDF(double[][] PDF_2D)
    {
        int i,j;
        for(i=range_m_min;i<range_m_max;i++)
        {
            for(j=range_n_min;j<range_n_max;j++)
            {
                PDF_2D[i][j]+= get_PDF_value(i,j);
            }
        }
        return true;
    }
}

ArrayList<D2vector> get_spawn_locs(int num, int M, int N)
{
    // using eclipse parametric equation
    ArrayList<D2vector> result = new ArrayList<D2vector>();
    double ratio = 0.80;
    double PI = 3.141592653;
    int L_V = (int)(ratio*M/2); // length of vertical axis
    int L_H = (int)(ratio*N/2); // length of horizontal axis
    double unit_angle = 2*PI/num;
    double cur_angle = 0;
    int new_pos_m;
    int new_pos_n;
    for(int i =0;i<num;i++)
    {
        new_pos_m = center_m+(int)(L_V*Math.sin(cur_angle));
        new_pos_n = center_n+(int)(L_H*Math.cos(cur_angle));
        D2vector tmp_vec = new D2vector(new_pos_m,new_pos_n);
        System.out.format("spawn location:"); tmp_vec.Print();
        result.add(tmp_vec);
        cur_angle+=unit_angle;
    }
    return result;
}

game_map(int X_len, int Y_len)
{
    emptyobj = new empty();
    this.N = X_len;
    this.M = Y_len;
    map_grid = new gridpt[M][N];
    for(int ii=0;ii<M;ii++)
    {
        for(int jj=0;jj<N;jj++)
        {
            map_grid[ii][jj] = new gridpt(emptyobj,ii,jj);
        }
    }
    prob_distr = new D2distr(M,N);
    game_objs = new ArrayList<game_objects>();
    spawn_locs = new ArrayList<D2vector>();
    center_m = M/2;
    center_n = N/2;
}
void apply_all_objs_tomap()
{
    System.out.format("Now updating objs to map, total number of objs is :%d%n",game_objs.size());
    for(int i=0;i<game_objs.size();i++)
    {
        game_objs.get(i).apply_to_map(this);
    }
}
int initialize_map(int num_players, ArrayList<zone_func> prob_funcs)
{
    // need to init spawn loc based on number of players
    // need to generate probability density function and cumulative density function
    spawn_locs = get_spawn_locs(num_players, M, N);
    prob_distr.Initialize_distri(prob_funcs);
    spwan_queen();
    //init_CDF(prob_distr,prob_funcs);
    //has_bornloc_flag = false;
    return 1;
}
boolean spwan_queen()
{
    for(int i = 0;i<spawn_locs.size();i++)
    {
        queen q1 = new queen(spawn_locs.get(i));
        game_objs.add(q1);
    }
    return true;
}
int update_map()
{
    return 1;
}
boolean is_empty(D2vector vec,game_objects obj) // test whether I can put obj at location vec?
{
    if(map_grid[(int) vec.getx()][(int) vec.gety()].gobj == emptyobj)
    {
        return true;
    }
    else
    {
        return false;
    }
}
game_situation get_gamesituation(D2vector center, int M_range, int N_range)
  {
      game_situation result = new  game_situation(this,center,M_range,N_range);
      return result;
 }
boolean generate_new_nutriant()
{
    int max_num_try = 100;
    nutriant new_nutriant = new nutriant();
    D2vector attempt_cor = new D2vector();
    int i=0;
    while(i<max_num_try)
    {
        attempt_cor = prob_distr.getrandcor();
        attempt_cor.Print();
        if(new_nutriant.shape.is_fit_newloc(attempt_cor,this))
        {
            new_nutriant = new nutriant(attempt_cor);
            game_objs.add(new_nutriant);
            return true;
        }
        i++;
    }
    return false;
}
public static void main(String[] args)
{
    game_map mygamemap = new game_map(1000,1000);
    game_situation cur_game;
    cur_game = mygamemap.get_gamesituation(new D2vector(500,500),200,200);
    cur_game.Print_size();
    //zone_func func1 = new zone_func(5,mygamemap.center_m,mygamemap.center_n,100,10);
    zone_func func1 = mygamemap.new zone_func(5,500,500,100,10);
    ArrayList<zone_func> funcs = new ArrayList<zone_func>();
    funcs.add(func1);
    mygamemap.initialize_map(3,funcs);
    mygamemap.apply_all_objs_tomap();
    cur_game = mygamemap.get_gamesituation(new D2vector(150,300),300,300);
    cur_game.Print_size();
    //mygamemap.generate_new_nutriant();
    //mygamemap.generate_new_nutriant();
    //queen q1 = new queen(mygamemap.spawn_locs.get(0));
    System.out.println("init success");
}
}

