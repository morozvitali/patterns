package patterns.facade;

public class CPU {
    public void freeze () {
        System.out.println("freezing processor");
    }

    public void jump (long position) {
        System.out.println("jumping to position " + position);
    }

    public void execute () {
        System.out.println("executing instructions ...");
    }
}
