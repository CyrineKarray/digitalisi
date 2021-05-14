package com.example.digitalisi.model

data class User(
    val branding_version: String,
    val conf: String,
    val copyright: String,
    val is_guest_user: String,
    val is_technical_user: String,
    val session_id: String,
    val user_id: String,
    val user_name: String,
    val version: String
)