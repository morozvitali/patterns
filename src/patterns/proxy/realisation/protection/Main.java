package patterns.proxy.realisation.protection;

public class Main {
    public static void main(String[] args) {
        Image image = new ProxyImage("cat_photo.jpg");
    }

    /* завантаження відкладене
        image.display();
        // Loading cat_photo.jpg
        Displaying cat_photo.jpg

        image.display();
        // Displaying cat_photo.jpg
        (без повторного завантаження)
     */
}
