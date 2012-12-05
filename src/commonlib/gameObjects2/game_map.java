package commonlib.gameObjects2;

import commonlib.D2index;
import commonlib.D2vector;
import commonlib.Random_2Dcoord_generator;
import commonlib.game_situation;

import java.util.ArrayList;
//import java.util.ArrayList;
//import java.util.List;


public class game_map {

//enum typeofobject{Empty, commonlib.gameObjects.Queen, Nutrition, commonlib.gameObjects.Particle,Taken, Bornloc,Dummy};
//boolean has_bornloc_flag;
//typeofobject[][]  gamemap;

    public gridpt[][] getMap_grid() {
        return map_grid;
    }

    public void setMap_grid(gridpt[][] map_grid) {
        this.map_grid = map_grid;
    }

    gridpt[][]              map_grid;
ArrayList<D2vector>     spawn_locs;
ArrayList<game_objects> game_objs;
//D2distr                 prob_distr;
Random_2Dcoord_generator rand_generator;

    public int getM() {
        return M;
    }

    public void setM(int m) {
        M = m;
    }

    public int getN() {
        return N;
    }

    public void setN(int n) {
        N = n;
    }

    int M, N;
int i,j;
int center_m;
int center_n;
double dt;
//double dh;
empty emptyobj;
public class gridpt
{
    public game_objects getGobj() {
        return gobj;
    }

    public void setGobj(game_objects gobj) {
        this.gobj = gobj;
    }

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
gridpt get_gridpt(D2index coord)
{
    return  map_grid[coord.getM_cor()][coord.getN_cor()];
}
public boolean is_occupied(D2index coord)
{
    if(is_inside_map(coord))
    {
        if(get_gridpt(coord).gobj!=emptyobj)
        {
            return true;
        }
    }
    return false;
}
public boolean is_inside_map(D2index coord)
{
   if(coord.getM_cor()>=0&&coord.getM_cor()<M&&coord.getN_cor()>=0&&coord.getN_cor()<N)
   {
       return true;
   }
    else
   {
       return false;
   }
}
/*public class D2distr
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
}     */

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

public game_map(int X_len, int Y_len)
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
    //prob_distr = new D2distr(M,N);
    game_objs = new ArrayList<game_objects>();
    spawn_locs = new ArrayList<D2vector>();
    center_m = M/2;
    center_n = N/2;
    rand_generator = new Random_2Dcoord_generator();
}
void apply_all_objs_tomap()
{
    System.out.format("Now updating objs to map, total number of objs is :%d%n",game_objs.size());
    for(int i=0;i<game_objs.size();i++)
    {
        game_objs.get(i).apply_to_map(this);
    }
}
public boolean remove_obj(game_objects obj)
{
    int N = game_objs.size();
    int i;
    for(i=0;i<N;i++)
    {
        if(game_objs.get(i)==obj)
        {
            game_objs.remove(i);
            return true;
        }
    }
    return false;
}
//int initialize_map(int num_players, ArrayList<zone_func> prob_funcs)
public void initialize_map(int num_players)
{
    // need to init spawn loc based on number of players
    spawn_locs = get_spawn_locs(num_players, M, N);
    spwan_queen();
    return;
}
boolean spwan_queen()
{
    for(int i = 0;i<spawn_locs.size();i++)
    {
        Queen q1 = new Queen(spawn_locs.get(i),this);
        game_objs.add(q1);
        //q1.apply_to_map(this);
    }
    return true;
}
int update_map()
{
    return 1;
}

game_situation get_gamesituation(D2vector center, int M_range, int N_range)
  {
      game_situation result = new  game_situation(this,center,M_range,N_range);
      return result;
 }
/*boolean generate_new_nutriant()
{
    int max_num_try = 1000;
    nutrient new_nutrient = new nutrient();
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
    return;
}
    */
public ArrayList<D2vector> generate_corarray_for_newnutrient(int num)
{
    ArrayList<D2vector> coord_array = new ArrayList<D2vector>();
    generate_new_nutrient(num);
    for(int i=0;i<num;i++)
    {
       coord_array.add(remove_last_item_and_return_coord());
    }
    return coord_array;

}
public boolean generate_new_nutrient(int num)
{
    final int max_num_try_per_nutrient = 1000;
    final int max_num_nutrient_onetime  = 100;
    assert(num< max_num_nutrient_onetime);
    int i,j;
    //boolean max_iter_flag;
    D2index  mytry;
    nutrient mynewnutrient;
    for(i=0;i<num;i++)
    {
        j=0;
        //max_iter_flag =true;
        do{
            mytry = rand_generator.get_random_2Dcoord(0,M,0,N);
            mynewnutrient = new nutrient(mytry.to_vector());
            j++;
        }
        while(!mynewnutrient.is_fit(this)&&j<max_num_try_per_nutrient);
        if(j>=max_num_try_per_nutrient)
        {
            System.out.format("max try in random generation of particles reached, possibly because the map is full\n");
            return false;
        }
        else
        {
            mynewnutrient.Printmyself();
            mynewnutrient.apply_to_map(this);
        }
    }
    return true;
}
public void remove_last_item()
{
    int N = game_objs.size();
    System.out.format("total item number is %d, removing %dth ele!", N,N-1) ;
    game_objects obj = game_objs.get(N-1);
    obj.remove_from_map(this);
    //game_objs.remove(N-1);
}
public D2vector remove_last_item_and_return_coord()
{
    D2vector result = new D2vector();
    int N = game_objs.size();
    System.out.format("total item number is %d, removing %dth ele!", N,N-1) ;
    game_objects obj = game_objs.get(N-1);
    result = obj.cor;
    obj.remove_from_map(this);
    return result;
}
void print_occupation_stat()
{
    int total_grid_point=M*N;
    int occupied_grid_point=0;
    double percentage;
    int i,j;
    D2index tmpindx;
    for(i=0;i<M;i++)
    {
        for(j=0;j<N;j++)
        {
            tmpindx = new D2index(i,j);
            if(is_occupied(tmpindx))
            {
                occupied_grid_point++;
            }
        }
    }
    percentage = (double)(occupied_grid_point)/(double)(total_grid_point);
    System.out.format("There are %d total grid points in the map, %d of which are occupied, occupation percentage is %e",
            total_grid_point, occupied_grid_point,percentage );
}
public static void main(String[] args)
{
    game_map mygamemap = new game_map(20,20);
    mygamemap.print_occupation_stat();
    game_situation cur_game;
    cur_game = mygamemap.get_gamesituation(new D2vector(500,500),200,200);
    cur_game.Print_size();
    //zone_func func1 = new zone_func(5,mygamemap.center_m,mygamemap.center_n,100,10);
    //zone_func func1 = mygamemap.new zone_func(5,500,500,100,10);
    //ArrayList<zone_func> funcs = new ArrayList<zone_func>();
    //funcs.add(func1);
    mygamemap.initialize_map(1);
    //mygamemap.apply_all_objs_tomap();
    mygamemap.print_occupation_stat();
    mygamemap.generate_new_nutrient(10);
    mygamemap.print_occupation_stat();
    cur_game = mygamemap.get_gamesituation(new D2vector(150,300),300,300);
    cur_game.Print_size();
    //mygamemap.generate_new_nutriant();
    //mygamemap.generate_new_nutriant();
    //Queen q1 = new Queen(mygamemap.spawn_locs.get(0));
    System.out.println("init success");
}
}

