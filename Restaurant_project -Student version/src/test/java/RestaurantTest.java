import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    private static Restaurant restaurant;
    private static Restaurant res;
    int initialMenuSize=0;

    @BeforeEach
    public void setup(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
    }

    //REFACTOR ALL THE REPEATED LINES OF CODE

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TEST BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        setup();
        assertTrue(restaurant.isRestaurantOpen(LocalTime.parse("23:30:00")));
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        setup();
        assertFalse(restaurant.isRestaurantOpen(LocalTime.parse("17:00:00")));
    }

    @Test
    public void testOrderTotal(){
        Item item1 = new Item("Paneer Chilli",100);
        Item item2 = new Item("Paneer Makhani",150);

        List<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        assertEquals(250,restaurant.totalOrderValue(items));
        //System.out.println();
    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        setup();
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        setup();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        setup();
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}