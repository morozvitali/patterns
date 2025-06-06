package patterns.prototype.realisation.sword;

public class Sword implements Cloneable {
    private String material;

    public Sword (String material) {
        this.material = material;
    }

    public void setMaterial (String material)
    {this.material = material;}

    public String getMaterial () {
        return material;
    }

    @Override
    protected Sword clone () {
        try {
            return (Sword) super.clone(); // поверхнева копія
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // таке не трапиться бо ми реалізовуємо Cloneable
        }
    }

    public static void main(String[] args) {
        Sword prototype = new Sword ("steel");
        Sword enchantedSword = prototype.clone();
        enchantedSword.setMaterial("magic steel");
        System.out.println(prototype.getMaterial());
        System.out.println(enchantedSword.getMaterial());
    }
}
