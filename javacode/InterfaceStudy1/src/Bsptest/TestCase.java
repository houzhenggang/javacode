package Bsptest;

public class TestCase
{
	public static void main(String[] args) 
	{
        VehicleFactory factory = new BroomFactory();
        Moveable m = factory.create();
        m.run();
        VehicleFactory factory1 = new PlaneFactory();
        Moveable p = factory1.create();
        p.ru();
    }
}
