import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FoodTest {

    @Test
    void stopTest() {
        Aquarium aquarium = new Aquarium();
        Food food = new Food(1);
        Aquarium.foods.add(food);
        food.run();
        assertFalse(food.isRunning(), "Running state of food should be false");
    }

    @Test
    void startTest() {
        Aquarium aquarium = new Aquarium();
        Food food = new Food(0);
        Aquarium.foods.add(food);
        double prevAbsis = food.getPosition().getOrdinat();
        food.start();
        while(food.isRunning()) {
            prevAbsis = food.getPosition().getOrdinat();
            System.out.println(food.getPosition().getOrdinat());
            assertTrue(food.getPosition().getOrdinat() > prevAbsis, "Ordinate position of food should be greater than initial position");
        }
    }
}