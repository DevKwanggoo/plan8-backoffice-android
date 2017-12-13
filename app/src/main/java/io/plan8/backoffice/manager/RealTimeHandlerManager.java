package io.plan8.backoffice.manager;

import android.content.Context;
import android.os.Handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by SSozi on 2017. 12. 13..
 */

public class RealTimeHandlerManager {
    private Context context;
    private static volatile RealTimeHandlerManager instance = null;
    private HashMap<String, List<Handler>> timeHandlers = new HashMap<>();
    private List<Handler> actionHandlers = new ArrayList<>();

    public static RealTimeHandlerManager getInstance() {
        if (instance == null) {
            synchronized (RealTimeHandlerManager.class){
                instance = new RealTimeHandlerManager();
            }
        }
        return instance;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void addHandler(String key, List<Handler> handler) {
        timeHandlers.put(key, handler);
    }

    public HashMap<String, List<Handler>> getHandlers() {
        return timeHandlers;
    }

    public void clearHandler(String key) {
        for (int i = 0; i< timeHandlers.get(key).size(); i++) {
            timeHandlers.get(key).get(i).removeMessages(0);
        }
        timeHandlers.get(key).clear();
    }

    public List<Handler> getActionHandler() {
        return actionHandlers;
    }

    public void addActionHandler(Handler handler) {
        this.actionHandlers.add(handler);
    }

    public void stopHandler(String key){
        for (int i = 0; i< timeHandlers.get(key).size(); i++) {
            timeHandlers.get(key).get(i).removeMessages(0);
        }
    }

    public void startHandler(String key){
        for (int i = 0; i< timeHandlers.get(key).size(); i++) {
            timeHandlers.get(key).get(i).sendEmptyMessage(0);
        }
    }
}
