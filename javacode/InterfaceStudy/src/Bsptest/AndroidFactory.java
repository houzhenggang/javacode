package Bsptest;

public class AndroidFactory extends AllFactory
{
	public Interface1 create() {
        return new Android();
    }
}
