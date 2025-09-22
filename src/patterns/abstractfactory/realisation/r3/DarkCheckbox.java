package patterns.abstractfactory.realisation.r3;

public class DarkCheckbox implements Checkbox{
    @Override
    public void render() {
        System.out.println("Dark checkbox");
    }
}
