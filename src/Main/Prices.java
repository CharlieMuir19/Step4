//The class Prices sets all the prices for fruit for the day and proceeds to save them to a JSON file
//This class uses instances of InputHandler to get price input from user,
//JSONProcessor to save values to file
public class Prices {

    String[] listOfFruits = {"Strawberry", "Raspberry", "Blackberry", "Gooseberry"};
    double stGradeAPrice = 0; double stGradeBPrice = 0; double stGradeCPrice = 0; double stRejectedPrice = 0;
    double raGradeAPrice = 0; double raGradeBPrice = 0; double raGradeCPrice = 0; double raRejectedPrice = 0;
    double blGradeAPrice = 0; double blGradeBPrice = 0; double blGradeCPrice = 0; double blRejectedPrice = 0;
    double goGradeAPrice = 0; double goGradeBPrice = 0; double goGradeCPrice = 0; double goRejectedPrice = 0;
    private JSONProcessor jsonP;
    private InputHandler inputHandler;

    //Prices constructor sets inputhandler and jsonProcessor to be used throughout the prices class
    public Prices(InputHandler inputHandler, JSONProcessor jsonP) {
        this.inputHandler = inputHandler;
        this.jsonP = jsonP;
    }

    //This method retrieves the prices from the user using the getPrice method from the inputHandler instance
    //then validates if price is not negative. If negative, it starts the process for prices for Strawberry
    //if valid, the program sets the Strawberry prices
    void setSTDailyPrice() {
        System.out.println("\nPlease enter prices for " + listOfFruits[0]);
        System.out.println("Please enter grade A price. \n");
        double tempStA = inputHandler.getPrice();
        if(tempStA == -1){
            System.out.println("ERROR - Price cannot be negative");
            setSTDailyPrice();
        }else{
            setStGradeAPrice(tempStA);
        }

        System.out.println("Please enter grade B price.");
        double tempStB = inputHandler.getPrice();
        if(tempStB == -1){
            System.out.println("ERROR - Price cannot be negative");
            setSTDailyPrice();
        }else{
            setStGradeBPrice(tempStB);
        }

        System.out.println("Please enter grade C price.");
        double tempStC = inputHandler.getPrice();
        if(tempStC == -1){
            System.out.println("ERROR - Price cannot be negative");
            setSTDailyPrice();
        }else{
            setStGradeCPrice(tempStC);
        }

        System.out.println("Please enter rejects price.");
        double tempStR = inputHandler.getPrice();
        if(tempStR == -1){
            System.out.println("ERROR - Price cannot be negative");
            setSTDailyPrice();
        }else{
            setStRejectedPrice(tempStR);
        }

        //We ask the user to validate before saving prices, if not valid we repeat the method,
        //if valid, the program goes to the next method called in the processor class.
        System.out.println("Your prices are: \nGrade A: " + getStGradeAPrice()
        + "\nGrade B: " + getStGradeBPrice() + "\nGrade C: " + getStGradeCPrice()
        + "\nRejects: " + getStRejectedPrice() + "\nIs this correct?");
        System.out.println("1. YES - SAVE");
        System.out.println("2. NO - RESTART");
        int confirm = inputHandler.decide(1,2);
        if(confirm == 2){
            setSTDailyPrice();
        }
    }


    //The rest of the setDailyPrices methods have the same functionality as described above.
    void setRADailyPrice() {
        System.out.println("\nPlease enter prices for " + listOfFruits[1]);
        System.out.println("Please enter grade A price.");
        double tempRaA = inputHandler.getPrice();
        if(tempRaA == -1){
            System.out.println("ERROR - Price cannot be negative");
            setRADailyPrice();
        } else{
            setRaGradeAPrice(tempRaA);
        }

        System.out.println("Please enter grade B price.");
        double tempRaB = inputHandler.getPrice();
        if(tempRaB == -1){
            System.out.println("ERROR - Price cannot be negative");
            setRADailyPrice();
        }else{
            setRaGradeBPrice(tempRaB);
        }

        System.out.println("Please enter grade C price.");
        double tempRaC = inputHandler.getPrice();
        if(tempRaC == -1){
            System.out.println("ERROR - Price cannot be negative");
            setRADailyPrice();
        }else{
            setRaGradeCPrice(tempRaC);
        }

        System.out.println("Please enter rejects price.");
        double tempRaR = inputHandler.getPrice();
        if(tempRaR == -1){
            System.out.println("ERROR - Price cannot be negative");
            setRADailyPrice();
        }else{
            setRaRejectedPrice(tempRaR);
        }

        System.out.println("Your prices are: \nGrade A: " + getRaGradeAPrice()
                + "\nGrade B: " + getRaGradeBPrice() + "\nGrade C: " + getRaGradeCPrice()
                + "\nRejects: " + getRaRejectedPrice() + "\nIs this correct?");
        System.out.println("1. YES - SAVE");
        System.out.println("2. NO - RESTART");
        int confirm = inputHandler.decide(1,2);
        if(confirm == 2){
            setRADailyPrice();
        }

    }

    void setBLDailyPrice() {
        System.out.println("\nPlease enter prices for " + listOfFruits[2]);
        System.out.println("Please enter grade A price.");
        double tempBlA = inputHandler.getPrice();
        if(tempBlA == -1){
            System.out.println("ERROR - Price cannot be negative");
            setBLDailyPrice();
        }else{
            setBlGradeAPrice(tempBlA);
        }

        System.out.println("Please enter grade B price.");
        double tempBlB = inputHandler.getPrice();
        if(tempBlB == -1){
            System.out.println("ERROR - Price cannot be negative");
            setBLDailyPrice();
        }else{
            setBlGradeBPrice(tempBlB);
        }

        System.out.println("Please enter grade C price.");
        double tempBlC = inputHandler.getPrice();
        if(tempBlC == -1){
            System.out.println("ERROR - Price cannot be negative");
            setBLDailyPrice();
        }else{
            setBlGradeCPrice(tempBlC);
        }

        System.out.println("Please enter rejects price.");
        double tempBlR = inputHandler.getPrice();
        if(tempBlR == -1){
            System.out.println("ERROR - Price cannot be negative");
            setBLDailyPrice();
        }else{
            setBlRejectedPrice(tempBlR);
        }

        System.out.println("Your prices are: \nGrade A: " + getBlGradeAPrice()
                + "\nGrade B: " + getBlGradeBPrice() + "\nGrade C: " + getBlGradeCPrice()
                + "\nRejects: " + getBlRejectedPrice() + "\nIs this correct?");
        System.out.println("1. YES - SAVE");
        System.out.println("2. NO - RESTART");
        int confirm = inputHandler.decide(1,2);
        if(confirm == 2){
            setBLDailyPrice();
        }
    }

    void setGODailyPrice() {
        System.out.println("\nPlease enter prices for " + listOfFruits[3]);
        System.out.println("Please enter grade A price.");
        double tempGoA = inputHandler.getPrice();
        if(tempGoA == -1){
            System.out.println("ERROR - Price cannot be negative");
            setGODailyPrice();
        }else{
            setGoGradeAPrice(tempGoA);
        }

        System.out.println("Please enter grade B price.");
        double tempGoB = inputHandler.getPrice();
        if(tempGoB == -1){
            System.out.println("ERROR - Price cannot be negative");
            setGODailyPrice();
        }else{
            setGoGradeBPrice(tempGoB);
        }
        System.out.println("Please enter grade C price.");
        double tempGoC = inputHandler.getPrice();
        if(tempGoC == -1){
            System.out.println("ERROR - Price cannot be negative");
            setGODailyPrice();
        }else{
            setGoGradeCPrice(tempGoC);
        }

        System.out.println("Please enter rejects price.");
        double tempGoR = inputHandler.getPrice();
        if(tempGoR == -1){
            System.out.println("ERROR - Price cannot be negative");
            setGODailyPrice();
        }else{
            setGoRejectedPrice(tempGoR);
        }

        System.out.println("Your prices are: \nGrade A: " + getGoGradeAPrice()
                + "\nGrade B: " + getGoGradeBPrice() + "\nGrade C: " + getGoGradeCPrice()
                + "\nRejects: " + getGoRejectedPrice() + "\nIs this correct?");
        System.out.println("1. YES - SAVE");
        System.out.println("2. NO - RESTART");
        int confirm = inputHandler.decide(1,2);
        if(confirm == 2){
            setGODailyPrice();
        }
    }

    //Once all prices are set, inside the processor setPrices method this method is called
    //we pass all the values as a parameter to the write to json file method inside the jsonProcessor class.
    public void savePricesToFile(String date){
        jsonP.writeJSONPriceFile(date, stGradeAPrice, stGradeBPrice, stGradeCPrice, stRejectedPrice,
                raGradeAPrice, raGradeBPrice, raGradeCPrice, raRejectedPrice,
                blGradeAPrice, blGradeBPrice, blGradeCPrice, blRejectedPrice,
                goGradeAPrice, goGradeBPrice, goGradeCPrice, goRejectedPrice);

    }

    //setters for all fruit and grades
    public void setStGradeAPrice(double stGradeAPrice) { this.stGradeAPrice = stGradeAPrice; }
    public void setStGradeBPrice(double stGradeBPrice) { this.stGradeBPrice = stGradeBPrice; }
    public void setStGradeCPrice(double stGradeCPrice) { this.stGradeCPrice = stGradeCPrice; }
    public void setStRejectedPrice(double stRejectedPrice) { this.stRejectedPrice = stRejectedPrice; }

    public void setRaGradeAPrice(double raGradeAPrice) { this.raGradeAPrice = raGradeAPrice; }
    public void setRaGradeBPrice(double raGradeBPrice) { this.raGradeBPrice = raGradeBPrice; }
    public void setRaGradeCPrice(double raGradeCPrice) { this.raGradeCPrice = raGradeCPrice; }
    public void setRaRejectedPrice(double raRejectedPrice) { this.raRejectedPrice = raRejectedPrice; }

    public void setBlGradeAPrice(double blGradeAPrice) { this.blGradeAPrice = blGradeAPrice; }
    public void setBlGradeBPrice(double blGradeBPrice) { this.blGradeBPrice = blGradeBPrice; }
    public void setBlGradeCPrice(double blGradeCPrice) { this.blGradeCPrice = blGradeCPrice; }
    public void setBlRejectedPrice(double blRejectedPrice) { this.blRejectedPrice = blRejectedPrice; }

    public void setGoGradeAPrice(double goGradeAPrice) { this.goGradeAPrice = goGradeAPrice; }
    public void setGoGradeBPrice(double goGradeBPrice) { this.goGradeBPrice = goGradeBPrice; }
    public void setGoGradeCPrice(double goGradeCPrice) { this.goGradeCPrice = goGradeCPrice; }
    public void setGoRejectedPrice(double goRejectedPrice) { this.goRejectedPrice = goRejectedPrice; }

    //getters for all fruit and grades
    public double getStGradeAPrice() { return stGradeAPrice; }
    public double getStGradeBPrice() { return stGradeBPrice; }
    public double getStGradeCPrice() { return stGradeCPrice; }
    public double getStRejectedPrice() { return stRejectedPrice; }

    public double getBlGradeAPrice() { return blGradeAPrice; }
    public double getBlGradeBPrice() { return blGradeBPrice; }
    public double getBlGradeCPrice() { return blGradeCPrice; }
    public double getBlRejectedPrice() { return blRejectedPrice; }

    public double getRaGradeAPrice() { return raGradeAPrice; }
    public double getRaGradeBPrice() { return raGradeBPrice; }
    public double getRaGradeCPrice() { return raGradeCPrice; }
    public double getRaRejectedPrice() { return raRejectedPrice; }

    public double getGoGradeAPrice() { return goGradeAPrice; }
    public double getGoGradeBPrice() { return goGradeBPrice; }
    public double getGoGradeCPrice() { return goGradeCPrice; }
    public double getGoRejectedPrice() { return goRejectedPrice; }

    public String[] getListOfFruits() { return listOfFruits; }

    public void setListOfFruits(String[] listOfFruits) { this.listOfFruits = listOfFruits; }
}
