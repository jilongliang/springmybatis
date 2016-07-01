package com.flong.pojo.vo;

public class UserVo {
    private Integer id;

    private String username;

    private String password;

    private Integer age;
    
    private String type;//类型名称

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	 
}