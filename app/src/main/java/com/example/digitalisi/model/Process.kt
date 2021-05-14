package com.example.digitalisi.model

data class Process(
    val activationState: String,
    val actorinitiatorid: String,
    val configurationState: String,
    val deployedBy: String,
    val deploymentDate: String,
    val description: String,
    val displayDescription: String,
    val displayName: String,
    val id: String,
    val last_update_date: String,
    val name: String,
    val version: String
)