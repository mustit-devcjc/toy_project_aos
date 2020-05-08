package dev.chu.toyapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import dev.chu.basemodule.BaseAdapter
import dev.chu.basemodule.BaseViewHolder
import dev.chu.toyapp.R
import dev.chu.toyapp.entity.GithubRepos
import java.util.*
import kotlin.collections.ArrayList

class ReposAdapter(
    items: MutableList<GithubRepos>
) : BaseAdapter<GithubRepos, ReposViewHolder>(items) {

    private var itemList: MutableList<GithubRepos> = mutableListOf()
    private var arrayList: ArrayList<GithubRepos> = ArrayList()

    override fun setNewItems(newItems: List<GithubRepos>) {
        super.setNewItems(newItems)
        arrayList.clear()
        arrayList.addAll(newItems)
    }

    init {
        itemList = items
        arrayList = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        return ReposViewHolder(view)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        (holder as ReposViewHolder).bind(itemList[position])
    }

    fun filter(charText: String) {
        itemList.clear()
        if (charText.isEmpty()) {
            itemList.addAll(arrayList)
        } else {
            for (mainItem in arrayList) {
                val item = mainItem.name
                if (item.toLowerCase(Locale.getDefault()).contains(charText)) {
                    itemList.add(mainItem)
                }
            }
        }
        notifyDataSetChanged()
    }
}