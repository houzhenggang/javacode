package Bsptest;

public class PlaneFactory extends VehicleFactory
{
	public Moveable create() 
	{
        return new Plane();
    }
}
