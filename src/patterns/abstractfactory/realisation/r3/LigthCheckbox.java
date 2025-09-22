package patterns.abstractfactory.realisation.r3;

public class LigthCheckbox implements Checkbox{
    @Override
    public void render () {
        System.out.println("Ligth checkbox");
    }
}
