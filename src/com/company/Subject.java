package com.company;

import java.util.ArrayList;

public interface Subject {
    public void addObserver(User user);

    public void removeObserver(User user);

    public void notifyAllObservers(String notification);
}
