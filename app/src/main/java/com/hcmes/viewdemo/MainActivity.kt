package com.hcmes.viewdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hcmes.viewdemo.l2.Lin2Activity
import com.hcmes.viewdemo.l1.Lin1Activity
import com.hcmes.viewdemo.l3.Lin3Activity
import com.hcmes.viewdemo.l4.Lin4Activity
import com.hcmes.viewdemo.l5.Lin5Activity
import com.hcmes.viewdemo.l6.Lin6Activity
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    var array= ArrayList<ListDemo>()
    lateinit var recyclerView:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        array.add(ListDemo("画布",Lin1Activity::class.java))
        array.add(ListDemo("补间动画和帧动画 （视图动画）",Lin2Activity::class.java))
        array.add(ListDemo("属性动画", Lin3Activity::class.java))
        array.add(ListDemo("布局动画", Lin4Activity::class.java))
        array.add(ListDemo("PathMeasure", Lin5Activity::class.java))
        array.add(ListDemo("Path", Lin6Activity::class.java))
        recyclerView=  findViewById<RecyclerView>(R.id.recyclerView);
        var manager=  LinearLayoutManager(this@MainActivity)
        manager.setOrientation(RecyclerView.VERTICAL)
        var adapter= ListAdapter<ListDemo>();
        recyclerView.adapter=adapter
        recyclerView.layoutManager=manager
    }
    inner class  ListAdapter<T>: RecyclerView.Adapter<ListViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
           var itemView= LayoutInflater.from(baseContext).inflate(R.layout.recy_item,parent,false)
           var viewHolder=ListViewHolder(itemView)
            return  viewHolder;
        }
        override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
            holder.textview!!.setText(array[position].name)
            holder.textview!!.setOnClickListener{
                var item= array[position]
                var intent=Intent(baseContext,item.clazz)
                startActivity(intent)
            }
        }

        override fun getItemCount(): Int {
           return array.size
        }


    }
    inner class ListViewHolder : RecyclerView.ViewHolder {
        var textview:TextView?=null
         constructor(itemView: View):super(itemView){
             textview=  itemView.findViewById<TextView>(R.id.textView);
         }

    }


    inner class ListDemo {
        var name:String?=null
        var clazz:Class<*>?=null

        constructor(name: String?, clazz: Class<*>?) {
            this.name = name
            this.clazz = clazz
        }
    }
}