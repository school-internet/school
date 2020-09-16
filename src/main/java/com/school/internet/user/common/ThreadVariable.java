package com.school.internet.user.common;


import com.school.internet.user.entity.SmUser;

public class ThreadVariable implements Runnable {

    private static class ThreadUtilsHolder {

        private static final ThreadVariable INSTANCE = new ThreadVariable();

    }

    private ThreadVariable() {

    }

    public static final ThreadVariable getInstance() {
        return ThreadUtilsHolder.INSTANCE;
    }

    private SmUser smUser;


    private final ThreadLocal<SmUser> psndocThreadLocal = new ThreadLocal<SmUser>(){
        protected SmUser initialValue(){
            return smUser;
        }
    };
    public void setPsndoc(SmUser smUser){
        this.smUser = smUser;
    }
    public SmUser getPsndoc(){
        return  smUser;
    }



    public void remove(){
        this.smUser = null;
    }

    @Override
    public void run() {

    }

}
