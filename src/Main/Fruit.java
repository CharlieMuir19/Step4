//The class Fruit controls which fruit is set based of the input from the user
public class Fruit {

    private String finalFruit;
    private String fruitCode;

    //Ask for input, recieve input and use switch statement to set the fruit type and fruit code.
    public void askFruitType() {
        System.out.println("Choose a fruit: " +
                "\n1. Strawberry " +
                "\n2. Raspberry " +
                "\n3. Blackberry " +
                "\n4. Gooseberry " +
                "\n >");
        int choice = InputHandler.decide(1,4);
        switch (choice) {
            case 1:
                setSTFruit();
                break;
            case 2:
                setRAFruit();
                break;
            case 3:
                setBLFruit();
                break;
            case 4:
                setGOFruit();
                break;
            case -1:
                askFruitType();
                break;
        }
    }

    //each fruit type has a seperate method to set the fruit and code
    public void setSTFruit() {
        finalFruit = "Strawberry";
        fruitCode = "ST";
    }
    public void setRAFruit() {
        finalFruit = "Raspberry";
        fruitCode = "RA";
    }
    public void setBLFruit() {
        finalFruit = "Blackberry";
        fruitCode = "BL";
    }
    public void setGOFruit(){
        finalFruit = "Gooseberry";
        fruitCode = "GO";
    }

    //we use these methods when setting the fruit type and fruit code in the Processor class.
    public String returnFruit(){
        return finalFruit;
    }

    public String returnFruitCode(){
        return fruitCode;
    }
}
