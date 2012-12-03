package commonlib;

import java.util.Random;

public class D2vector {
      private double x,y;
      public D2vector()
      {
    	  this.x =0.0;
    	  this.y =0.0;
      }
      public D2vector(double initx, double inity)
      {
    	  this.x = initx;
    	  this.y = inity;
      }
      public D2vector(int ix, int iy)
      {
    	  this.x = ix;
    	  this.y = iy;
      }
      public D2vector(D2vector vec)
      {
    	  this.x = vec.getx();
    	  this.y = vec.gety();
      }
      /*public D2vector(D2distr distr)
      {
    	  D2vector vec = distr.getrandcor();
    	  this.x = vec.getx();
    	  this.y = vec.gety();
      } */
	  public void assign(D2vector vec)
      {
    	  this.x = vec.getx();
    	  this.y = vec.gety();
      }
      public void set0()
      {
    	  this.x = 0;
    	  this.y = 0;
      }
      int get_mcor()
      {
    	  return (int) Math.ceil(this.x);
      }
      int get_ncor()
      {
    	  return (int) Math.ceil(this.y);
      }
      public void random_change(double range)
      {
    	  Random randGen = new Random();
    	  setx(this.x+randGen.nextDouble()*range);
    	  sety(this.y+randGen.nextDouble()*range);
      }
      public double getx()
      {
    	  return this.x;
      }
      public double gety()
      {
    	  return this.y;
      }
      public void setx(double newx)
      {
    	  this.x = newx;
      }
      public void sety(double newy)
      {
    	  this.y = newy;
      }
      double distance(D2vector another_vec)
      {
    	  double square = (this.x -another_vec.getx())* (this.x -another_vec.getx())+(this.y -another_vec.gety())* (this.y -another_vec.gety());
    	  return Math.sqrt(square);
      }
      double distance(D2vector v1, D2vector v2)
      {
    	  double square = (v1.getx() - v2.getx())*(v1.getx() - v2.getx())+(v1.gety() - v2.gety())*(v1.gety() - v2.gety());
    	  return Math.sqrt(square);
      }
      D2vector multi(double k)
      {
    	  D2vector result = new D2vector(this.x*k,this.y*k);
    	  return result;
      }
      D2vector plus(D2vector v2)
      {
    	  D2vector result = new D2vector(this.x+v2.getx(),this.y+v2.gety());
    	  return result;
      }
      D2vector minus(D2vector v2)
      {
    	  D2vector result = new D2vector(this.x-v2.getx(),this.y-v2.gety());
    	  return result;
      }
      D2index  to_index()
      {
          int m = (int)y;
          int n = (int)x;
          D2index result= new D2index(m,n);
          return result;
      }
      double Modul()
      {
          return Math.sqrt(x*x+y*y);
      }
      D2vector unitified()
      {
          double modul = Modul();
          double newx,newy;
          D2vector result;
          if(modul!=0)
          {
              newx = x/modul;
              newy = y/modul;
              result = new D2vector(newx, newy);
          }
          else
          {
              newx = 0.0;
              newy = 0.0;
              result = new D2vector(newx, newy);
          }
          return result;
      }
      public void Print()
  	  {
  		System.out.format("(%e,%e)%n",x,y);
  	  }
      public static void main(String[] args)
      {
    	  D2vector v1= new D2vector(1.0,2.0);
    	  D2vector v2= new D2vector(0.0,2.0);
    	  System.out.format("Vector v1:");
    	  v1.Print();
    	  System.out.format("Vector v2, before change:");
    	  v2.Print();
    	  v2.random_change(3);
    	  System.out.format("Vector v2, after change:");
    	  v2.Print();
      }
}
