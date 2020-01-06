package bean;

public class Role {
    String name;
    Boolean isIdentified;
    Boolean isExternal;
    Boolean isGlobal;
    String password;
    String schema;
    String pakage;

    public Role() {}

    public Role(String name, Boolean isIdentified, Boolean isExternal, Boolean isGlobal, String password, String schema, String pakage) {
        if(name!=null)
        this.name = name.toUpperCase();
        this.isIdentified = isIdentified;
        this.isExternal = isExternal;
        this.isGlobal = isGlobal;
        this.password = password;
        this.schema = schema;
        this.pakage = pakage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIdentified() {
        return isIdentified;
    }

    public void setIdentified(Boolean identified) {
        isIdentified = identified;
    }

    public Boolean getExternal() {
        return isExternal;
    }

    public void setExternal(Boolean external) {
        isExternal = external;
    }

    public Boolean getGlobal() {
        return isGlobal;
    }

    public void setGlobal(Boolean global) {
        isGlobal = global;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getPakage() {
        return pakage;
    }

    public void setPakage(String pakage) {
        this.pakage = pakage;
    }

    @Override
    public String toString() {
        return name;
    }
}
