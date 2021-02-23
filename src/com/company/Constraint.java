package com.company;

public class Constraint {
    private Object inferiorLimit;
    private Object superiorLimit;

    Constraint() {
        inferiorLimit = 0.0;
        superiorLimit = 0.0;
    }

    Constraint(Object inferiorLimit, Object superiorLimit) {
        this.inferiorLimit = inferiorLimit;
        this.superiorLimit = superiorLimit;
    }

    public Object getInferiorLimit() {
        return inferiorLimit;
    }

    public void setInferiorLimit(Object inferiorLimit) {
        this.inferiorLimit = inferiorLimit;
    }

    public Object getSuperiorLimit() {
        return superiorLimit;
    }

    public void setSuperiorLimit(Object superiorLimit) {
        this.superiorLimit = superiorLimit;
    }

    public String toString() {
        return "Constraint{" +
                "inferiorLimit=" + inferiorLimit +
                ", superiorLimit=" + superiorLimit +
                '}';
    }
}
