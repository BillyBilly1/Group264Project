package entity;

import org.json.JSONObject;

import java.util.List;

public class User {
    private String name;
    private final String user_id;
    private final String nickname;
    private String profileUrl;
    private String profileFile;
    private String issueAccessToken;
    private final List<String> discoveryKeys;
    private final JSONObject metadata;

    public User(String user_id, String nickname, String profileUrl, String profileFile,
                String issueAccessToken, List<String> discoveryKeys, JSONObject metadata) {
        this.user_id = user_id;
        this.nickname = nickname;
        this.profileUrl = profileUrl;
        this.profileFile = profileFile;
        this.issueAccessToken = issueAccessToken;
        this.discoveryKeys = discoveryKeys;
        this.metadata = metadata;
    }

    public static  UserBuilder builder() { return new UserBuilder(); }

    public boolean isIssueAccessToken() {
        return true;
    }

    // Getters for all fields

    public static class UserBuilder {
        private String user_id;
        private String nickname;
        private String profileUrl;
        private String profileFile;
        private String issueAccessToken;
        private List<String> discoveryKeys;
        private JSONObject metadata;


        public UserBuilder user_id(String id) {
            this.user_id = id;
            return this;
        }

        public UserBuilder nickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public UserBuilder profileUrl(String profileUrl) {
            this.profileUrl = profileUrl;
            return this;
        }

        public UserBuilder profileFile(String profileFile) {
            this.profileFile = profileFile;
            return this;
        }

        public UserBuilder issueAccessToken(String issueAccessToken){
            this.issueAccessToken = issueAccessToken;
            return this;
        }

        public UserBuilder discoveryKeys(List<String> discoveryKeys) {
            this.discoveryKeys = discoveryKeys;
            return this;
        }

        public UserBuilder metadata(JSONObject metadata) {
            this.metadata = metadata;
            return this;
        }

        public User build() {
            return new User(user_id, nickname, profileUrl, profileFile, issueAccessToken, discoveryKeys,
                    metadata);
        }
    }

    // Other methods and constructors for the User class



    public String getUser_Id() {return user_id;}

    public String getNickname() {return nickname;}

    public String getProfileUrl() { return nickname;}
    public String getProfileFile() { return profileFile;}

    public List<String> getDiscoveryKeys(){ return discoveryKeys;}
    public JSONObject getMetadata() { return metadata;}
}
