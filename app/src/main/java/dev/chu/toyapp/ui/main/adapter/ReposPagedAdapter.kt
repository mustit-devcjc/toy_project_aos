//package dev.chu.toyapp.ui.main.adapter
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.paging.PagedList
//import androidx.paging.PagedListAdapter
//import androidx.recyclerview.widget.DiffUtil
//import dev.chu.toyapp.R
//import dev.chu.toyapp.entity.GithubRepos
//
//class ReposPagedAdapter(private val callback: (GithubRepos) -> Unit) : PagedListAdapter<GithubRepos, ReposViewHolder>(
//    Companion
//) {
//
//    companion object : DiffUtil.ItemCallback<GithubRepos>() {
//        override fun areItemsTheSame(
//            oldItem: GithubRepos,
//            newItem: GithubRepos
//        ): Boolean = oldItem.id == newItem.id
//
//        override fun areContentsTheSame(
//            oldItem: GithubRepos,
//            newItem: GithubRepos
//        ): Boolean = oldItem == newItem
//    }
//
//
////    private var itemList: MutableList<GithubRepositories> = mutableListOf()
////    private var arrayList: ArrayList<GithubRepositories> = ArrayList()
////    private var items: MutableList<GithubRepositories> = mutableListOf()
//
//    override fun onCurrentListChanged(
//        previousList: PagedList<GithubRepos>?,
//        currentList: PagedList<GithubRepos>?
//    ) {
////        println("previousList")
////        previousList?.forEach {
////            println(it)
////        }
////        println("currentList")
////        currentList?.forEach {
////            println(it)
////        }
//
////        items = currentList as MutableList<GithubRepositories>
////        itemList = items
////        arrayList.clear()
////        arrayList.addAll(items)
//
//        super.onCurrentListChanged(previousList, currentList)
//    }
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder {
//        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
//        return ReposViewHolder(inflater, callback)
//    }
//
//    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
//        getItem(position)?.let {
////        itemList[position].let {
//            holder.bind(it)
//        }
//    }
//
////    fun filter(text: String) {
////        itemList.clear()
////        if (text.isEmpty()) {
////            itemList.addAll(arrayList)
////        } else {
////            for (mainItem in arrayList) {
////                val item = mainItem.fullName
////                if (item.toLowerCase(Locale.getDefault()).contains(text)) {
////                    itemList.add(mainItem)
////                }
////            }
////        }
////        notifyDataSetChanged()
////    }
//}