package thread.chapter_26;

import java.lang.reflect.Method;

public class Subscriber {

    private final Object subscibeObject;

    private final Method subsribeMethod;

    private boolean disable = false;

    public Subscriber(Object subscibeObject,Method subscibeMethod){
        this.subscibeObject = subscibeObject;
        this.subsribeMethod = subscibeMethod;
    }

    public Object getSubscibeObject(){
        return subscibeObject;
    }


    public Method getSubsribeMethod(){
        return subsribeMethod;
    }


    public boolean isDisable(){
        return disable;
    }

    public void setDisable(boolean disable){
        this.disable = disable;
    }

}

