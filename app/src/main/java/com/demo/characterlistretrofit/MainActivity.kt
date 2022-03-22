package com.demo.characterlistretrofit

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.characterlistretrofit.adapter.CharacterListAdapter
import com.demo.characterlistretrofit.data.CharacterModel
import com.demo.characterlistretrofit.repository.Repository
import com.demo.characterlistretrofit.retrofit.RetroInstance
import com.demo.characterlistretrofit.retrofit.RetroServiceInterface
import com.demo.characterlistretrofit.viewmodel.MainActivityViewModel
import com.demo.characterlistretrofit.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    public val arr = ArrayList<CharacterModel>()
    private lateinit var mainViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val service = RetroInstance.getRetroInstance().create(RetroServiceInterface::class.java)
        val repository = Repository(service)
        mainViewModel = ViewModelProvider(this,
            MainViewModelFactory(repository)
        ).get(MainActivityViewModel::class.java)
        mainViewModel.characters.observe(this) {
                arr.clear()
            for (i in 0 until 825) {
                val currentCharacter =
                    CharacterModel(it[i].name, it[i].image, it[i].status, it[i].species, it[i].gender, it[i].created, it[i].url, it[i].episode)
                arr.add(currentCharacter)
            }
            initRecyclerView(arr)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.groupCharacters -> {
                var i = Intent(this@MainActivity, AllGroups::class.java )
                startActivity(i)
            }

            R.id.episodeasc -> {
                arr.sortBy {it.episode?.size}
                initRecyclerView(arr)
            }
            R.id.episodedesc -> {
                arr.sortByDescending {it.episode?.size}
                initRecyclerView(arr)
            }
            R.id.nameasc -> {
                arr.sortBy {it.name}
                initRecyclerView(arr)
            }
            R.id.namedesc -> {
                arr.sortByDescending{it.name}
                initRecyclerView(arr)
            }
        }
        return super.onOptionsItemSelected(item)
    }



    private fun initRecyclerView(arr: ArrayList<CharacterModel>) {
        var recyclerAdapter: CharacterListAdapter
        characterListRecyclerview.layoutManager = LinearLayoutManager(this)

        recyclerAdapter = CharacterListAdapter(this)
        characterListRecyclerview.adapter = recyclerAdapter
        recyclerAdapter.setOnItemClickListener(object: CharacterListAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {

                var intent = Intent(this@MainActivity, CharacterActivity::class.java )
                intent.putExtra("name", arr[position].name)
                intent.putExtra("image", arr[position].image)
                intent.putExtra("status", arr[position].status)
                intent.putExtra("species", arr[position].species)
                intent.putExtra("gender", arr[position].gender)
                intent.putExtra("created", arr[position].created)
                intent.putExtra("url", arr[position].url)
                intent.putExtra("episode", arr[position].episode)
                println(arr[position].name)
                startActivity(intent)
            }

        })
        recyclerAdapter.setCharacterList(arr)
    }
}