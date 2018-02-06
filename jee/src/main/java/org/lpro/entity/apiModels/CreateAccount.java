package org.lpro.entity.apiModels;

import io.swagger.annotations.ApiModel;

@ApiModel(value="CreateAccount", description="Modèle pour passer du JSON pour s'inscrire")
public class CreateAccount {

    private String fullname;

    private String mail;

    private String password;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
