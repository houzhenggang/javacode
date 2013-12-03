package Bsptest;

public class BroomFactory extends VehicleFactory
{
	public Moveable create() {
        return new Broom();
    }
}
