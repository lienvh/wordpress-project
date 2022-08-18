package pageUIs.adminPageUIs;

public class AllPostsPageUI {
    public static final String locator = "";

    public static final String ADD_NEW_BUTTON = "css= a.page-title-action";
    public static final String SEARCH_TEXTBOX = "id= post-search-input";
    public static final String SEARCH_BUTTON = "id= search-submit";
    public static final String HEADER_INDEX_BY_ID = "xpath= //thead//th[@id='%s']/preceding-sibling::*";
    public static final String VALUE_EACH_COLUMN_BY_HEADER_INDEX = "xpath= //tbody//tr/*[%s]//a[contains(text(),'%s')]";
    public static final String TITLE_POST_BY_TEXT = "xpath= //tbody//a[text()='%s']";
    public static final String CHECKBOX_ON_ROW_BY_POST_TITLE = "xpath= //tbody//a[text()='%s']//ancestor::tr//input";
    public static final String ACTION_DROPDOWN = "name= action";
    public static final String MOVED_TO_TRASH_MESSAGE = "xpath= //div[@id='message']/p[text()='%s']";
    public static final String APPLY_BUTTON = "id= doaction";
    public static final String NOT_FOUND_SEARCH_RESULT = "xpath= //tbody//td[text()='No posts found.']";
    public static final String TOTAL_RECORD_BY_POST_STATUS = "css= li.%s span";
}
