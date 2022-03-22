package com.demo.characterlistretrofit.data

data class CharacterModel(val name: String?,
                          val image: String?,
                          val status: String?,
                          val species: String?,
                          val gender: String?,
                          val created: String?,
                          val url: String?,
                          val episode: ArrayList<String>?)
