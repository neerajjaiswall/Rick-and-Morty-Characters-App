package com.demo.characterlistretrofit

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.characterlistretrofit.adapter.CharacterListAdapter
import com.demo.characterlistretrofit.adapter.GroupListAdapter
import com.demo.characterlistretrofit.data.CharacterModel
import com.demo.characterlistretrofit.repository.Repository
import com.demo.characterlistretrofit.retrofit.RetroInstance
import com.demo.characterlistretrofit.retrofit.RetroServiceInterface
import com.demo.characterlistretrofit.viewmodel.MainActivityViewModel
import com.demo.characterlistretrofit.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_all_groups.*
import kotlinx.android.synthetic.main.activity_main.*

class AllGroups : AppCompatActivity() {
    public val arr = ArrayList<CharacterModel>()
    public var species = arrayListOf<String>("Human", "Alien", "Poopybutthole", "Mythological Creature", "Disease", "Humanoid", "Animal", "Cronenberg", "Robot", "unknown")
    private lateinit var mainViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_groups)
        this.title = "All Species"
        initRecyclerView()
    }

    private fun initRecyclerView() {
        var recyclerAdapter: GroupListAdapter
        groupListRecyclerview.layoutManager = LinearLayoutManager(this)

        recyclerAdapter = GroupListAdapter(this)
        groupListRecyclerview.adapter = recyclerAdapter
        recyclerAdapter.setGroupList(species)
    }
}