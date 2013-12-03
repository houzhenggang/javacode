package xjh;
public class Interface {
    
    public static void main(String[] args){
            
            CareAnimalable c = new Worker();
            //Worker w = (Worker)c;
            Interface t = new Interface();
            t.t(c); //��̬

            c = new Farmer();
            t.t(c); 

            
    }

    public void t(CareAnimalable c){//��������Ϊ�ӿڻ��������
            c.feed();
            c.play();
    }
}


interface CareAnimalable{
    public void feed();
    public void play();
}

class Worker implements CareAnimalable{
    
    public void feed(){
            System.out.println("-----feed()----");
    }

    public void play(){
            System.out.println("-----play()----");
    }
}

class Farmer implements CareAnimalable{
    
    public void feed(){
            System.out.println("-----Farmer feed()----");
    }

    public void play(){
            System.out.println("-----Farmer play()----");
    }
}