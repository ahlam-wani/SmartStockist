package com.smartstockist.app.ui.client

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.smartstockist.app.data.model.GetClients
import com.smartstockist.app.databinding.ClientsViewBinding

//class ClientAdapter(private val clients:List<GetClients>):RecyclerView.Adapter<ClientAdapter.ViewHolder>() {
  //  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientAdapter.ViewHolder {
    //                  val clientBinding=ClientsViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
      //                  return ViewHolder(clientBinding,parent)
    //}

   // override fun onBindViewHolder(holder: ClientAdapter.ViewHolder, position: Int) {
     //        holder.bindTo(clients[position])
    //}

    //override fun getItemCount(): Int {
      //            return clients.size
    //}
    //class ViewHolder(val view:ClientsViewBinding,val parent: ViewGroup):RecyclerView.ViewHolder(view.root){
      //          fun bindTo(client: GetClients){
        //            view.tvName.text=client.name
          //          view.tvNumber.text=client.mainContactNumber
            //        view.tvPrice.text=client.balance.toString()
              //  }
    //}
//}
class ClientAdapter :PagedListAdapter<GetClients,ClientAdapter.ViewHolder>(USER_COMPARATOR) {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ClientAdapter.ViewHolder {
            val clientBinding =
                ClientsViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                            return ViewHolder(clientBinding,parent)

        }

        override fun onBindViewHolder(holder: ClientAdapter.ViewHolder, position: Int) {
            val client=getItem(position)
            client?.let {
            holder.bindTo(it)}

        }

        class ViewHolder(val view: ClientsViewBinding, val parent: ViewGroup) :
            RecyclerView.ViewHolder(view.root) {
            fun bindTo(client: GetClients) {
                view.tvName.text = client.name
                view.tvNumber.text = client.mainContactNumber
                view.tvPrice.text = client.balance.toString()
            }
        }
        companion object{
            private val USER_COMPARATOR=object :DiffUtil.ItemCallback<GetClients>(){
                override fun areItemsTheSame(oldItem: GetClients, newItem: GetClients): Boolean =
                                oldItem.id==newItem.id


                override fun areContentsTheSame(oldItem: GetClients, newItem: GetClients): Boolean =
                    newItem==oldItem

            }
        }
    }
    //}