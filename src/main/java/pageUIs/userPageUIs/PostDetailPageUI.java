package pageUIs.userPageUIs;

public class PostDetailPageUI {
    public static final String POST_TITLE = "xpath= //article//h1[text()='%s']";
    public static final String POST_CREATED_DATE = "xpath= //article//h1[text()='%s']/following-sibling::div//time[text()='%s']";
    public static final String POST_AUTHOR = "xpath= //article//h1[text()='%s']/following-sibling::div//span[@class='author vcard']/a[text()='%s']";
    public static final String POST_BODY = "xpath= //article//h1[text()='%s']/parent::header//following-sibling::div/p[text()='%s']";

}