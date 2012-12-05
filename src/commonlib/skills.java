package commonlib;

import commonlib.gameObjects2.Queen;

public abstract class   skills {  // skills could be obtained from nutriants
       boolean is_instant;


}

abstract class instant_skill  extends skills       // is a skill is instant, then it will apply its effect on the object immediately. in beta version, all skills are instant;
{
    abstract boolean applytoqueen(Queen objqueen);
}

class regen extends instant_skill{
    boolean applytoqueen(Queen objqueen)
    {
       //objqueen.cur_health_point = objqueen.max_health_point;
       objqueen.regenerate();
       return true;
    }
}

class add_particle extends instant_skill{
    boolean applytoqueen(Queen objqueen)
    {

        return true;
    }
}
