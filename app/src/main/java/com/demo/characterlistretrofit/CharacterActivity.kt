package com.demo.characterlistretrofit

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide


class CharacterActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_data)

        val charName: TextView = findViewById(R.id.character_name)
        val charImage: ImageView = findViewById(R.id.character_image)
        val charStatus: TextView = findViewById(R.id.character_status)
        val charSpecies: TextView = findViewById(R.id.character_species)
        val charGender: TextView = findViewById((R.id.character_gender))
        val charCreated: TextView = findViewById(R.id.character_created)
        val charUrl: TextView = findViewById(R.id.character_url)
        val spinner: Spinner = findViewById(R.id.spin_episode)
        val episodeLabel: TextView = findViewById(R.id.episode_label)

        val bundle: Bundle?=intent.extras


        val name = bundle!!.getString("name")
        val imgId = bundle.getString("image")
        val status = bundle.getString("status")
        val species = bundle.getString("species")
        val gender = bundle.getString("gender")
        val created = bundle.getString("created")
        val url = bundle.getString("url")
        val episode = bundle.getStringArrayList("episode")
        print(episode)
        episode as ArrayList<String>
        val adapter: ArrayAdapter<String>? = ArrayAdapter<String>(
            applicationContext,
            android.R.layout.simple_spinner_dropdown_item, episode)
        if (adapter != null) {
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        spinner.adapter = adapter

        Glide.with(charImage)
            .load(imgId)
            .into(charImage)

        charName.text = name
        charStatus.text = "Status: "+status
        charSpecies.text = "Species: "+species
        charGender.text = "Gender: " + gender
        charCreated.text = "Created: "+created
        charUrl.text = "Url: "+url
        episodeLabel.text = "No. of Episodes: "+ episode.size
    }
}