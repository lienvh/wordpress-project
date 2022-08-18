package pageUIs.userPageUIs;

public class HomePageUI {
    public static final String POST_TITLE_BY_TEXT = "xpath= //article//h2/a[text()='%s']";
    public static final String POST_CREATED_DATE_BY_TEXT = "xpath= //article//a[text()='%s']/parent::h2//following-sibling::div//time[text()='%s']";
    public static final String POST_AUTHOR_BY_TEXT = "xpath= //article//a[text()='%s']/parent::h2//following-sibling::div//span[@class='author vcard']/a[text()='%s']";
    public static final String POST_BODY_BY_TEXT = "xpath= //article//a[text()='%s']//ancestor::header//following-sibling::div/p[text()='%s']";
    public static final String POST_SEARCH_TEXBOX = "css= input.wp-block-search__input ";
    public static final String POST_SEARCH_BUTTON = "css= button.wp-block-search__button   ";
}