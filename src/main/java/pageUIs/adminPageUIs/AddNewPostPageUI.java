package pageUIs.adminPageUIs;

public class AddNewPostPageUI {
    public static final String TITLE_TEXTBOX = "css= h1[class*='post-title']";
    public static final String BODY_BUTTON = "css= p[role='button'][class*='block-editor']";
    public static final String BODY_TEXTBOX = "css= p[role='document'][class*='block-editor']";
    public static final String PRE_PUBLISH_OR_UPDATE_BUTTON = "css= div[aria-label='Editor top bar'] button[class*=publish-button]";
    public static final String PUBLISH_BUTTON = "css=div[aria-label='Editor publish'] button[class*=publish-button]";
    public static final String POST_PUBLISHED_OR_EDITED_MESSAGE = "xpath= //div[contains(@class,'notice-container')]//div[text()='%s']";
}

