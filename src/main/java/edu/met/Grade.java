package edu.met;

public class Grade {
    public int A, APlus, AMinus;
    public int B, BPlus, BMinus;
    public int C, CPlus, CMinues;
    public int F;

    Grade() {

        //A Grade
        APlus = 97;
        A = 95;
        AMinus = 93;

        //B grade
        BPlus = 87;
        B = 85;
        BMinus = 80;

        //C grade


        //FGrade


    }

    public int getA() {
        return A;
    }

    public void setA(int a) {
        A = a;
    }

    public int getAPlus() {
        return APlus;
    }

    public void setAPlus(int APlus) {
        this.APlus = APlus;
    }

    public int getAMinus() {
        return AMinus;
    }

    public void setAMinus(int AMinus) {
        this.AMinus = AMinus;
    }

    public int getB() {
        return B;
    }

    public void setB(int b) {
        B = b;
    }

    public int getBPlus() {
        return BPlus;
    }

    public void setBPlus(int BPlus) {
        this.BPlus = BPlus;
    }

    public int getBMinus() {
        return BMinus;
    }

    public void setBMinus(int BMinus) {
        this.BMinus = BMinus;
    }

    public int getC() {
        return C;
    }

    public void setC(int c) {
        C = c;
    }

    public int getCPlus() {
        return CPlus;
    }

    public void setCPlus(int CPlus) {
        this.CPlus = CPlus;
    }

    public int getCMinues() {
        return CMinues;
    }

    public void setCMinues(int CMinues) {
        this.CMinues = CMinues;
    }

    public int getF() {
        return F;
    }

    public void setF(int f) {
        F = f;
    }
}


