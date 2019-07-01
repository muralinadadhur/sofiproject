package com.automation.techassessment.ui.pages.sauce;

import com.automation.techassessment.ui.lib.UIThreadManager;
import com.slickqa.webdriver.FindBy;
import com.slickqa.webdriver.PageElement;

public class ShoppingCartPage {
    private PageElement products_Label = new PageElement("Products",FindBy.className("product_label"));
    private PageElement onesie_Label = new PageElement("Sauce Labs Onsie", FindBy.id("item_2_img_link"));
    private PageElement bike_light = new PageElement("Sauce Labs Onsie", FindBy.id("item_0_img_link"));
    private PageElement add_to_Cart = new PageElement("Add to Cart", FindBy.cssSelector("button[class='btn_primary btn_inventory']"));
    private PageElement number_of_Elements_Cart = new PageElement("Cart List", FindBy.xpath("//*[@id=\"shopping_cart_container\"]/a/span"));

    public boolean product_Label_Exists() {
        return UIThreadManager.getBrowser().exists(products_Label);
    }
    public boolean onsie_Label_Exists() {
        return UIThreadManager.getBrowser().exists(onesie_Label);
    }
    public boolean bike_Label_Exists() {
        return UIThreadManager.getBrowser().exists(bike_light);
    }
    //Click on the item and load page that contains the specific item.
    //Click on Add to cart
    //Return number of items in the cart.
    public String onsie_Label_Click(String items_to_be_added)  {
        if (items_to_be_added.equals("Onsie")) {
            UIThreadManager.getBrowser().click(onesie_Label);
        } else if (items_to_be_added.equals("Bike")) {
            UIThreadManager.getBrowser().click(bike_light);
        }
        if(UIThreadManager.getBrowser().exists(add_to_Cart)){
            UIThreadManager.getBrowser().click(add_to_Cart);
        }
        return UIThreadManager.getBrowser().getText(number_of_Elements_Cart);
    }
}
