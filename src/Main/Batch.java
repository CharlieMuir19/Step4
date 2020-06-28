//The Batch class sets and gets all important information in each batch
public class Batch {
    private String date;
    private String farmCode;
    private String finalFruit;
    private String fruitCode;
    private int batchWeight;
    private String batchNumber;
    private String batch;
    private int gradeA;
    private int gradeB;
    private int gradeC;
    private int rejected;
    //Batch allows other classes to get and set the most important batch values
    public Batch() {
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setFarmCode(String farmCode) {
        this.farmCode = farmCode;
    }

    public void setBatchWeight(int decide) {
        batchWeight = decide;
    }

    public String getFarmCode() {
        return farmCode;
    }

    public String getFruit() {
        return finalFruit;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public String getFruitCode() {
        return fruitCode;
    }

    public String getDate() {
        return date;
    }

    public int getBatchWeight() {
        return batchWeight;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public void setFruit(String returnFruit) {
        finalFruit = returnFruit;
    }

    public void setFruitCode(String returnFruitCode) {
        fruitCode = returnFruitCode;
    }

    public void setFile(String file) { batch = file; }

    public void setGradeA(int gradeA){ this.gradeA = gradeA; }

    public void setGradeB(int gradeB){ this.gradeB = gradeB; }

    public void setGradeC(int gradeC){ this.gradeC = gradeC; }

    public void setRejected(int rejected){ this.rejected = rejected; }

    public String getFile(){ return batch; }

    public int getGradeA(){ return gradeA; }

    public int getGradeB() { return gradeB; }

    public int getGradeC() { return gradeC; }

    public int getRejected() { return rejected; }



}
