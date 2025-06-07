package patterns.proxy.realisation.dynamic;

import java.lang.reflect.*;

public class ProxyHandler implements InvocationHandler {

    private Object realObject;

    public ProxyHandler (Object realObject) {
        this.realObject = realObject;
    }


    public Object invoke (Object proxy, Method method,
                          Object [] args) throws Throwable {
        System.out.println("Before method");
        Object result = method.invoke (realObject, args);
        System.out.println("After method");
        return result;
    }

}
