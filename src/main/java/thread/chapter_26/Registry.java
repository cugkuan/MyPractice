package thread.chapter_26;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Registry {

    private final ConcurrentHashMap<String, ConcurrentLinkedDeque<Subscriber>> subscribeContainer = new ConcurrentHashMap<>();

    public void bind(Object subscriber){
        List<Method> subscribeMethods = getSubscribeMethods(subscriber);
        subscribeMethods.forEach( m -> tieSubscriber(subscriber,m));
    }


    public void unbind(Object subscriber){
//        subscribeContainer.forEach((key,queue) ->{
//            queue.forEach( s ->{
//                if (s.getSubscibeObject() == subscriber){
//                    s.setDisable(true);
//                }
//            });
//        });

        subscribeContainer.remove(subscriber);

    }

    private void tieSubscriber(Object subscriber,Method method){

        final Subscribe subscribe = method.getDeclaredAnnotation(Subscribe.class);
        String topic = subscribe.topic();
        subscribeContainer.computeIfAbsent(topic,key -> new ConcurrentLinkedDeque<>());
        subscribeContainer.get(topic).add(new Subscriber(subscriber,method));
    }

    private List<Method> getSubscribeMethods(Object subscriber){
        List<Method> methods = new ArrayList<>();
        Class c = subscriber.getClass();
        while (c != null){
            Method[] methods1 = c.getDeclaredMethods();
            for (Method m:methods1){
                if (m.isAnnotationPresent(Subscribe.class) && m.getParameterCount() == 1 && m.getModifiers() == Modifier.PUBLIC){
                    methods.add(m);
                }
            }
            c = c.getSuperclass();
        }

        return methods;
    }
}
