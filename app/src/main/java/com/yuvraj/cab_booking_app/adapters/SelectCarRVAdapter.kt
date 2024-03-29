package com.yuvraj.cab_booking_app.adapters

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.yuvraj.cab_booking_app.DataClasses.Car
import com.yuvraj.cab_booking_app.R

class SelectCarRVAdapter(val cars: ArrayList<Car>):
    RecyclerView.Adapter<SelectCarRVAdapter.SelectCarViewHolder>() {

    inner class SelectCarViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectCarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.select_car_item,parent,false)
        return SelectCarViewHolder(view)
    }

    override fun onBindViewHolder(holder: SelectCarViewHolder, position: Int) {
        holder.itemView.apply {
          findViewById<ImageView>(R.id.car_iv_sci).load(cars[position].carImageRes) {
              crossfade(true)
              placeholder(R.drawable.place_holder)
            }
            findViewById<Button>(R.id.book_now_btn_scf).setOnClickListener {
                val db = Firebase.firestore
                var myBookingMap = hashMapOf(
                    "CabNum" to cars[position].carNum,
                    "ALoc" to "Etawah",
                    "DLoc" to "Goa",
                    "cabTime" to "8PM",
                    "cabDate" to "04/01/23"
                )

                db.collection(FirebaseAuth.getInstance().currentUser!!.uid).document(cars[position].carName.toString()).set(myBookingMap)
            }
            findViewById<TextView>(R.id.car_name_tv_sci).text = cars[position].carName
            findViewById<TextView>(R.id.car_num_tv_sci).text = cars[position].carNum
            findViewById<TextView>(R.id.rate_tv_sci).text = cars[position].carRate
        }
    }

    override fun getItemCount(): Int {
        return cars.size
    }
}