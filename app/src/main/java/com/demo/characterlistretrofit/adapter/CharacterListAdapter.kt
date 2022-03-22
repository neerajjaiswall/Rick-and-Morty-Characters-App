package com.demo.characterlistretrofit.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import androidx.recyclerview.widget.RecyclerView
import com.demo.characterlistretrofit.AllGroups
import com.demo.characterlistretrofit.R
import com.demo.characterlistretrofit.data.CharacterModel
import kotlinx.android.synthetic.main.character_list_row.view.*

class CharacterListAdapter(val activity: Activity): RecyclerView.Adapter<CharacterListAdapter.MyViewHolder>() {

    private var characterList: List<CharacterModel>? = null
    private lateinit var mListener : onItemClickListener
interface onItemClickListener {
    fun onItemClick(position: Int)
}

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener

    }

    fun setCharacterList(characterList: ArrayList<CharacterModel>?) {
        this.characterList = characterList
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterListAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.character_list_row, parent, false)

        return MyViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: CharacterListAdapter.MyViewHolder, position: Int) {
        holder.bind(characterList?.get(position)!!, AllGroups())
    }

    override fun getItemCount(): Int {
        if(characterList == null)return 0
        else return characterList?.size!!
    }

    class MyViewHolder(view : View, listener: onItemClickListener): RecyclerView.ViewHolder(view){
        val charImage = view.charImage
        val charName = view.charName
        val charStatus = view.charStatus
        val charGender = view.charGender

        init {
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }
        fun bind(data: CharacterModel, activity: AllGroups) {
            charName.text = data.name +" (" + data.species+")"
            charStatus.text = "Status: "+data.status
            charGender.text = "Gender: "+data.gender

            Glide.with(charImage)
                .load(data.image)
                .into(charImage)
        }
    }
}