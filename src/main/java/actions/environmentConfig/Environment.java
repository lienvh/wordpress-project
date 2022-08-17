package actions.environmentConfig;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"classpath:${envOwner}.properties"})
public interface Environment extends Config {
    @Key("app.adminUrl")
    String adminUrl();

    @Key("app.userUrl")
    String userUrl();

    @Key("db.hotname")
    String dbHotName();

    @Key("db.userName")
    String dbUserName();

    @Key("db.password")
    String dbPassword();

}

