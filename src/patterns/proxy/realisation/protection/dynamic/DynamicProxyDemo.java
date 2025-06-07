package patterns.proxy.realisation.protection.dynamic;

import java.lang.reflect.Proxy;

public class DynamicProxyDemo {
    public static void main(String[] args) {
        Service real = new RealService();
        Service proxy = (Service) Proxy.newProxyInstance(
                real.getClass().getClassLoader(),
                new Class[]{Service.class},
                new ProxyHandler(real)
        );

        proxy.run(); // added logging before/after logging
    }
}
