package net.greenbone.demolibrary.bdd.helper.representations;


public class KeyCloakLoginRequest {
    private String grant_type;
    private String username;
    private String password;
    private String client_id;
    private String client_secret;

    public KeyCloakLoginRequest(String grant_type, String username, String password, String client_id, String client_secret) {
        this.grant_type = grant_type;
        this.username = username;
        this.password = password;
        this.client_id = client_id;
        this.client_secret = client_secret;
    }
}
