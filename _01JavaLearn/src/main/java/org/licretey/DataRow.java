package org.licretey;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class DataRow implements Serializable {

    @JsonProperty("insert_user_id")
    private long insertUserID;
    @JsonProperty("insert_timestamp")
    private String insertTimestamp;
    @JsonProperty("role_code")
    private String roleCode;
    private long version;
    @JsonProperty("role_name")
    private String roleName;
    @JsonProperty("authorizable")
    private boolean authorizable;
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("update_user_id")
    private long updateUserID;
    @JsonProperty("update_timestamp")
    private String updateTimestamp;
    @JsonProperty("user_code")
    private String userCode;
    @JsonProperty("user_id")
    private long userID;
    @JsonProperty("role_id")
    private long roleID;
    @JsonProperty("nickname")
    private String nickname;
    private long id;
    @JsonProperty("category")
    private String category;
    @JsonProperty("org_name")
    private String orgName;

    @JsonProperty("username")
    private String username;

    public long getInsertUserID() {
        return insertUserID;
    }

    public void setInsertUserID(long insertUserID) {
        this.insertUserID = insertUserID;
    }

    public String getInsertTimestamp() {
        return insertTimestamp;
    }

    public void setInsertTimestamp(String insertTimestamp) {
        this.insertTimestamp = insertTimestamp;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public boolean isAuthorizable() {
        return authorizable;
    }

    public void setAuthorizable(boolean authorizable) {
        this.authorizable = authorizable;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public long getUpdateUserID() {
        return updateUserID;
    }

    public void setUpdateUserID(long updateUserID) {
        this.updateUserID = updateUserID;
    }

    public String getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(String updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public long getRoleID() {
        return roleID;
    }

    public void setRoleID(long roleID) {
        this.roleID = roleID;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
