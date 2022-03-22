package com.demo.characterlistretrofit.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.characterlistretrofit.R
import com.demo.characterlistretrofit.data.CharacterModel
import kotlinx.android.synthetic.main.group_list_row.view.*

class GroupListAdapter(val activity: Activity): RecyclerView.Adapter<GroupListAdapter.MyViewHolder>() {

    public var distinctSpecies= mutableSetOf<String>()
    private var groupList: List<CharacterModel>? = null
    var species: ArrayList<String>? = null
    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setGroupList(species: ArrayList<String>) {
        this.species = species
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GroupListAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.group_list_row, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: GroupListAdapter.MyViewHolder, position: Int) {
        holder.bind(species?.get(position)!!, activity)
    }

    override fun getItemCount(): Int {
        if(species == null)return 0
        else return species?.size!!
    }

    inner class MyViewHolder(view : View): RecyclerView.ViewHolder(view){

          val GroupName = view.GroupName
//        val TotalEpisodes = view.TotalEpisodes
//
//        init {
//            itemView.setOnClickListener{
//                listener.onItemClick(adapterPosition)
//            }
//        }

        fun bind(data: String, activity: Activity) {
//            if(distinctSpecies.contains(data.species)) {return}
//            if(!(distinctSpecies.contains(data.species))){
//                GroupName.text = data.species
//                distinctSpecies.add(data.species as String)
//                print(data.species)
//            }

            GroupName.text = data
        }
    }
}