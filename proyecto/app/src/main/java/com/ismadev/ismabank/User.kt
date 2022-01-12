package com.ismadev.ismabank

class User {

    var email : String
    var name : String

    constructor(email: String, name: String) {
        this.email = email
        this.name = name
    }


    override fun toString(): String {
        return "User(email='$email', name='$name')"
    }


}